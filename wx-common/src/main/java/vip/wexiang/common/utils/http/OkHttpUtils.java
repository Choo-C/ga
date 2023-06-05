package vip.wexiang.common.utils.http;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vip.wexiang.common.exception.ServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Http请求工具类
 * Created by wu.
 */
public class OkHttpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);
    private static final OkHttpClient mOkHttpClient;

    static {
        mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();
    }

    /**
     * GET
     *
     * @param url 请求地址
     */
    public static <R> R get(String url, TypeToken<R> resp) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            return parseJson(response, resp);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("请求异常:{}", e.getMessage());
            throw new ServiceException("网络异常，请稍后再试");
        }
    }

    /**
     * GET
     *
     * @param url 请求地址
     */
    public static Response get(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            return mOkHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("请求异常:{}", e.getMessage());
        }
        return null;
    }

    /**
     * POST
     *
     * @param url   请求地址
     * @param query Query参数
     */
    public static Response post(String url, String query) {
        String mediaType = "application/x-www-form-urlencoded";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Accept", "*/*");
        return post(url, mediaType, headers, query);
    }

    /**
     * POST
     *
     * @param url       请求地址
     * @param mediaType 媒体格式
     * @param headers   请求头
     * @param params    请求参数
     */
    public static Response post(String url, String mediaType, Map<String, String> headers, String params) {
        LOGGER.info("请求地址:{}, 请求参数:{}", url, params);
        MediaType type = MediaType.parse(mediaType);
        RequestBody requestBody = RequestBody.create(type, params);
        Request.Builder post = new Request.Builder().url(url).post(requestBody);
        if (headers != null && headers.size() != 0) {
            Headers.Builder header = new Headers.Builder();
            for (Map.Entry<String, String> next : headers.entrySet()) {
                header.add(next.getKey(), next.getValue());
            }
            post.headers(header.build());
        }
        try {
            return mOkHttpClient.newCall(post.build()).execute();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("请求异常:{}", e.getMessage());
            throw new ServiceException("网络异常，请稍后再试");
        }
    }

    /**
     * POST
     *
     * @param url  请求地址
     * @param body Body参数
     * @param resp 响应实体
     */
    public static <R> R post(String url, String body, TypeToken<R> resp) {
        String mediaType = "application/json; charset=utf-8";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return post(url, mediaType, headers, body, resp);
    }

    /**
     * POST
     *
     * @param url   请求地址
     * @param token Token
     * @param body  Body参数
     * @param resp  响应实体
     */
    public static <R> R post(String url, String token, String body, TypeToken<R> resp) {
        String mediaType = "application/json; charset=utf-8";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return post(url, mediaType, headers, body, resp);
    }

    /**
     * POST
     *
     * @param url       请求地址
     * @param mediaType 媒体格式
     * @param headers   请求头
     * @param params    请求参数
     * @param resp      响应实体
     */
    public static <R> R post(String url, String mediaType, Map<String, String> headers, String params, TypeToken<R> resp) {
        Response response = post(url, mediaType, headers, params);
        return parseJson(response, resp);
    }

    /**
     * 解析JSON数据
     *
     * @param response 响应
     * @param resp     响应实体
     */
    private static <R> R parseJson(Response response, TypeToken<R> resp) {
        R result = null;
        try {
            if (response.body() != null) {
                String body = response.body().string();
                LOGGER.info("响应原始数据:{}", body);
                result = JsonUtils.fromJson(body, resp.getType());
            }
            LOGGER.info("响应解析数据:{}", result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("请求异常:{}", e.getMessage());
            throw new ServiceException("网络异常，请稍后再试");
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            LOGGER.info("解析异常:{}", e.getMessage());
            throw new ServiceException("网络异常，请稍后再试");
        }
    }

}
