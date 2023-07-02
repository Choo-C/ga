package vip.wexiang.job.txt.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Youdao {
    private String url;
    private String appkey;
    private String appsecret;
}
