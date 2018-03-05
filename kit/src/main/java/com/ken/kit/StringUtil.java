package com.ken.kit;

import java.util.regex.Pattern;


public class StringUtil {

    public static boolean isEmpty(String line) {
        Pattern pattern = Pattern.compile("\\s*");
        return pattern.matcher(line).matches();
    }
}
