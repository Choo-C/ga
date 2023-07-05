package vip.wexiang.job.translate.impl;


import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import vip.wexiang.job.translate.TranslateTwoFile;
import vip.wexiang.job.txt.domain.Youdao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Component
public class TextTranslateTwo implements TranslateTwoFile<String> {

//    private static final String YOUDAO_URL = "https://openapi.youdao.com/api";
    private  String YOUDAO_URL;
//    private static final String APP_KEY = "3a98d3b6fa96b508";
    private  String APP_KEY;

//    private static final String APP_SECRET = "sth7u8U3flx02og0dZtOJyc1sS2ctwRT";
    private  String APP_SECRET;
    public TextTranslateTwo(Youdao youdao){
        this.YOUDAO_URL = youdao.getUrl();
        this.APP_KEY = youdao.getAppkey();
        this.APP_SECRET = youdao.getAppsecret();
    }
    public TextTranslateTwo(){
        YOUDAO_URL = "https://openapi.youdao.com/api";
        APP_KEY = "3a98d3b6fa96b508";
        APP_SECRET = "sth7u8U3flx02og0dZtOJyc1sS2ctwRT";
    }

    @Override
    public  boolean checkYoudao() throws IOException {
        Map<String,String> params = setMap("hi","zh-CHS","en");
        String returnTranslation = null;
        /** 创建HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /** httpPost */
        HttpPost httpPost = new HttpPost(YOUDAO_URL);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key,value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
        int i = 0;
        while (true){
            try {
                Thread.sleep(400);
            }catch (    Exception e){
                e.printStackTrace();
            }

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String json = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
            log.info(json);
            JSONObject object = new JSONObject(json);
            if ("0".equals(object.getString("errorCode"))) {
                return true;
            }
            if (Arrays.asList("101","105","108","111","206", "401", "310","202").contains(object.getString("errorCode"))) {
                return false;
            }
            if(i++ > 10){
                break;
            }
        }

        return false;
    }

    @Override
    public String translate(String q,String fromLanguage,String toLanguage) throws IOException{

        if (ObjectUtil.isNull(q)||ObjectUtil.isEmpty(q)){
            return "请保证左侧原文不能为空，否则无法翻译，同时保证原文字段的右测的字段无任何字符。";
        }
        Map<String,String> params = setMap(q,fromLanguage,toLanguage);

        /** 处理结果 */
        String tra = requestForHttp(YOUDAO_URL, params);
        return tra;
    }
    private Map<String,String>  setMap(String q,String fromLanguage,String toLanguage){
        Map<String,String> params = new HashMap<String,String>();
        log.info(toLanguage);
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("from", "zh-CHS");
        params.put("to", toLanguage);
        params.put("signType", "v3");
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = APP_KEY + truncate(q) + salt + curtime + APP_SECRET;
        String sign = getDigest(signStr);
        params.put("appKey", APP_KEY);
        params.put("q", q);
        params.put("salt", salt);
        params.put("sign", sign);
//        params.put("vocabId","您的用户词表ID");

        return params;
    }
    public static String requestForHttp(String url, Map<String,String> params) throws IOException {

        String returnTranslation = null;
        /** 创建HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /** httpPost */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key,value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
//        int qwe = 0;
        while(true){
            log.error("-------");
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try{
                    try{
                        Thread.sleep(400);
                    }catch (Exception e){

                    }
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String json = EntityUtils.toString(httpEntity,"UTF-8");
                    EntityUtils.consume(httpEntity);
//                log.error(json);
                    log.info(json);
                    JSONObject object = new JSONObject(json);

                    if (!"0".equals(object.getString("errorCode"))) {
                        if ("401".equals(object.getString("errorCode"))) {
                            return "<youdao>401</youdao>";
                        }
                        try{
                            Thread.sleep(1500);
                            log.error("超时了");
                        }catch (Exception e){
                        }
//                        if (qwe>15){
//                            return "--------------------------翻译失败";
//                        }
//                        qwe++;
                        continue;
                    }
                    JSONArray translation = object.getJSONArray("translation");
                    returnTranslation = translation.getString(0);
                    log.error(returnTranslation);

            }finally {
                try{
                    if(httpResponse!=null){
                        httpResponse.close();
                    }
                }catch(IOException e){
                    log.info("## release resouce error ##" + e);
                }
            }
            return returnTranslation;
        }
    }
    /**
     * 生成加密字段
     */
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     *
     * @param result 音频字节流
     * @param file 存储路径
     */
    private static void byte2File(byte[] result, String file) {
        File audioFile = new File(file);
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(audioFile);
            fos.write(result);

        }catch (Exception e){
            log.info(e.toString());
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
}
