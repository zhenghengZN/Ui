package so.bubu.ui.test.mylibrary.input;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/26.
 */
public class TextEditText extends LinearLayout implements View.OnFocusChangeListener, TextWatcher {

    private TextView mHintText;
    private EditText mContentEditText;
    //    private int mMargin = ResourceUtil.Dp2Px(5);
    private int mTextSize = ResourceUtil.sp2px(15f);
    private int mHintTextColor = Color.BLACK;
    private int mHintEms = -1;
    private int mContentTextColor = Color.BLACK;
    private String mContentTextHint, mTitle;
    private int mStrokeColor = Color.BLACK;
    private int mStrokeWidth = ResourceUtil.Dp2Px(1f);
    private int style = 0;
    private float mRadius = 0;
    private boolean isTitle, hasStrokeBg;

    public TextEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(LinearLayout.VERTICAL);
//        this.setPadding(ResourceUtil.Dp2Px(15), ResourceUtil.Dp2Px(10), ResourceUtil.Dp2Px(15), ResourceUtil.Dp2Px(10));
//        this.setBackgroundColor(Color.WHITE);

        View view = LayoutInflater.from(context).inflate(R.layout.textedittext, this, true);
        mHintText = (TextView) view.findViewById(R.id.hint_text);
        mContentEditText = (EditText) view.findViewById(R.id.content_text);
        mContentEditText.addTextChangedListener(new TextWatcher() {
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
//                if (!TextUtils.isEmpty(mContentEditText.getText())) {
//                }
            }
        });
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextEditText);
//        mMargin = ta.getInt(R.styleable.TextEditText_field_margin, ResourceUtil.Dp2Px(5));
        mTextSize = ta.getDimensionPixelSize(R.styleable.TextEditText_text_size, 16);
