package org.wade.mbus.worker.service.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageUtil {
    public static boolean isChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find())
            flg = true;

        return flg;
    }
}
