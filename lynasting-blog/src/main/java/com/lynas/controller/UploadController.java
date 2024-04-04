package com.lynas.controller;

import com.lynas.domain.R;
import com.lynas.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

  @Autowired
  private UploadService uploadService;

  @PostMapping("/upload")
  public R uploadImg(MultipartFile img) {
    return uploadService.uploadImg(img);
  }
}
