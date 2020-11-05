package com.backend.application.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileService {

    String store(MultipartFile file) throws IOException;

    Resource load(String fileName) throws MalformedURLException;

    String delete(Long id, String newFile) throws IOException;
}
