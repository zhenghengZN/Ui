package so.bubu.ui.test.mylibrary.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;

import Util.StringUtils;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.item.inputlist.MineViewList;

/**
 * Created by zhengheng on 18/2/26.
 */
public class ItemSelection extends LinearLayout {
    public ItemSelection(Context context) {
        this(context, null);
    }

    private TextView title;
    private MineViewList list;

    public ItemSelection(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.itemselection, this, true);
        title = (TextView) view.findViewById(R.id.item_title);
        list = (MineViewList) view.findViewById(R.id.list);

    }

    public void init(JSONArray objects,String headerTitle) {
//        try {
//            String headerTitle = object.getString("headerTitle");
//            title.setText(headerTitle);
//            JSONArray objects = object.getJSONArray("objects");
////            ArrayList<JSONObject> jsonObjects = MyJsonUtil.JsonArray2JsonObject(objects);
//            list.init(objects);
////            MyJsonUtil.JSONObject2HashMap()
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        list.init(objects);
        if(StringUtils.isNull(headerTitle)){
            title.setText(headerTitle);
        }
    }
}
