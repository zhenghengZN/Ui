package so.bubu.ui.test.mylibrary.wiget.form;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.wiget.WarningEditText;

/**
 * Created by zhengheng on 18/2/1.
 */
public class FormTextView extends LinearLayout {
    public FormTextView(Context context) {
        this(context, null);
    }

    private TextView mTitle;
    private WarningEditText content;

    public FormTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.formtextview, this, true);
        mTitle = (TextView) view.findViewById(R.id.title);
        content = (WarningEditText) view.findViewById(R.id.warin_edittext);
        content.setTextSize(17);
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (delayRun != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }
//                editString = s.toString();


                handler.postDelayed(delayRun, 200);

                //延迟800ms，如果不再输入字符，则执行该线程的run方法
//                if (!TextUtils.isEmpty(content.getText())) {
//
//                }

            }


        });
    }


    private Handler handler = new Handler();

    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
//            getSearchResult(editString, "all", 1, "true");
            try {
                o.put("value", content.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void setTitle(String s) {
        mTitle.setText(s);
    }

    private JSONObject o;


    public void init(JSONObject o) {
        this.o = o;
        try {
            String title = (String) o.get("title");
            String value = (String) o.get("placeholder");
            o.put("value","");
            content.setHint(value);
            setTitle(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
