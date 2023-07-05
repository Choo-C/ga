package vip.wexiang.business.translate;

import vip.wexiang.business.domain.Youdaoapi;


import java.io.IOException;
import java.util.List;

public interface TranslateFile<T> {
    String youdaoSupportString = "zh-CHS,zh-CHT,en,ja,ko,fr,es,pt,it,ru,vi,de,ar,id,af,bs,bg,yue,ca,hr,cs,da,nl,et,fj,fi,el,ht,he,hi,mww,hu,sw,tlh,lv,lt,ms,mt,no,fa,pl,otq,ro,sr-Cyrl,sr-Latn,sk,sl,sv,ty,th,to,tr,uk,ur,cy,yua,sq,am,hy,az,bn,eu,be,ceb,co,eo,tl,fy,gl,ka,gu,ha,haw,is,ig,ga,jw,kn,kk,km,ku,ky,lo,la,lb,mk,mg,ml,mi,mr,mn,my,ne,ny,ps,pa,sm,gd,st,sn,sd,si,so,su,tg,ta,te,uz,xh,yi,yo,zu";
    T translate(T t,String fromLanguage,String toLanguage)throws IOException;
    List<String> removeLanguaNotMatch(String lang);
    List<Youdaoapi> getYoudao();
    boolean checkYoudao() throws IOException;


}
