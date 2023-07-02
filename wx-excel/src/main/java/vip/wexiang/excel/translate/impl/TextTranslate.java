package vip.wexiang.excel.translate.impl;


import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
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
import vip.wexiang.excel.domain.Youdao;
import vip.wexiang.excel.translate.TranslateFile;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Component
public class TextTranslate implements TranslateFile<String> {

//    private static final String YOUDAO_URL = "https://openapi.youdao.com/api";
    private  String YOUDAO_URL;
//    private static final String APP_KEY = "3a98d3b6fa96b508";
    private  String APP_KEY;

//    private static final String APP_SECRET = "sth7u8U3flx02og0dZtOJyc1sS2ctwRT";
    private  String APP_SECRET;
    public TextTranslate(Youdao youdao){
        this.YOUDAO_URL = youdao.getUrl();
        this.APP_KEY = youdao.getAppkey();
        this.APP_SECRET = youdao.getAppsecret();
    }
    public TextTranslate(){
        YOUDAO_URL = "https://openapi.youdao.com/api";
        APP_KEY = "3a98d3b6fa96b508";
        APP_SECRET = "sth7u8U3flx02og0dZtOJyc1sS2ctwRT";
    }

    @Override
    public String translate(String q,String fromLanguage,String toLanguage) throws IOException{
        //拼装youdao api需要的字段
        Map<String,String> params = new HashMap<String,String>();
        if (ObjectUtil.isNull(q)||ObjectUtil.isEmpty(q)){
            return "请保证左侧原文不能为空，否则无法翻译，同时保证原文字段的右测的字段无任何字符。";
        }
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
        /** 处理结果 */
        String tra = requestForHttp(YOUDAO_URL, params);
        return tra;
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

//        字段名	        类型	        含义	            备注
//        errorCode	    text	错误返回码	    一定存在
//        query	        text	源语言	        查询正确时，一定存在
//        translation	Array	翻译结果	        查询正确时，一定存在
//        basic	        text	词义	            基本词典，查词时才有
//        web	        Array	词义	            网络释义，该结果不一定存在
//        l	            text	源语言和目标语言	一定存在
//        dict	        text	词典deeplink	    查询语种为支持语言时，存在
//        webdict	    text	webdeeplink	    查询语种为支持语言时，存在
//        tSpeakUrl	    text	翻译结果发音地址	翻译成功一定存在，需要应用绑定语音合成服务才能正常播放,否则返回110错误码
//        speakUrl	    text	源语言发音地址	    翻译成功一定存在，需要应用绑定语音合成服务才能正常播放,否则返回110错误码
//        returnPhrase	Array	单词校验后的结果	主要校验字母大小写、单词前含符号、中文简繁体

//        错误码	含义
//        0	    正常回复翻译内容
//        411	访问频率受限,请稍后访问
//        206	因为时间戳无效导致签名校验失败
//        错误码很多，具体看文档
//                https://ai.youdao.com/DOCSIRMA/html/trans/api/wbfy/index.html
//        正常回复的json字符
//        {
//            "errorCode":"0",
//            "query":"good", //查询正确时，一定存在
//            "isDomainSupport":"true", //翻译结果是否为领域翻译(仅开通领域翻译时存在)
//            "translation": [ //查询正确时一定存在
//            "好"
//  ],
//            "basic":{ // 有道词典-基本词典,查词时才有
//            "phonetic":"gʊd",
//                "uk-phonetic":"gʊd", //英式音标
//                "us-phonetic":"ɡʊd", //美式音标
//                "uk-speech": "XXXX",//英式发音
//                "us-speech": "XXXX",//美式发音
//                "explains":[
//            "好处",
//                "好的",
//                "好",
//      ]
//        },
//            "web":[ // 有道词典-网络释义，该结果不一定存在
//            {
//                "key":"good",
//                "value":["良好","善","美好"]
//            },
//            {...}
//  ],
//            "dict":{
//            "url":"yddict://m.youdao.com/dict?le=eng&q=good"
//        },
//            "webdict":{
//            "url":"http://m.youdao.com/dict?le=eng&q=good"
//        },
//            "l":"EN2zh-CHS",
//            "tSpeakUrl":"XXX",//翻译后的发音地址
//            "speakUrl": "XXX" //查询文本的发音地址
//        }
//        并非每次请求都能正常回复，大多数情况都是411，所以需要反复请求，正常第一次请求都可以执行成功，但是反复请求
        while(true){
            log.error("-------");
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try{
                Header[] contentType = httpResponse.getHeaders("Content-Type");
                log.info("Content-Type:" + contentType[0].getValue());
                if("audio/mp3".equals(contentType[0].getValue())){
                    //如果响应是wav
                    HttpEntity httpEntity = httpResponse.getEntity();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    httpResponse.getEntity().writeTo(baos);
                    byte[] result = baos.toByteArray();
                    EntityUtils.consume(httpEntity);
                    if(result != null){//合成成功
                        String file = "合成的音频存储路径"+System.currentTimeMillis() + ".mp3";
                        byte2File(result,file);
                    }
                }else{
                    /** 响应不是音频流，直接显示结果 */
                    try{
                        Thread.sleep(350);
                    }catch (Exception e){

                    }
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String json = EntityUtils.toString(httpEntity,"UTF-8");
                    EntityUtils.consume(httpEntity);
//                log.error(json);
                    log.info(json);
                    JSONObject object = new JSONObject(json);

                    if (!"0".equals(object.getString("errorCode"))) {
                        try{
                            Thread.sleep(1500);
                            log.error("超时了");
                        }catch (Exception e){
                        }
                        continue;
                    }
                    JSONArray translation = object.getJSONArray("translation");
                    returnTranslation = translation.getString(0);
                    log.error(returnTranslation);
                }
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
