package com.lynas.service.impl;

import com.google.gson.Gson;
import com.lynas.domain.ResponseResult;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import com.lynas.service.UploadService;
import com.lynas.utils.PathUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {

  @Override
  public ResponseResult uploadImg(MultipartFile img) {

    // TODO 判断文件类型及大小
    // 获取文件名，判断出文件类型
    String originalFilename = img.getOriginalFilename();
    if(!originalFilename.endsWith(".png")) {
      throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERR);
    }
    // 获取到文件源路径
    String filePath = PathUtils.generateFilePath(originalFilename);
    // 上传到七牛云
    String url = uploadQiniuOss(img, filePath);
    // 返回
    return ResponseResult.okResult(url);
  }

  private String ak;
  private String sk;
  private String bucket;
  private String cdn;
  public String uploadQiniuOss(MultipartFile imgFile, String filePath) {
    // 构造一个带指定 Region 对象的配置类
    Configuration cfg = new Configuration(Region.autoRegion());
    cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
    UploadManager uploadManager = new UploadManager(cfg);
    // 文件路径
    String key = filePath;
    try {
      InputStream inputStream = imgFile.getInputStream();
      Auth auth = Auth.create(ak, sk);
      String upToken = auth.uploadToken(bucket);
      try {
        Response response = uploadManager.put(inputStream,key,upToken,null, null);
        // 解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
        return cdn + key;
      } catch (QiniuException ex) {
        ex.printStackTrace();
        if (ex.response != null) {
          System.err.println(ex.response);
          try {
            String body = ex.response.toString();
            System.err.println(body);
          } catch (Exception ignored) {
            System.err.println(ignored);
          }
        }
      }
    } catch (Exception ex) {
      //ignore
    }
    return "上传失败啦！";
  }
}