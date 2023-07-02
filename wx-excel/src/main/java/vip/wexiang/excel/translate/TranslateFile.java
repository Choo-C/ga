package vip.wexiang.excel.translate;

import java.io.IOException;

public interface TranslateFile<T> {

    T translate(T t,String fromLanguage,String toLanguage)throws IOException;

}
