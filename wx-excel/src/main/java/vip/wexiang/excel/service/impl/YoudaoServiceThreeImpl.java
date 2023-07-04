package vip.wexiang.excel.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.wexiang.excel.domain.Youdao;
import vip.wexiang.excel.service.YoudaoService;

import java.util.ArrayList;
import java.util.List;

@Service("three")
@Slf4j
public class YoudaoServiceThreeImpl implements YoudaoService {

    @Override
    public List<Youdao> getAllYoudao() {
        List<Youdao> list = new ArrayList<>();
        //            珠宝通用
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("3a98d3b6fa96b508").appsecret("sth7u8U3flx02og0dZtOJyc1sS2ctwRT").build());
//            珠宝2
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6b09dd60007c0946").appsecret("dZdHqPQWUhob2WUlSXAE9MlSuTYSUdUW").build());
//            3
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("71df0580007c5a2b").appsecret("hHvZoUKvJ2AT0CkXMGZttsImQI1lpBvi").build());
//            4
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0f135cc0008b7094").appsecret("gZOu0Z8djBcso9h4KlRSAnYUERt7z9H6").build());
//            5
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("02f306b5008bba09").appsecret("d6AjnDuxFR8q0IePHhQ3VZlAfuIqmvJT").build());
//            6
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("78e04fc3008c3aa0").appsecret("PsKk2wedl4AEeM0zogKoxGAIqCoEaGjg").build());
//            7
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6b00d429008c9dd3").appsecret("1PP8kRcjBDJ9s0VWDMFnq6Q95Bk7wzvf").build());
//            8
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("3ee656f9008ce0e6").appsecret("ITzVYpyep2faZ0I0UHfm3Vs9Bma5aDKf").build());
//            9
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6e0eed59008d1334").appsecret("Rx4U9oTLz5jotlx6kKbTNr3WyIL04twE").build());
//            10
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("18ab0027008d42fe").appsecret("GcbFQ69wuVvhGWMrdeDSnjGfz6miTZJH").build());
        //            11
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0fcbc3bd00da64e6").appsecret("uUaqSfZI47BfoXb6EMkokhyuQU4gqlMx").build());
        //            12
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("42065f3200da9171").appsecret("5pJ87KfLhjDw8W0TdsGUUVSiZBEG2GAS").build());
        //            13
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0e61923b00dad6db").appsecret("RT7fKZpdPrqf89urHsjtwl1UUwRdkRO8").build());
        //            14
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("1e4b114600db1a81").appsecret("fFDhArzBazvtlZgz7IFlRbNYm56kgBcz").build());
        //            15
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("07e77b8b00db541f").appsecret("yopOdV6Hj4W7SPP0rY0RnuDWxbx1LEl9").build());
        //            16
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("7d28c03a00db87a3").appsecret("QpruIT9VKuPGFNuJcv3GK10pUzpSNehz").build());
        //            17
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("2d2159bd00e3fd4f").appsecret("xa7S6jTwIvJMTOo5D6GKsQW9RbB3EyKR").build());
        //            18
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("578c491d00e46e7a").appsecret("FeSR8Z6Shx1LZB0rW6qZtOXqqOxefzfa").build());
        //            19
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6c5f32e900e64897").appsecret("GBuL8FW1xe6v90MJ7TYfOuQIHsn68Y2R").build());
        //            20
        list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("62ccf56900e678c4").appsecret("hPG9hMbPow5c8fTMFaO7CAM32YUZdl0H").build());
        return list;
    }
}
