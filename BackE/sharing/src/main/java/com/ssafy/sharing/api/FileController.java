package com.ssafy.sharing.api;

import java.io.File;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class FileController {
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
    
    @ApiOperation(value = "파일 올리기")
    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if(file == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
			String baseUri = "/home/ubuntu/images/";
            String fileName = RandomStringUtils.randomAlphabetic(10)+file.getOriginalFilename();
			String filePath = baseUri + fileName;
			file.transferTo(new File(filePath));
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/resources/")
                                .path(fileName)
                                .toUriString();
            System.out.println(fileDownloadUri);
            return new ResponseEntity<String>(fileDownloadUri, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
