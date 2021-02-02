package com.ssafy.pjt.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt.core.service.dto.UploadFileDTO;

public interface FileRepository extends JpaRepository<UploadFileDTO, Integer> {
    public UploadFileDTO findOneBySaveFileName(String saveFileName);
    public Optional<UploadFileDTO> findById(int id);
}