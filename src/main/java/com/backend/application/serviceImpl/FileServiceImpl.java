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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    private String generateFileName(String fileName){
        final String[] source = {
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "abcdefghijklmnopqrstuvwxyz",
                "0123456789"
        };
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 15; i++){
            int idx = (int)(Math.random()*100%3);
            int charIdx = (int)(Math.random()*100%source[idx].length());
            sb.append(source[idx].charAt(charIdx));
        }
        String extension = fileName.substring(fileName.lastIndexOf("."));
        return sb.toString()+extension;
    }

    public String store(MultipartFile file) throws IOException {
        Files.createDirectories(directory);
        if(file == null){
            return "";
        }
        String fileName = generateFileName(file.getOriginalFilename());
        Path targetPath = directory.resolve(fileName).normalize();
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
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

    public String delete(Long id, String newFile) throws IOException {
        Concert concert = concertRepository.getOne(id);
        Path filePath = directory.resolve(concert.getImgUrl()).normalize();
        File prevFile = new File(filePath.toString());
        if(prevFile.exists() && newFile != null){
            boolean isDeleted = prevFile.delete();
        }
        return filePath.toString();
    }
}
