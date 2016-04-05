package com.mobanker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;

public class OSSUpLoadClientApp
{

    private OSSClient ossClient;
    private boolean   isOSSOn = false;
    private String    bucketImg;

    public OSSUpLoadClientApp()
    {
        this.isOSSOn = true;
        this.bucketImg = "test-protocol";
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com/";
        String accessKeyId = "Ozjct8fDW7CU87Zz";
        String accessKeySecret = "e9zcheXWf5zB2HeZxhZjo63jvJCyCE";
        this.ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public PutObjectResult putObject(String objId, String filePath) throws FileNotFoundException
    {
        PutObjectResult result = this.putObject(this.bucketImg, objId, filePath);
        return result;
    }

    public PutObjectResult putObject(String bucketName, String objId, String filePath) throws FileNotFoundException
    {
        if (!isOSSOn)
        {
            return null;
        }
        // 获取指定文件的输入流
        File file = new File(filePath);
        InputStream content = new FileInputStream(file);

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(file.length());

        // 上传Object.
        PutObjectResult result = ossClient.putObject(bucketName, objId, content, meta);

        // 打印ETag
        System.out.println(result.getETag());
        return result;
    }
}
