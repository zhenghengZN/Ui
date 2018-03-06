package so.bubu.ui.test.mylibrary.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/12.
 */
public class DrawableClearEditText extends LinearLayout {

    private Drawable mLeftDrawable;

    public DrawableClearEditText(Context context) {
        this(context, null);
//        this.setOrientation(LinearLayout.HORIZONTAL);
//        initView();
    }

    public DrawableClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(LinearLayout.HORIZONTAL);
        initView(context, attrs);
    }

    public DrawableClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ImageView mImageView;
    private int mStrokeColor;
    private ClearEditText mClearEditText;
    private LinearLayout layout;
    private int mRadius = ResourceUtil.Dp2Px(4);
    private int mStrokeWidth = ResourceUtil.Dp2Px(1);
    private int mTextColor;
    private View view;

    private void initView(Context context, AttributeSet attrs) {
//        mImageView = new ImageView(context);
        view = LayoutInflater.from(context).inflate(R.layout.drawable_clearedit_text, this, true);
        mImageView = (ImageView) view.findViewById(R.id.v_icon_user);
        mClearEditText = (ClearEditText) view.findViewById(R.id.edt_name);
//        layout = (LinearLayout) view.findViewById(R.id.layout);
        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.DrawableClearEditText);
        mTextColor = ty.getColor(R.styleable.DrawableClearEditText_text_color, Color.BLACK);
        mLeftDrawable = ty.getDrawable(R.styleable.DrawableClearEditText_left_drawable);
        mStrokeColor = ty.getColor(R.styleable.DrawableClearEditText_stroke_color, Color.BLACK);
        if (mLeftDrawable != null) {
            mImageView.setImageDrawable(mLeftDrawable);
        } else {
            mImageView.setImageDrawable(getResources().getDrawable(R.drawable.pho_user_head));
        }
        mClearEditText.setTextColor(mTextColor);
//            if ()
        buildLayoutDrawable();
        ty.recycle();
    }


    private void buildLayoutDrawable() {
        float outRectr[] = new float[]{mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius};
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setStroke(mStrokeWidth, mStrokeColor);
        pressedDrawable.setCornerRadii(outRectr);
        this.setBackground(pressedDrawable);
    }


    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public void setmStrokeColor(int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
        buildLayoutDrawable();
    }

    public ImageView getLeftImageView() {
        return mImageView;
    }


    public ClearEditText getClearEditText() {
        return mClearEditText;
    }
}
