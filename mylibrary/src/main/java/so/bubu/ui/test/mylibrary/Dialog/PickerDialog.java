package so.bubu.ui.test.mylibrary.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Utils.MyJsonUtil;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.wiget.pickTimeView.PickTimeView;
import so.bubu.ui.test.mylibrary.wiget.pickTimeView.PickValueView;

/**
 * Created by zhengheng on 18/2/9.
 */
public class PickerDialog extends Dialog implements PickTimeView.onSelectedChangeListener, PickValueView.onSelectedChangeListener {
    public PickerDialog(Context context) {
        super(context);
    }

    private PickTimeView pickDate, pickTime;
    private PickValueView pickPhone;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picktime);


        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);

        TextView cancle = (TextView) findViewById(R.id.cancle);
        TextView success = (TextView) findViewById(R.id.sucess);

        pickDate = (PickTimeView) findViewById(R.id.pickDate);
        pickTime = (PickTimeView) findViewById(R.id.pickTime);
        pickPhone = (PickValueView) findViewById(R.id.pickPhone);
        pickDate.setOnSelectedChangeListener(this);
        pickTime.setOnSelectedChangeListener(this);
        pickPhone.setOnSelectedChangeListener(this);

        setPickView(type);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.sucessClick(date, v);
                }
                dismiss();
            }
        });

    }

    @Override
    public void show() {
        super.show();

    }


    private String[] phoneVaule;
    private Object defaultValue;

    public void setPickViewValue(JSONArray array, Object defaultValue) {
//        pickPhone.setValueData(phoneVaule, defaultValue);
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<JSONObject> jsonObjects = MyJsonUtil.JsonArray2JsonObject(array);
        for (JSONObject object : jsonObjects) {
            HashMap<String, Object> objectHashMap = MyJsonUtil.JSONObject2HashMap(object);
            String value = (String) objectHashMap.get("value");
            strings.add(value);
        }
        String[] phoneVaule = strings.toArray(new String[strings.size()]);
        this.phoneVaule = phoneVaule;
        this.defaultValue = defaultValue;
    }


    public void setPickViewValue(String[] phoneVaule, Object defaultValue) {
        this.phoneVaule = phoneVaule;
        this.defaultValue = defaultValue;
    }

    private int type;

    public void setPickView(int type) {
        if (type == PickTimeView.TYPE_PICK_DATE) {
            pickDate.setViewType(PickTimeView.TYPE_PICK_DATE);
            pickDate.setVisibility(View.VISIBLE);
        } else if (type == PickTimeView.TYPE_PICK_TIME) {
            pickTime.setViewType(PickTimeView.TYPE_PICK_TIME);
            pickTime.setVisibility(View.VISIBLE);
        } else if (type == PickValueView.TYPE_PICK_PHONE) {
            pickPhone.setVisibility(View.VISIBLE);
            pickPhone.setValueData(phoneVaule, defaultValue);
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    private OnSucessClickListener listener;
    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    @Override
    public void onSelected(PickTimeView view, long timeMillis) {
        if (view == pickTime) {
            String str = sdfTime.format(timeMillis);
            date = str;
        } else {
            String str = sdfDate.format(timeMillis);
            date = str;
        }
    }

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        if (view == pickPhone) {
            date = (String) leftValue;
        }
    }

    public interface OnSucessClickListener {
        void sucessClick(String date, View v);
    }

    public void setOnSucessClickListener(OnSucessClickListener listener) {
        this.listener = listener;
    }
}
