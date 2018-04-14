package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * stringUtils
 * Created by Auro on 8/20/15.
 */
public class StringUtils {

    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");


    public static boolean isNull(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return true;
    }

//    public static boolean isBnull(Boolean s){
//        if()
//    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @return boolean
     */
    public static boolean isEmail(String email) {
        return !(email == null || email.trim().length() == 0) && emailer.matcher(email).matches();
    }

    public static String subContactString(String source) {

        int start = source.indexOf("[");
        int end = source.indexOf("]");
        String buffstr = source.substring(start + 1, end);
        if (Helper.isNotEmpty(buffstr)) {
            String target = buffstr.substring(1, buffstr.length() - 1);
            return target;
        }

        return buffstr;

    }


//    public static List<String> json2Array(String source) {
//
//        if (Helper.isNotEmpty(source)) {
//            List<String> strings = JSON.parseArray(source, String.class);
//
//            return strings;
//        }
//
//        return null;
//
//
//    }

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern
                .compile("^(13[0-9]|15[0-9]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static boolean checkQQ(String s) {
        Pattern pattern = Pattern
                .compile("[1-9][0-9]{4,14}");
        Matcher matcher = pattern.matcher(s);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    //截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public static int getNumber(String content) {

        String numbers = getNumbers(content);

        try {
            int i = Integer.parseInt(numbers);
            return i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return 0;

    }

    //半角字符转全角字符
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }


}
