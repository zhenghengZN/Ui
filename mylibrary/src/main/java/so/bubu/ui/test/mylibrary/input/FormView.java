package so.bubu.ui.test.mylibrary.input;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.wiget.TitleView;
import so.bubu.ui.test.mylibrary.wiget.form.FormDatePick;
import so.bubu.ui.test.mylibrary.wiget.form.FormTextView;
import so.bubu.ui.test.mylibrary.wiget.form.FormTextViewAndImage;
import so.bubu.ui.test.mylibrary.wiget.form.FormTwoTextView;

/**
 * Created by zhengheng on 18/1/29.
 */
public class FormView extends LinearLayout {
    public FormView(Context context) {
        this(context, null);
    }

    //    private View view;
//    private TextView getCheck, date, time;
//    private EditText mQqNum, mPhoneNum, check;
//    private ImageView checkImg;
    private Context context;
    private LinearLayout layout;
    //    private View line;
    private TitleView instruction;

    public FormView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.form, this, true);
//        line = LayoutInflater.from(context).inflate(R.layout.wight_line, null, false);
        layout = (LinearLayout) view.findViewById(R.id.diff_formview);
        instruction = (TitleView) view.findViewById(R.id.instruction);
    }

    private OnCheckCodeListener onCheckCodeListener;

    public interface OnCheckCodeListener {
        void getPhoneCheckCode();

        void getImageCheckCode();
    }

    public void setOnCheckCodeListener(OnCheckCodeListener onCheckCodeListener) {
        this.onCheckCodeListener = onCheckCodeListener;
    }

    public void init(LinkedHashMap<String, Object> object) {
//        if(objects.length

        JSONArray objects = (JSONArray) object.get("objects");
        String placeholder = (String) object.get("placeholder");
        if (placeholder != null && !placeholder.isEmpty()) {
            instruction.setVisibility(VISIBLE);
            instruction.setText(placeholder);
        }
        for (int i = 0; i < objects.length(); i++) {
            try {
                JSONObject o = (JSONObject) objects.get(i);
                String type = (String) o.get("type");
                switch (type) {
                    case "FormTextView":
                        FormTextView formTextView = new FormTextView(context);
                        formTextView.init(o);
                        layout.addView(formTextView);
                        break;
                    case "FormTwoTextView":
                        FormTwoTextView formTwoTextView = new FormTwoTextView(context);
                        formTwoTextView.init(o);
                        layout.addView(formTwoTextView);
                        break;
                    case "FormDatePick":
                        FormDatePick formDatePick = new FormDatePick(context);
                        formDatePick.init(o);
                        layout.addView(formDatePick);
                        break;
                    case "FormTextViewAndImage":
                        FormTextViewAndImage viewAndImage = new FormTextViewAndImage(context);
                        viewAndImage.init(o);
                        layout.addView(viewAndImage);
                        break;
                }
                if (i == objects.length() - 1) {
                    return;
                }
                layout.addView(setLine());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public View setLine() {
        View view = new View(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ResourceUtil.Dp2Px(0.5f));
        layoutParams.setMargins(ResourceUtil.Dp2Px(10), 0, 0, 0);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(context.getResources().getColor(R.color.color_b2b2b2));
        view.setPadding(ResourceUtil.Dp2Px(10), 0, 0, 0);
        return view;
    }
//    public interface OnFormFinishListener {
//        void OnFormFinish(String qq, String phone, String date, String time, String check);
//    }
//
//    private OnFormFinishListener listener;
//
//    public void setOnFormFinishListener(OnFormFinishListener listener) {
//        this.listener = listener;
//    }
}
