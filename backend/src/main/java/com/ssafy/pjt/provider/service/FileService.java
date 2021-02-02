package com.ssafy.pjt.provider.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.models.Response;

public class FileService {
//@Autowired
//FileRepository fileRepository;
//public Response uploadFile(MultipartHttpServletRequest request) throws  IOException {
//    
//    Response response = new Response();
//    List fileList = new ArrayList();
//    
//    Iterator<String> itr = request.getFileNames();
//    
//    while (itr.hasNext()) {
//        String uploadedFile = itr.next();
//        MultipartFile file = request.getFile(uploadedFile);
//        String mimeType = file.getContentType();
//        String filename = file.getOriginalFilename();
//        byte[] bytes = file.getBytes();
//
//        File newFile = new File(filename, bytes, mimeType);
//        File savedFile = fileRepository.saveAndFlush(newFile);
//        savedFile.setFile(null);
//        fileList.add(savedFile);
//    }
//    
//    response.setReport(fileList);
//    return response;
//}
}
