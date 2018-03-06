package so.bubu.ui.test.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Utils.MyJsonUtil;
import so.bubu.ui.test.mylibrary.Toast.ToastDialog;
import so.bubu.ui.test.mylibrary.button.StrokeButton;
import so.bubu.ui.test.mylibrary.msg.MsgView;

/**
 * Created by zhengheng on 18/2/9.
 */
public class SuccessActivity extends Activity {

    private LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        view = findViewById(R.id.parent);

        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PickerDialog pickerDialog = new PickerDialog(SuccessActivity.this);
//                pickerDialog.setPickViewValue(new String[]{"日本","中国","美国"},"美国");
//                pickerDialog.setType(PickValueView.TYPE_PICK_PHONE);
//                pickerDialog.show();

//                HintToastUtil.showToast(SuccessActivity.this, 1000, HintToastUtil.TYPE_LOAD);
                ToastDialog toastDialog = new ToastDialog(SuccessActivity.this, 10 * 1000);
                toastDialog.setType(ToastDialog.TYPE_SUCCESS);
                toastDialog.show();
            }

        });
        view.addView(button);
        getData();
    }

    public void getData() {
        String msg = MyJsonUtil.getJson(this, "getMsg");
        ArrayList<LinkedHashMap<String, Object>> weightList = MyJsonUtil.getWeightList(msg);
        for (LinkedHashMap<String, Object> weight : weightList) {
            String type = (String) weight.get("type");
            JSONArray objects = (JSONArray) weight.get("objects");
            switch (type) {
                case "MsgView":
                    ArrayList<JSONObject> jsonObjects = MyJsonUtil.JsonArray2JsonObject(objects);
                    for (JSONObject object : jsonObjects) {
                        MsgView msgView = new MsgView(this);
                        msgView.init(object);
                        view.addView(msgView);
                    }
                    break;
                case "StrokeButton":
                    ArrayList<JSONObject> jsonObject = MyJsonUtil.JsonArray2JsonObject(objects);
                    for (JSONObject object : jsonObject) {
                        StrokeButton msgView = new StrokeButton(this);
                        msgView.init(object);
                        view.addView(msgView);
                    }
                    break;

//                case "FooterView":
//                    ArrayList<JSONObject> jsonObjec = MyJsonUtil.JsonArray2JsonObject(objects);
//                    for (JSONObject object : jsonObjec) {
//                        FooterView msgView = new FooterView(this);
//                        msgView.init(object);
//                        view.addView(msgView);
//                    }

            }
        }

    }
}
