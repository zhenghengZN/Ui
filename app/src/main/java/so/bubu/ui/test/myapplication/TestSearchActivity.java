package so.bubu.ui.test.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Utils.MyJsonUtil;
import so.bubu.ui.test.mylibrary.page.SeachActivity;

/**
 * Created by zhengheng on 18/1/22.
 */
public class TestSearchActivity extends SeachActivity {


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);



    }

    @Override
    public List<String> setHotStrings() {
        ArrayList<String> strings = new ArrayList<>();
        String hotSearch = MyJsonUtil.getJson(this, "HotSearch");
        try {
            JSONObject jsonObject = new JSONObject(hotSearch);
            JSONArray array = jsonObject.getJSONArray("hotSearch");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject1 = array.getJSONObject(i);
                String hotkey = jsonObject1.getString("hotkey");
                strings.add(hotkey);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
