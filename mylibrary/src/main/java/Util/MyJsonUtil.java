package Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by zhengheng on 17/12/7.
 */
public class MyJsonUtil {

    public static String getJson(Context mContext, String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        Log.e("zhengheng", sb.toString().trim());
        return sb.toString().trim();
    }


    public static ArrayList<LinkedHashMap<String, Object>> getWeightList(String Json) {
        ArrayList<LinkedHashMap<String, Object>> array = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(Json);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if ("widgets".equalsIgnoreCase(key)) {
                    JSONArray jsonArray = jsonObject.getJSONArray(key);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Iterator<String> keys1 = jsonObject1.keys();
                            LinkedHashMap<String, Object> weights = new LinkedHashMap<>();
                            while (keys1.hasNext()) {
                                String key1 = keys1.next();
                                Object o = jsonObject1.get(key1);
                                weights.put(key1, o);
                                //保存了所有控件, 去除控件红的object,内部可能会有不同
//                    SparseArray
                            }
                            array.add(weights);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if ("hasMore".equalsIgnoreCase(key)) {

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }


    public static ArrayList<JSONObject> JsonArray2JsonObject(JSONArray objects) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < objects.length(); i++) {
            try {
                JSONObject jsonObject = objects.getJSONObject(i);
                jsonObjects.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }


    public static HashMap<String, Object> JSONObject2HashMap(JSONObject object) {
        Iterator<String> keys = object.keys();
        HashMap<String, Object> map = new HashMap();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = null;
            try {
                value = object.get(key);
                map.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
