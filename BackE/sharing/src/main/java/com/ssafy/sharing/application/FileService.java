package com.ssafy.sharing.application;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.sharing.domain.Host;

public interface FileService {
	public String[] uploadFile(MultipartFile[] files);
}
