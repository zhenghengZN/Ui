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
import so.bubu.ui.test.mylibrary.item.inputlist.MineViewLayout;
import so.bubu.ui.test.mylibrary.page.common.BaseActivity;
import so.bubu.ui.test.mylibrary.wiget.TitleView;
import so.bubu.ui.test.mylibrary.wiget.TypeTitleView;

public class ListActivity extends BaseActivity {

    private LinearLayout parent;

    @Override
    public View addBaseContenetView(LinearLayout view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_list, null, false);
        parent = inflate.findViewById(R.id.parent);
        return parent;
    }

    @Override
    public void doInCreateView() {
        String buttonJson = MyJsonUtil.getJson(this, "getList");
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
                case "MineViewList":
                    MineViewLayout list = new MineViewLayout(this);
                    list.init(objects);
                    parent.addView(list);
                    break;

                case "TitleView":
                    ArrayList<JSONObject> titleViewObject = MyJsonUtil.JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : titleViewObject) {
                        TitleView titleview = new TitleView(this);
                        titleview.init(jsonObject);
                        parent.addView(titleview);
                    }
                    break;
            }
        }


    }

    @Override
    protected void doBack(int keyCode, KeyEvent event) {

    }
}
