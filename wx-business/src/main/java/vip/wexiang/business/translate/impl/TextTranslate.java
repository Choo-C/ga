package vip.wexiang.business.translate.impl;


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
import vip.wexiang.business.domain.Youdaoapi;
import vip.wexiang.business.translate.TranslateFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TextTranslate implements TranslateFile<String> {

    private  String YOUDAO_URL;
    private  String APP_KEY;
    private  String APP_SECRET;
    public TextTranslate(Youdaoapi youdao){
        this.YOUDAO_URL = youdao.getUrl();
        this.APP_KEY = youdao.getYoudaoKey();
        this.APP_SECRET = youdao.getYoudaoSecret();
    }
    public TextTranslate(){
        YOUDAO_URL = "https://openapi.youdao.com/api";
        APP_KEY = "3a98d3b6fa96b508";
        APP_SECRET = "sth7u8U3flx02og0dZtOJyc1sS2ctwRT";
    }

    @Override
    public boolean checkYoudao() throws IOException {
        Map<String,String> params = setMap("hi","zh-CHS","en");
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

    @Override
    public List<String> removeLanguaNotMatch(String lang) {
        //符合youdao翻译的语言
        List<String> youdaoSupport = new ArrayList<>(Arrays.asList(youdaoSupportString.split(",")));
        List<String> languages = Arrays.stream(lang.split(",")).map(String::trim).collect(Collectors.toList());
//        List<String> languages = new ArrayList<>(Arrays.asList(lang.split(",")));
        // 使用LinkedHashSet删除重复项并保持插入顺序
        Set<String> set = new LinkedHashSet<>(languages);
        // 清空原始列表
        languages.clear();
        // 将Set中的元素添加回列表
        languages.addAll(set);
//        去除不支持的元素
//        languages.retainAll(youdaoSupport);
        languages = languages.stream().filter(youdaoSupport::contains).
            collect(Collectors.toList());
        return languages;
    }

    @Override
    public List<Youdaoapi> getYoudao() {
        //youdao 的list
        List<Youdaoapi> list = new ArrayList<>();
        //            珠宝通用
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("3a98d3b6fa96b508").youdaoSecret("sth7u8U3flx02og0dZtOJyc1sS2ctwRT").build());
//            珠宝2
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("6b09dd60007c0946").youdaoSecret("dZdHqPQWUhob2WUlSXAE9MlSuTYSUdUW").build());
//            3
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("71df0580007c5a2b").youdaoSecret("hHvZoUKvJ2AT0CkXMGZttsImQI1lpBvi").build());
//            4
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("0f135cc0008b7094").youdaoSecret("gZOu0Z8djBcso9h4KlRSAnYUERt7z9H6").build());
//            5
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("02f306b5008bba09").youdaoSecret("d6AjnDuxFR8q0IePHhQ3VZlAfuIqmvJT").build());
//            6
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("78e04fc3008c3aa0").youdaoSecret("PsKk2wedl4AEeM0zogKoxGAIqCoEaGjg").build());
//            7
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("6b00d429008c9dd3").youdaoSecret("1PP8kRcjBDJ9s0VWDMFnq6Q95Bk7wzvf").build());
//            8
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("3ee656f9008ce0e6").youdaoSecret("ITzVYpyep2faZ0I0UHfm3Vs9Bma5aDKf").build());
//            9
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("6e0eed59008d1334").youdaoSecret("Rx4U9oTLz5jotlx6kKbTNr3WyIL04twE").build());
//            10
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("18ab0027008d42fe").youdaoSecret("GcbFQ69wuVvhGWMrdeDSnjGfz6miTZJH").build());
        //            11
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("0fcbc3bd00da64e6").youdaoSecret("uUaqSfZI47BfoXb6EMkokhyuQU4gqlMx").build());
        //            12
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("42065f3200da9171").youdaoSecret("5pJ87KfLhjDw8W0TdsGUUVSiZBEG2GAS").build());
        //            13
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("0e61923b00dad6db").youdaoSecret("RT7fKZpdPrqf89urHsjtwl1UUwRdkRO8").build());
        //            14
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("1e4b114600db1a81").youdaoSecret("fFDhArzBazvtlZgz7IFlRbNYm56kgBcz").build());
        //            15
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("07e77b8b00db541f").youdaoSecret("yopOdV6Hj4W7SPP0rY0RnuDWxbx1LEl9").build());
        //            16
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("7d28c03a00db87a3").youdaoSecret("QpruIT9VKuPGFNuJcv3GK10pUzpSNehz").build());
        //            17
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("2d2159bd00e3fd4f").youdaoSecret("xa7S6jTwIvJMTOo5D6GKsQW9RbB3EyKR").build());
        //            18
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("578c491d00e46e7a").youdaoSecret("FeSR8Z6Shx1LZB0rW6qZtOXqqOxefzfa").build());
        //            19
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("6c5f32e900e64897").youdaoSecret("GBuL8FW1xe6v90MJ7TYfOuQIHsn68Y2R").build());
        //            20
        list.add(Youdaoapi.builder().url("https://openapi.youdao.com/api").youdaoKey("62ccf56900e678c4").youdaoSecret("hPG9hMbPow5c8fTMFaO7CAM32YUZdl0H").build());
        return list;
    }
    private Map<String,String> setMap(String q,String fromLanguage,String toLanguage){
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
//        正常------------------
//        0	    正常回复翻译内容
//        严重------------------
//        401	账户已经欠费，请进行账户充值。不能继续执行
//        小问题----------------
//        411	访问频率受限,请稍后访问
//        206	因为时间戳无效导致签名校验失败
//        query
//        q有值，当回复的是空值，长度为0时，说明不符合中国言论规范，给予提示
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
    //    public static String requestForHttp(String url, Map<String,String> params) throws IOException {
//
//        String returnTranslation = null;
//        /** 创建HttpClient */
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        /** httpPost */
//        HttpPost httpPost = new HttpPost(url);
//        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
//        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
//        while(it.hasNext()){
//            Map.Entry<String,String> en = it.next();
//            String key = en.getKey();
//            String value = en.getValue();
//            paramsList.add(new BasicNameValuePair(key,value));
//        }
//        httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
//
////        字段名	        类型	        含义	            备注
////        errorCode	    text	错误返回码	    一定存在
////        query	        text	源语言	        查询正确时，一定存在
////        translation	Array	翻译结果	        查询正确时，一定存在
////        basic	        text	词义	            基本词典，查词时才有
////        web	        Array	词义	            网络释义，该结果不一定存在
////        l	            text	源语言和目标语言	一定存在
////        dict	        text	词典deeplink	    查询语种为支持语言时，存在
////        webdict	    text	webdeeplink	    查询语种为支持语言时，存在
////        tSpeakUrl	    text	翻译结果发音地址	翻译成功一定存在，需要应用绑定语音合成服务才能正常播放,否则返回110错误码
////        speakUrl	    text	源语言发音地址	    翻译成功一定存在，需要应用绑定语音合成服务才能正常播放,否则返回110错误码
////        returnPhrase	Array	单词校验后的结果	主要校验字母大小写、单词前含符号、中文简繁体
//
////        错误码	含义
////        0	    正常回复翻译内容
////        411	访问频率受限,请稍后访问
////        206	因为时间戳无效导致签名校验失败
////        错误码很多，具体看文档
////                https://ai.youdao.com/DOCSIRMA/html/trans/api/wbfy/index.html
////        正常回复的json字符
////        {
////            "errorCode":"0",
////            "query":"good", //查询正确时，一定存在
////            "isDomainSupport":"true", //翻译结果是否为领域翻译(仅开通领域翻译时存在)
////            "translation": [ //查询正确时一定存在
////            "好"
////  ],
////            "basic":{ // 有道词典-基本词典,查词时才有
////            "phonetic":"gʊd",
////                "uk-phonetic":"gʊd", //英式音标
////                "us-phonetic":"ɡʊd", //美式音标
////                "uk-speech": "XXXX",//英式发音
////                "us-speech": "XXXX",//美式发音
////                "explains":[
////            "好处",
////                "好的",
////                "好",
////      ]
////        },
////            "web":[ // 有道词典-网络释义，该结果不一定存在
////            {
////                "key":"good",
////                "value":["良好","善","美好"]
////            },
////            {...}
////  ],
////            "dict":{
////            "url":"yddict://m.youdao.com/dict?le=eng&q=good"
////        },
////            "webdict":{
////            "url":"http://m.youdao.com/dict?le=eng&q=good"
////        },
////            "l":"EN2zh-CHS",
////            "tSpeakUrl":"XXX",//翻译后的发音地址
////            "speakUrl": "XXX" //查询文本的发音地址
////        }
////        并非每次请求都能正常回复，大多数情况都是411，所以需要反复请求，正常第一次请求都可以执行成功，但是反复请求
//        while(true){
//            log.error("-------");
//            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//            try{
//                Header[] contentType = httpResponse.getHeaders("Content-Type");
//                log.info("Content-Type:" + contentType[0].getValue());
//                if("audio/mp3".equals(contentType[0].getValue())){
//                    //如果响应是wav
//                    HttpEntity httpEntity = httpResponse.getEntity();
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    httpResponse.getEntity().writeTo(baos);
//                    byte[] result = baos.toByteArray();
//                    EntityUtils.consume(httpEntity);
//                    if(result != null){//合成成功
//                        String file = "合成的音频存储路径"+System.currentTimeMillis() + ".mp3";
//                        byte2File(result,file);
//                    }
//                }else{
//                    /** 响应不是音频流，直接显示结果 */
//                    try{
//                        Thread.sleep(350);
//                    }catch (Exception e){
//
//                    }
//                    HttpEntity httpEntity = httpResponse.getEntity();
//                    String json = EntityUtils.toString(httpEntity,"UTF-8");
//                    EntityUtils.consume(httpEntity);
////                log.error(json);
//                    log.info(json);
//                    JSONObject object = new JSONObject(json);
//
//                    if (!"0".equals(object.getString("errorCode"))) {
//                        try{
//                            Thread.sleep(1500);
//                            log.error("超时了");
//                        }catch (Exception e){
//                        }
//                        continue;
//                    }
//                    JSONArray translation = object.getJSONArray("translation");
//                    returnTranslation = translation.getString(0);
//                    log.error(returnTranslation);
//                }
//            }finally {
//                try{
//                    if(httpResponse!=null){
//                        httpResponse.close();
//                    }
//                }catch(IOException e){
//                    log.info("## release resouce error ##" + e);
//                }
//            }
//            return returnTranslation;
//        }
//    }
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

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
}
