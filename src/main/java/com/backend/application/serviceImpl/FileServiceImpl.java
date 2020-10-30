package com.backend.application.serviceImpl;

import com.backend.application.service.FileService;
import com.backend.config.StorageProperties;
import com.backend.core.concert.Concert;
import com.backend.core.concert.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class FileServiceImpl implements FileService {

    private final Path directory;
    private final ConcertRepository concertRepository;

    @Autowired
    public FileServiceImpl(StorageProperties properties, ConcertRepository concertRepository) {
        this.directory = Paths.get(properties.getLocation());
        this.concertRepository = concertRepository;
    }

    public String store(Long id, MultipartFile file) throws IOException {
        Files.createDirectories(directory);

        String fileName = StringUtils
                .cleanPath(Objects.requireNonNull(file.getOriginalFilename()))
                .replaceAll(" ", "_");

        if(fileName.contains("..")) return null;
        Path targetPath = directory.resolve(fileName).normalize();
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        Optional<Concert> concert = concertRepository.findById(id);
        concert.ifPresent(c -> c.updateImage(fileName));
        return fileName;
    }

    public Resource load(String fileName) throws MalformedURLException {
        Path filePath = directory.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        return null;
    }
}
