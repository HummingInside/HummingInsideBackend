package com.backend.api;

import com.backend.api.exception.ResourceNotFoundException;
import com.backend.application.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
