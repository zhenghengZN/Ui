package so.bubu.ui.test.myapplication;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Util.MyJsonUtil;
import so.bubu.ui.test.mylibrary.button.StrokeButton;
import so.bubu.ui.test.mylibrary.page.common.BaseActivity;
import so.bubu.ui.test.mylibrary.wiget.TypeTitleView;

public class ButtonActivity extends BaseActivity {


    private View inflate;
    private LinearLayout parent;

    @Override
    public View addBaseContenetView(LinearLayout view) {
        inflate = LayoutInflater.from(this).inflate(R.layout.activity_button, null, false);
        parent = inflate.findViewById(R.id.parent);
        return inflate;
    }

    @Override
    public void doInCreateView() {
        String buttonJson = MyJsonUtil.getJson(this, "getButtons");
        ArrayList<LinkedHashMap<String, Object>> weightList = MyJsonUtil.getWeightList(buttonJson);
        for (LinkedHashMap<String, Object> object : weightList) {
            String type = (String) object.get("type");
            JSONArray objects = (JSONArray) object.get("objects");
            switch (type) {
                case "TypeTitleView":
                    ArrayList<JSONObject> typeTitleObject = MyJsonUtil.JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : typeTitleObject) {
                        TypeTitleView titleview = new TypeTitleView(this);
                        titleview.init(jsonObject);
                        parent.addView(titleview);
                    }
                    break;
                case "StrokeButton":
                    ArrayList<JSONObject> strokebuttonObject = MyJsonUtil.JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : strokebuttonObject) {
                        StrokeButton strokeButton = new StrokeButton(this);
                        strokeButton.init(jsonObject);
                        parent.addView(strokeButton);
                    }
                    break;
            }
        }
    }

    @Override
    protected void doBack(int keyCode, KeyEvent event) {

    }
}
