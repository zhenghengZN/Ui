package so.bubu.ui.test.mylibrary.input.checkBox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;

public class CheckBox extends LinearLayout {

    protected final String NAME_SPACE = "http://schemas.android.com/apk/res/android";

    public int mDefaultSize;

    private TextView mTextView;
    private CheckView mCheckView;
    private RadioButton rightCheckBox;
    private OnCheckedChangeListener mListener;
    private Context ctx;

    public CheckBox(Context context) {
        this(context, null);
        ctx = context;
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundResource(R.drawable.button);
        mDefaultSize = dp2px(context, 10);
        initView(context, attrs);
    }
        private LayoutParams mRightParams;

    private void initView(Context context, AttributeSet attrs) {
        boolean clickable = attrs.getAttributeBooleanValue(NAME_SPACE, "clickable", true);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setClickable(clickable);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CheckBox);
        String text = ta.getString(R.styleable.CheckBox_text);
        int textColor = ta.getColor(R.styleable.CheckBox_textColor, Color.BLACK);
        float textSize = ta.getDimension(R.styleable.CheckBox_textSize, dp2px(context, 17));
        int middlePadding = ta.getDimensionPixelOffset(R.styleable.CheckBox_middlePadding, mDefaultSize);
        int checkBoxWidth = ta.getDimensionPixelOffset(R.styleable.CheckBox_checkBoxWidth, mDefaultSize * 2);
        int checkBoxHeight = ta.getDimensionPixelOffset(R.styleable.CheckBox_checkBoxHeight, mDefaultSize * 2);
        boolean isLeftCheckBox = ta.getBoolean(R.styleable.CheckBox_isleftcheckbox, true);
        ta.recycle();

        LayoutParams mCheckParams = new LayoutParams(checkBoxWidth, checkBoxHeight);
        mCheckView = new CheckView(context, attrs);
        //you  ce
        mRightParams = new LayoutParams(ResourceUtil.Dp2Px(18), ResourceUtil.Dp2Px(18));
        mRightParams.gravity = Gravity.RIGHT;
        rightCheckBox = new RadioButton(context);
        Drawable drawable = context.getResources().getDrawable(R.drawable.single_button_bg);
        rightCheckBox.setBackground(drawable);
        rightCheckBox.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));


        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTextView = new TextView(context);
        params.weight = 1f;
        params.leftMargin = middlePadding;
        mTextView.setLayoutParams(params);
        mTextView.setText(text);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTextView.setTextColor(textColor);

        if(isLeftCheckBox) {
            addView(mCheckView, mCheckParams);
            addView(mTextView, params);
        }else {
            addView(mTextView, params);

        }


        if (!isClickable()) return;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckView.toggle(true);
                rightCheckBox.toggle();

            }
        });
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        int defaultPadding = dp2px(getContext(), 10);
        if (left == 0) left = defaultPadding;
        if (top == 0) top = defaultPadding;
        if (right == 0) right = defaultPadding;
        if (bottom == 0) bottom = defaultPadding;
        super.setPadding(left, top, right, bottom);
    }

    public int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void toggle() {
        toggle(false);
    }

    public void toggle(boolean anim) {
        mCheckView.toggle(anim);
        rightCheckBox.toggle();
    }

    public boolean isChecked() {
        return mCheckView.isChecked();
    }

    //TODO
    public boolean isRightChecked() {
        return rightCheckBox.isChecked();
    }

    public void setRightCheck(boolean checked) {
        rightCheckBox.setChecked(checked);
    }

    public RadioButton getRightCheck() {
        return rightCheckBox;
    }

    public void setChecked(boolean checked) {
        setChecked(checked, false);
    }

    public void setChecked(boolean checked, boolean anim) {
        mCheckView.setChecked(checked, anim);
        if (mListener != null) {
            mListener.onCheckedChanged(this, mCheckView.isChecked());
        }
    }

    public void setCheckedColor(int checkedColor) {
        mCheckView.setCheckedColor(checkedColor);
    }

    public void setShape(int shape) {
        mCheckView.setShape(shape);
    }

    public void isLeftCheckBox(boolean isLeft){
        if(isLeft){
            mCheckView.setVisibility(VISIBLE);
            rightCheckBox.setVisibility(GONE);
        }else {
            mCheckView.setVisibility(GONE);
            rightCheckBox.setVisibility(VISIBLE);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 0;
            params.weight = 1f;
            mTextView.setLayoutParams(params);
            addView(rightCheckBox, mRightParams);
        }
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }

    public TextView getTextView(){
        return mTextView;
    }

    public CharSequence getText() {
        return mTextView.getText();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener l) {
        this.mListener = l;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CheckBox checkBox, boolean isChecked);
    }
}
