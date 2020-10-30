package com.backend.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String store(Long id, MultipartFile file) throws IOException;
}
