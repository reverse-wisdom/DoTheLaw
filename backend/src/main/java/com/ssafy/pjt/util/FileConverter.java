package com.ssafy.pjt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileConverter {
	
	/** MultipartFile 객체에서 File 객체를 복사 생성 */
	public static File convert(String path, MultipartFile file) throws IOException {
	    File convFile = new File(path + File.separator + file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
}
