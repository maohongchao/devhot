package com.mobanker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;
import com.mobanker.conf.AppConstants;
import com.mobanker.intf.SystemVariableIntf;
import com.mobanker.intf.impl.SystemVariableIntfImpl;

public class OSSUpLoadClient
{
    private final Logger       logger  = Logger.getLogger(getClass());
    private OSSClient          ossClient;
    private boolean            isOSSOn = false;
    private String             endpoint;
    private String             agreementBucket;

    private SystemVariableIntf systemVariableIntf;

    private OSSUpLoadClient()
    {
        this.isOSSOn = true;
    }

    private static class OSSUpLoadClientHolder
    {
        private static OSSUpLoadClient client = new OSSUpLoadClient();
    }

    /**
     * <b>Description:</b>〈仅返回同一个上传客户端实例〉<br/>
     * @return OSSUpLoadClient
     */
    public static OSSUpLoadClient getInstance()
    {
        OSSUpLoadClient ossClient = OSSUpLoadClientHolder.client;
        if (ossClient.systemVariableIntf == null)
        {
            synchronized (OSSUpLoadClient.class)
            {
                if (ossClient.systemVariableIntf == null)
                {
                    ossClient.systemVariableIntf = new SystemVariableIntfImpl();
                    ossClient.initOssClient();
                }
            }
        }
        return ossClient;
    }

    /**
     * <b>Description:</b>〈初始化阿里云OSSClient的key值〉<br/>
     * @author hongchaoMao <br/>
     */
    private void initOssClient()
    {
        Map<String, String> config = systemVariableIntf.getSys(new String[] { AppConstants.CON_BORROW_AGREEMENT_BUCKET,
                AppConstants.CON_ALIYUN_END_POINT, AppConstants.CON_ALIYUN_ACCESS_KEY_ID,
                AppConstants.CON_ALIYUN_ACCESS_KEY_SECRET });
        if (StringUtils.isNotBlank(config.get(AppConstants.CON_ALIYUN_END_POINT)))
        {
            endpoint = config.get(AppConstants.CON_ALIYUN_END_POINT);
        }
        else
        {
            logger.error("------>initOssClient() error: The system variable '" + AppConstants.CON_ALIYUN_END_POINT
                    + "' not config !");
            endpoint = "http://oss-cn-hangzhou.aliyuncs.com/";
        }
        if (StringUtils.isNotBlank(config.get(AppConstants.CON_BORROW_AGREEMENT_BUCKET)))
        {
            agreementBucket = config.get(AppConstants.CON_BORROW_AGREEMENT_BUCKET);
        }
        else
        {
            logger.error("------>initOssClient() error: The system variable '"
                    + AppConstants.CON_BORROW_AGREEMENT_BUCKET + "' not config !");
            agreementBucket = "test-protocol";
        }
        String accessKeyId;
        if (StringUtils.isNotBlank(config.get(AppConstants.CON_ALIYUN_ACCESS_KEY_ID)))
        {
            accessKeyId = config.get(AppConstants.CON_ALIYUN_ACCESS_KEY_ID);
        }
        else
        {
            logger.error("------>initOssClient() error: The system variable '" + AppConstants.CON_ALIYUN_ACCESS_KEY_ID
                    + "' not config !");
            accessKeyId = "Ozjct8fDW7CU87Zz";
        }
        String accessKeySecret;
        if (StringUtils.isNotBlank(config.get(AppConstants.CON_ALIYUN_ACCESS_KEY_SECRET)))
        {
            accessKeySecret = config.get(AppConstants.CON_ALIYUN_ACCESS_KEY_SECRET);
        }
        else
        {
            logger.error("------>initOssClient() error: The system variable '"
                    + AppConstants.CON_ALIYUN_ACCESS_KEY_SECRET + "' not config !");
            accessKeySecret = "e9zcheXWf5zB2HeZxhZjo63jvJCyCE";
        }
        this.ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public PutObjectResult putObject(String objId, String filePath) throws FileNotFoundException
    {
        PutObjectResult result = this.putObject(this.agreementBucket, objId, filePath);
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
        meta.setContentEncoding("UTF-8");
        meta.setContentType("application/pdf");

        // 上传Object.
        PutObjectResult result = ossClient.putObject(bucketName, objId, content, meta);

        // 打印ETag
        logger.debug("------->putObject() ETag: " + result.getETag());
        return result;
    }

    public String getEndpoint()
    {
        if (endpoint.lastIndexOf("/") != endpoint.length() - 1)
        {
            return endpoint + "/";
        }
        return endpoint;
    }

    public String getAgreementBucket()
    {
        return agreementBucket;
    }
}
