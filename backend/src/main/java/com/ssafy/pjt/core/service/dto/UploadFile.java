package com.ssafy.pjt.core.service.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
/**
 * 
 * @author https://github.com/woobong/spring-boot-jpa-summernote-image-upload-example/
 *
 */
@Entity
@Data
public class UploadFile {
    
    @Id
    @GeneratedValue
    int id;

    @Column
    String originFileName;
    
    @Column
    String saveFileName;
    
    @Column
    String filePath;
    
    @Column
    String contentType;
    
    @Column
    long size;
    
    Date regDate;
}