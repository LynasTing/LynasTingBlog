package com.lynas.service;

import com.lynas.domain.R;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
  R uploadImg(MultipartFile img);
}
