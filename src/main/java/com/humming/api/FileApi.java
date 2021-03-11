package com.humming.api;

import com.humming.api.exception.ResourceNotFoundException;
import com.humming.application.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@RequestMapping("/files")
@RestController
public class FileApi {

    private final FileService fileService;

    @GetMapping("/{filename}")
    public ResponseEntity<?> serveImage(@PathVariable String filename) throws MalformedURLException {
        Resource file = fileService.load(filename);
        if(file == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file) throws IOException {
        String fileName = fileService.store(file);
        return ResponseEntity.ok(fileName);
    }
}
