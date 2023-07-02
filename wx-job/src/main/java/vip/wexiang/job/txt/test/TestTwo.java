package vip.wexiang.job.txt.test;

import cn.hutool.core.util.ObjectUtil;

import java.util.*;
import java.util.stream.Collectors;

public class TestTwo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("123", "asdwqe");
        map.put("333", "asdwqe");
        System.out.println(map.get("123"));
        System.out.println(ObjectUtil.isNull(map.get("12er")));
        Integer integer = new Integer(1000);
        Integer integer1 = new Integer(1000);
        System.out.println(integer1 == integer1);
        String youdaoSupportString = "zh-CHS,zh-CHT,en,ja,ko,fr,es,pt,it,ru,vi,de,ar,id,af,bs,bg,yue,ca,hr,cs,da,nl,et,fj,fi,el,ht,he,hi,mww,hu,sw,tlh,lv,lt,ms,mt,no,fa,pl,otq,ro,sr-Cyrl,sr-Latn,sk,sl,sv,ty,th,to,tr,uk,ur,cy,yua,sq,am,hy,az,bn,eu,be,ceb,co,eo,tl,fy,gl,ka,gu,ha,haw,is,ig,ga,jw,kn,kk,km,ku,ky,lo,la,lb,mk,mg,ml,mi,mr,mn,my,ne,ny,ps,pa,sm,gd,st,sn,sd,si,so,su,tg,ta,te,uz,xh,yi,yo,zu";
        List<String> youdaoSupport = Arrays.asList(youdaoSupportString.split(","));
//        String lang = "en,pt,ja,ko,hi,fr,de,vi,ms,pl,ar,it,ar,ta,sv,fi,id,th";
//        List<String> languages = Arrays.asList(lang.split(","));


        String lang = "en,pt,ja,ko,hi,fr,de,vi,ms,pl,ar,it,,,, ar , ta, sv ,fi,id,,,,,,QWE ,th";
//        List<String> languages = new ArrayList<>(Arrays.asList(lang.split(",")));
        List<String> languages = Arrays.stream(lang.split(",")).map(String::trim).collect(Collectors.toList());
        System.out.println(languages.size());
        System.out.println(languages);
// 使用LinkedHashSet删除重复项并保持插入顺序
        Set<String> set = new LinkedHashSet<>(languages);
// 清空原始列表
        languages.clear();
// 将Set中的元素添加回列表
        languages.addAll(set);



        System.out.println(languages.size());
        System.out.println(languages);



//        for (Iterator<String> car = languages.iterator(); car.hasNext();){
//            String next = car.next();
//            if (!youdaoSupport.contains(next)){
//                car.remove();
//            }
//        }
//        languages.retainAll(youdaoSupport);
        languages = languages.stream()
            .filter(youdaoSupport::contains)
            .collect(Collectors.toList());

        languages.forEach(System.out::println);
        System.out.println(languages.size());
//        System.out.println(youdaoSupport.size());
        System.out.println("   ".replace(" ","").equals(""));
        StringBuilder log = new StringBuilder("");


    }
}
