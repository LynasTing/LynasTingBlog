package com.lynas;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.*;

@SpringBootTest
@Component
// @ConfigurationProperties(prefix = "oss")
public class OSSTest {
  private String ak;
  private String sk;
  private String bucket;

  public void setAk(String ak) {
    this.ak = ak;
  }

  public void setSk(String sk) {
    this.sk = sk;
  }

  public void setBucket(String bucket) {
    this.bucket = bucket;
  }




  @Test
  public void testOss() {
    // 构造一个带指定 Region 对象的配置类
    Configuration cfg = new Configuration(Region.autoRegion());
    cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
    // ...其他参数参考类注释

    UploadManager uploadManager = new UploadManager(cfg);
    // ...生成上传凭证，然后准备上传
    // String accessKey = "";
    // String secretKey = "";
    // String bucket = "";

    // 默认不指定key的情况下，以文件内容的hash值作为文件名
    String key = null;
    try {
      // byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
      // ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);

      InputStream inputStream = new FileInputStream("C:\\Users\\trait\\Desktop\\qiniu-test-img.png");

      Auth auth = Auth.create(ak, sk);
      String upToken = auth.uploadToken(bucket);
      try {
        Response response = uploadManager.put(inputStream,key,upToken,null, null);
        // 解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
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
  }
}
