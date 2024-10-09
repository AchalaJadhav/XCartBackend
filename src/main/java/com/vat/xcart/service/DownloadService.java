package com.vat.xcart.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DownloadService {

    private static final String EXTENSION = ".zip";
    private static final String FILE_NAME = "FAQ";

    public ResponseEntity<Resource> downloadBrochure() throws IOException {
        File file = ResourceUtils.getFile("classpath:" + FILE_NAME + EXTENSION + "");
        System.out.println("Path - " + file.getPath());
        System.out.println("Path - " + file.getAbsolutePath());
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + FILE_NAME + EXTENSION + "");
        header.add("Access-Control-Expose-Headers", "content-disposition");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok().headers(header).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }
}
