package hellospring.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hellospring.common.constants.DesConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class DESUtil {

    private DESUtil() {
    }

    public static String httpClientGetRequest(String jsonStr) throws Exception {
        String encodeStr = getEncodeStr(jsonStr);
        // 创建HTTP Client实例
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 创建请求方式
        HttpGet httpGet = new HttpGet(encodeStr);
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
            e.printStackTrace();
        }
        return decodeStr(result);
    }

    private static String generateStrId() {
        Long random = ThreadLocalRandom.current().nextLong(1000, 9999);
        Long localDate = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));
        Long id = localDate + random;
        return String.valueOf(id);
    }

    private static String getEncodeStr(String param) throws Exception {
        return encode("私钥".getBytes(StandardCharsets.UTF_8), param);
    }

    /**
     * @param data
     * @return String
     * @throws Exception
     * @description 解密后数据
     */
    private static String decodeStr(String data) throws Exception {
        JSONObject jsonObject = JSON.parseObject(data);
        if (jsonObject.get("status").equals(0)) {
            return dencode("私钥".getBytes(StandardCharsets.UTF_8), String.valueOf(jsonObject.get("data")));
        } else {
            return String.valueOf(jsonObject.get("info"));
        }
    }

    private static String encode(byte[] key, String data) throws Exception {
        byte[] buff = DesConstant.des3_encrypt(key, data.getBytes("utf-8"));
        return Base64.encodeBase64String(buff);
    }

    private static String dencode(byte[] key, String data) throws Exception {
        byte[] buff = Base64.decodeBase64(data);
        buff = DesConstant.des3_decrypt(key, buff);
        return new String(buff, "utf-8");
    }

}
