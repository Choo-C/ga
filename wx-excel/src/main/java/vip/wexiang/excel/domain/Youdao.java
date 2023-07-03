package vip.wexiang.excel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import vip.wexiang.excel.translate.impl.TextTranslate;


import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@Getter
@Setter
public class Youdao {
    private String url;
    private String appkey;
    private String appsecret;
    public static List<String> removeLanguaNotMatch(String lang) {
        //符合youdao翻译的语言
        String youdaoSupportString = "zh-CHS,zh-CHT,en,ja,ko,fr,es,pt,it,ru,vi,de,ar,id,af,bs,bg,yue,ca,hr,cs,da,nl,et,fj,fi,el,ht,he,hi,mww,hu,sw,tlh,lv,lt,ms,mt,no,fa,pl,otq,ro,sr-Cyrl,sr-Latn,sk,sl,sv,ty,th,to,tr,uk,ur,cy,yua,sq,am,hy,az,bn,eu,be,ceb,co,eo,tl,fy,gl,ka,gu,ha,haw,is,ig,ga,jw,kn,kk,km,ku,ky,lo,la,lb,mk,mg,ml,mi,mr,mn,my,ne,ny,ps,pa,sm,gd,st,sn,sd,si,so,su,tg,ta,te,uz,xh,yi,yo,zu";
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
    public static void checkyoudaoAll(List<Youdao> youdaos) throws Exception {
        for (Iterator<Youdao> car = youdaos.iterator(); car.hasNext(); ) {
            Youdao you = car.next();
            TextTranslate translate = new TextTranslate(you);
            boolean valid = translate.checkYoudao();
            if (!valid) {
                car.remove();
            }
        }
    }
}
