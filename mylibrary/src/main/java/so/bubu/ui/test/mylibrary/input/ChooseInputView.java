package so.bubu.ui.test.mylibrary.input;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Util.MyJsonUtil;
import so.bubu.ui.test.mylibrary.Dialog.PickerDialog;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/30.
 */
public class ChooseInputView extends LinearLayout {
    public ChooseInputView(Context context) {
        this(context, null);
    }

    private TextView num;
    private EditText phoneNum;
    private Context context;
    private PickerDialog pickTimePopWindow;

    public ChooseInputView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.chooseinputview, this, true);
        View addressPnum = view.findViewById(R.id.address_pnum);
        num = (TextView) view.findViewById(R.id.address);
        phoneNum = (EditText) view.findViewById(R.id.phone_num);
        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (delayRun != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }

                handler.postDelayed(delayRun, 200);

                //延迟800ms，如果不再输入字符，则执行该线程的run方法
//                if (!TextUtils.isEmpty(phoneNum.getText())) {
//                }
            }
        });
//        checkPhone();

        pickTimePopWindow = new PickerDialog((Activity) context);
        pickTimePopWindow.setType(3);
        pickTimePopWindow.setOnSucessClickListener(new PickerDialog.OnSucessClickListener() {
            @Override
            public void sucessClick(String date, View v) {
                if (date != null && !date.isEmpty()) {
                    num.setText(date);
                }
            }
        });

        addressPnum.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ((Activity) context).getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                pickTimePopWindow.show();
            }
        });
    }

    private ArrayList<String> titles = new ArrayList<>();
    private JSONObject object;

    public void init(JSONObject objects) {
        this.object = objects;
        HashMap<String, Object> object = MyJsonUtil.JSONObject2HashMap(objects);
        try {
            String hint = (String) object.get("placeholder");
            phoneNum.setHint(hint);
            JSONArray inputContent = (JSONArray) object.get("inputContent");
            String selectValue = (String) object.get("selectedValue");
            object.put("value", "");
            for (int i = 0; i < inputContent.length(); i++) {
                JSONObject jsonObject = inputContent.getJSONObject(i);
                String title = (String) jsonObject.get("title");
                titles.add(title);
            }
//            if (titles.size() > 0) {
//                num.setText(titles.get(0));
//            }
            num.setText(selectValue);
            String[] values = titles.toArray(new String[titles.size()]);
            Log.e("zhengheng", "" + values.toString());
            pickTimePopWindow.setPickViewValue(values, selectValue);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler();

    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            try {
                if (phoneNum.getText().toString().trim().isEmpty()) {
                    object.put("value", "");
                } else {
                    object.put("value", num.getText() + phoneNum.getText().toString().trim());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

}