//        ta.getDimensionPixelSize();ta.
        mHintTextColor = ta.getColor(R.styleable.TextEditText_hint_text_color, Color.BLACK);
        mContentTextColor = ta.getColor(R.styleable.TextEditText_content_text_color, getResources().getColor(R.color.color_e2e2e2));
        mHintEms = ta.getInt(R.styleable.TextEditText_hint_ems, -1);
        mContentTextHint = ta.getString(R.styleable.TextEditText_content_text_hint);
        isTitle = ta.getBoolean(R.styleable.TextEditText_istitle, true);
        mTitle = ta.getString(R.styleable.TextEditText_title_text);
        style = ta.getInt(R.styleable.TextEditText_mode, 0);
        hasStrokeBg = ta.getBoolean(R.styleable.TextEditText_hasStrokeBg, false);
        mStrokeColor = ta.getColor(R.styleable.TextEditText_bg_stroke_color, Color.BLACK);
        mStrokeWidth = (int) ta.getDimension(R.styleable.TextEditText_bg_stroke_width, ResourceUtil.Dp2Px(1f));
        mRadius = ta.getDimension(R.styleable.TextEditText_radius, 0);
        init();
        ta.recycle();

    }

    public TextEditText(Context context) {
        this(context, null);
    }

    private void init() {
        if (hasStrokeBg) {
            setTextEditTextBackground();
        }
        mHintText.setTextColor(mHintTextColor);
//        LinearLayout.LayoutParams lp = (LayoutParams) mHintText.getLayoutParams();
//        lp.setMargins(0, 0, mMargin, 0);
//        mHintText.setLayoutParams(lp);
        mHintText.setTextSize(mTextSize);
        if (mHintEms != -1 && mHintEms > 0) {
            mHintText.setEms(mHintEms);
        }
        mHintText.setVisibility(isTitle ? VISIBLE : GONE);
        mHintText.setText(mTitle);

        mContentEditText.setTextColor(mContentTextColor);
        mContentEditText.setTextSize(mTextSize);
        mContentEditText.setHint(mContentTextHint);
        mContentEditText.setOnFocusChangeListener(this);
        mContentEditText.addTextChangedListener(this);
        if (style == 1) {
            setNumberStyle();
        } else if (style == 2) {
            setNumberDecimalStyle();
        }
    }

    private JSONObject jsonObject;

    public void initView(JSONObject jsonObject) {
        mContentEditText.setTextColor(Color.BLACK);
        this.jsonObject = jsonObject;
        try {
            isTitle(false);
            String hint = (String) jsonObject.get("placeholder");
            jsonObject.put("value", "");

            setTextEditTextHint(hint);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public void setFieldMargin(int i) {
//        this.mMargin = i;
//        LinearLayout.LayoutParams lp = (LayoutParams) mHintText.getLayoutParams();
//        lp.setMargins(0, 0, mMargin, 0);
//        mHintText.setLayoutParams(lp);
//        //        invalidate();
//    }

    public void setAllTextSize(int i) {
        this.mTextSize = i;
        mHintText.setTextSize(i);
        mContentEditText.setTextSize(i);

    }

    public void setTitletEms(int i) {
        mHintEms = i;
        if (i != -1 && i > 0) {
            mHintText.setEms(mHintEms);
        }
//        invalidate();
    }

    public void isTitle(boolean isTitle) {
        this.isTitle = isTitle;
        mHintText.setVisibility(isTitle ? VISIBLE : GONE);
//        invalidate();
    }

    public void setTextEditTextHint(String hint) {
        this.mContentTextHint = hint;
        mContentEditText.setHint(mContentTextHint);
//        invalidate();
    }

    public void setAllTextColor(int HintTextColor, int ContentTextColor) {
        this.mContentTextColor = ContentTextColor;
        this.mHintTextColor = HintTextColor;
        mHintText.setTextColor(mHintTextColor);
        mContentEditText.setTextColor(ContentTextColor);
//        invalidate();
    }

    public void setTitle(String title) {
        this.mTitle = title;
        mHintText.setText(mTitle);
//        invalidate();
    }

    public Editable getText() {
        Editable text = mContentEditText.getText();
        return text;
    }

    public void clearText() {
        mContentEditText.setText("");
    }

    public EditText getContentEditText() {
        return mContentEditText;
    }

    public void setInputType(int inputType) {
        mContentEditText.setInputType(inputType);
    }

    public void setNumberStyle() {
        mContentEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public void setNumberDecimalStyle() {
        mContentEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        mContentEditText.setGravity(Gravity.END);
    }

    //TODO
    public void setTextEditTextBackground() {
        GradientDrawable bgDrawable = new GradientDrawable();
        bgDrawable.setStroke(mStrokeWidth, mStrokeColor);
        bgDrawable.setCornerRadii(new float[]{mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius});
        this.setBackground(bgDrawable);
    }

    public void setBackgroundStrokeColor(int color) {
        this.mStrokeColor = color;
        setTextEditTextBackground();
    }

    public void setBackgroundStrokeWidth(int width) {
        this.mStrokeWidth = width;
        setTextEditTextBackground();
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
        setTextEditTextBackground();
    }

    public interface OnFocusChangeImpl {
        void onFocusChange(View v, boolean hasFocus);
    }

    public interface TextWatcherImpl {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(Editable s);
    }

    private OnFocusChangeImpl focusChage;
    private TextWatcherImpl textWatcher;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (focusChage != null) {
            focusChage.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (textWatcher != null) {
            textWatcher.beforeTextChanged(s, start, count, after);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (textWatcher != null) {
            textWatcher.onTextChanged(s, start, before, count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (textWatcher != null) {
            textWatcher.afterTextChanged(s);
        }
    }

    public void setOnFocusChangeImpl(OnFocusChangeImpl focusChage) {
        this.focusChage = focusChage;
    }

    public void setTextWatcherImpl(TextWatcherImpl textWatcher) {

        this.textWatcher = textWatcher;
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
                jsonObject.put("value", mContentEditText.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
