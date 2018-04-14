package so.bubu.ui.test.mylibrary.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Util.MyJsonUtil;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/8.
 */
public class ActionSheet extends Dialog implements AdapterView.OnItemClickListener {

    private ArrayList<String> titles = new ArrayList<>();

    public ActionSheet(Context context) {
        super(context);
    }


    public void init(JSONArray array) {
        ArrayList<JSONObject> jsonObjects = MyJsonUtil.JsonArray2JsonObject(array);
        for (JSONObject object : jsonObjects) {
            HashMap<String, Object> objectHashMap = MyJsonUtil.JSONObject2HashMap(object);
            String title = (String) objectHashMap.get("title");
            titles.add(title);
        }
//        this.titles = titles;
//        adapter.notifyDataSetChanged();
    }

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionsheet);
        ListView list = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.actionsheet_item, R.id.actionSheetTitle, this.titles);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), titles.get(position), Toast.LENGTH_LONG).show();
        dismiss();
    }
}
