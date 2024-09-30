package com.innovation.xcartbackend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeroturnaround.zip.ZipUtil;

//@CrossOrigin("http://localhost:4200")
@RestController
public class FileController {

//	private static final String SERVER_LOCATION = "C:/Users/Niranjan.sutar/Downloads";
	private static final String EXTENSION = ".zip";
	private static final String FILE_NAME = "FAQ";

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() throws IOException {
//		File file = new File(SERVER_LOCATION + File.separator + FILE_NAME + EXTENSION);

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
	
	@GetMapping(value ="/downloadFolderInZipFormate",produces="application/zip")
	public void downloadFolderInZipFormate(HttpServletResponse response) throws IOException 
	{
		String nameGivenToZipForDownload = "MyDownloadResources.zip"; // Extensio is Compoulsory
    	response.addHeader(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=" + nameGivenToZipForDownload);
		File folderToBeZippedAndDownloaded = new File(new File("").getAbsoluteFile()+"\\DownloadableResources\\ResourceOfDocs");
    	ZipUtil.pack(folderToBeZippedAndDownloaded, response.getOutputStream());
    	response.setStatus(HttpServletResponse.SC_OK);

	}
	
}
