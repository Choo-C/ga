package vip.wexiang.common.utils.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * JSON工具类
 * Created by wu.
 */
public class JsonUtils {
    public static final Gson mJson;
    public static final Gson mPrettyJson;

    static {
        mJson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        mPrettyJson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .setLenient()
                .create();
    }

    public static <T> T fromJson(String jsonStr, Type typeOfT) {
        return mJson.fromJson(jsonStr, typeOfT);
    }

    public static String toJson(Object obj) {
        return mJson.toJson(obj);
    }

    public static String toPrettyJson(Object obj) {
        return mPrettyJson.toJson(obj);
    }
}
