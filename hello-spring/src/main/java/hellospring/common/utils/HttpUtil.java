package hellospring.common.utils;

import cn.hutool.core.util.IdUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author admin
 * @version 1.0
 * @description http请求工具类
 * @date 2022/02/08
 */
public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {
    }

    /**
     * @param param
     * @return String
     * @description 发起HttpClient GET请求
     */
    public static String httpClientGetRequest(String param) {
        // 创建HTTP Client实例
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 创建请求方式
        HttpGet httpGet = new HttpGet(param);
        // 设置通用请求头信息
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("参数值", "参数值");
        httpGet.setHeader("参数值", "参数值");
        httpGet.setHeader("参数值", generateStrId());
        httpGet.setHeader("参数值", "参数值");
        httpGet.setHeader("参数值", "参数值");
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(3000).setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        // 获取返回结果
        String result = "";
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            LOGGER.error("发起HttpClient GET请求获取返回结果IOException异常:", e);
        }
        return result;
    }

    /**
     * @param strUrl
     * @param param
     * @return String
     * @description 发起HttpClient POST请求
     */
    public static String httpClientPostRequest(String strUrl, String param) {
        // 创建HTTP Client实例
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 创建请求方式
        HttpPost httpPost = new HttpPost(strUrl);
        // 设置通用请求头信息
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("参数值", "参数值");
        httpPost.setHeader("参数值", "参数值");
        httpPost.setHeader("参数值", generateStrId());
        httpPost.setHeader("参数值", "参数值");
        httpPost.setHeader("参数值", "参数值");
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(3000).setSocketTimeout(5000).build();
        httpPost.setConfig(requestConfig);
        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, ContentType.create("application/json", "utf-8")));
        // 获取返回结果
        String result = "";
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity());
            } else {
                System.out.println(httpResponse.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            LOGGER.error("发起HttpClient POST请求获取返回结果IOException异常:", e);
        }
        return result;
    }

    /**
     * @return String
     * @description 生成随机字符串ID
     */
    private static String generateStrId() {
        return IdUtil.randomUUID();
    }

}
