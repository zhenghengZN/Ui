package so.bubu.ui.test.mylibrary.input;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/29.
 */
public class TextArea extends LinearLayout {
    public TextArea(Context context) {
        this(context, null);
    }

    private TextView numText;
    private EditText textarea;

    public TextArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(VERTICAL);
        View view = LayoutInflater.from(context).inflate(R.layout.textarea, this, true);
        textarea = (EditText) view.findViewById(R.id.textarea);
        numText = (TextView) view.findViewById(R.id.text_num);
        textarea.addTextChangedListener(new TextWatcher() {
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
                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                if (!TextUtils.isEmpty(textarea.getText())) {
                    handler.postDelayed(delayRun, 200);
                }
            }
        });
    }

    public Editable getTextAreaText() {
        Editable text = textarea.getText();
        return text;
    }

    private Integer maxNum = 200;

    private JSONObject jsonObject;

    public void init(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        try {
            String hint = (String) jsonObject.get("placeholder");
            maxNum = (Integer) jsonObject.get("maxNum");
            jsonObject.put("value", "");
            textarea.setHint(hint);
            textarea.setMaxHeight(maxNum <= 0 ? 200 : maxNum);
            numText.setText("0/" + maxNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                numText.setText(s.length() + "/" + maxNum);

                if (delayRun != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }

                handler.postDelayed(delayRun, 200);

                //延迟800ms，如果不再输入字符，则执行该线程的run方法
//                if (!TextUtils.isEmpty(textarea.getText())) {
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
            try {
                jsonObject.put("value", textarea.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
