package vip.wexiang.job.translate;

import java.io.IOException;

public interface TranslateTwoFile<T> {

    T translate(T t,String fromLanguage,String toLanguage)throws IOException;
    boolean checkYoudao() throws IOException;

}
