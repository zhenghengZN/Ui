package Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.LinkedHashSet;
import java.util.Set;

import so.bubu.ui.test.mylibrary.page.common.BaseApplication;

/**
 * Created by zhengheng on 18/1/22.
 */
public class SPUtil {


    public static final String HISTORY = "historyKey";
    private static Context context = BaseApplication.getInstance();
    private static SharedPreferences sp = context.getSharedPreferences("history", context.MODE_PRIVATE);

    public static void save(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static void save(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static void saveInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static void saveSet(String key, Set<String> set) {
        sp.edit().putStringSet(key, set).apply();
    }

    public static Set<String> getSet(String key) {
        return sp.getStringSet(key, new LinkedHashSet<String>());
    }

    public static String get(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static String getString(String key) {
        return sp.getString(key, "");
    }

    public static int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean def) {

        return sp.getBoolean(key, def);
    }

    public void saveHistoryValue(String text) {
//        String text = mKeywordEt.getText().toString();
        String oldText = sp.getString(HISTORY, "");
        StringBuilder builder = new StringBuilder(text);
        if (oldText.length() < 1) {
            return;
        }
        builder.append("," + oldText);
        if (!TextUtils.isEmpty(text) && !oldText.contains(text + ",")) {
            SharedPreferences.Editor myEditor = sp.edit();
            myEditor.putString(HISTORY, builder.toString());
            myEditor.commit();
        }
//        updateData();
    }


    public void getHistoryValue() {

    }

    public static void clearSharedPreferences() {
        sp.edit().clear().apply();
    }
}

