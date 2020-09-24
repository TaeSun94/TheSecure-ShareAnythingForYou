package com.ssafy.sharing.application;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.commons.lang3.RandomStringUtils;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String[] uploadFile(MultipartFile[] files) {
		String[] result = new String[files.length];
		try {
			String baseUri = "/home/ubuntu/images/";
			for (int i = 0; i < files.length; i++) {
				String fileName = RandomStringUtils.randomAlphabetic(10) + files[i].getOriginalFilename();
				
				String filePath = baseUri + fileName;
				files[i].transferTo(new File(filePath));
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/resources/")
						.path(fileName).toUriString();
				
				result[i] = fileDownloadUri;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
