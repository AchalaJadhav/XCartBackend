package com.vat.xcart.controller;

import com.vat.xcart.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/download")
public class DownloadController
{

    @Autowired
    DownloadService downloadService;

    @GetMapping("/brochure")
    public ResponseEntity<Resource> downloadBrochure() throws IOException {

        return downloadService.downloadBrochure();
    }

}
