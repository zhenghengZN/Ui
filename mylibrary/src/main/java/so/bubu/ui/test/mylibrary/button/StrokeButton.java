package so.bubu.ui.test.mylibrary.button;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Util.MyJsonUtil;
import Util.ResourceUtil;
import Util.StringUtils;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.bean.ParamAndSelect;

/**
 * Created by zhengheng on 18/1/3.
 */
public class StrokeButton extends Button {

    public static int[] mNormalState = new int[]{};
    public static int[] mPressState = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
    public static int[] mDisableState = new int[]{-android.R.attr.state_enabled};
    public static int[] mSelectedState = new int[]{android.R.attr.state_selected, android.R.attr.state_enabled};
    private int mRadius = ResourceUtil.Dp2Px(3);                                                                            //默认的圆角半径
    private int mStrokeWidth = ResourceUtil.Dp2Px(0.25f);
    //默认文字和背景颜色
    private int mBgNormalColor = getResources().getColor(R.color.color_un_select);
    private int mBgPressedColor = getResources().getColor(R.color.color_un_select);
    private int mTextNormalColor = getResources().getColor(R.color.colorPrimaryDark);
    //    private int mTextPressedColor = getResources().getColor(R.color.color_82cd6b);
    private int mBgDisableColor = getResources().getColor(R.color.color_un_select);
    private int mTextDisableColor = getResources().getColor(R.color.color_ff5000);
    private int mSolidPressColor = getResources().getColor(R.color.color_d8d8d8);
    private int mSolidNormalColor = Color.WHITE;
    private int mSolidDisableColor = Color.WHITE;


    private final static String BIG = "big";
    private final static String MIDDLE = "middle";
    private final static String SMALL = "small";

    private Context context;

    public StrokeButton(Context context) {
        this(context, null);
        this.context = context;
//        initUI();
    }

    public StrokeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
//        style="?android:attr/borderlessButtonStyle


    }

    public StrokeButton(Context context, AttributeSet attrs, int defStyleAt) {
        super(context, attrs, android.R.attr.borderlessButtonStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StrokeButton);
        mRadius = (int) ta.getDimension(R.styleable.StrokeButton_stroke_radius, ResourceUtil.Dp2Px(5));
        mBgNormalColor = ta.getColor(R.styleable.StrokeButton_stroke_bg_normal_color, getResources().getColor(R.color.color_008b00));
        mBgPressedColor = ta.getColor(R.styleable.StrokeButton_stroke_bg_pressed_color, getResources().getColor(R.color.color_008b00));
        mTextNormalColor = ta.getColor(R.styleable.StrokeButton_stroke_text_normal_color, getResources().getColor(R.color.color_82cd6b));
//        mTextPressedColor = ta.getColor(R.styleable.StrokeButton_stroke_text_pressed_color, getResources().getColor(R.color.color_82cd6b));
        mStrokeWidth = (int) ta.getDimension(R.styleable.StrokeButton_stroke_width, ResourceUtil.Dp2Px(0.5f));
        mSolidNormalColor = ta.getColor(R.styleable.StrokeButton_stroke_solid_normal_color, getResources().getColor(R.color.success));
        mSolidPressColor = ta.getColor(R.styleable.StrokeButton_stroke_solid_pressed_color, getResources().getColor(R.color.color_008b00));
        mSolidDisableColor = ta.getColor(R.styleable.StrokeButton_stroke_solid_disable_color, getResources().getColor(R.color.btn_primary_disable));
        ta.recycle();
        initUI();
    }

    private void initUI() {
        setGravity(Gravity.CENTER);
        buildDraweableState();
        buildColorDrawableState();
    }

    /**
     * 构建图片drawble
     */
    private void buildColorDrawableState() {
        ColorStateList colorStateList = new ColorStateList(new int[][]{mNormalState, mDisableState},
                new int[]{mTextNormalColor, mTextDisableColor});
        setTextColor(colorStateList);
    }

    /**
     * 构建背景Drawble
     */
    private void buildDraweableState() {

        float outRectr[] = new float[]{mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius};
        //创建状态管理器
        StateListDrawable drawable = new StateListDrawable();
        /**
         * 注意StateListDrawable的构造方法我们这里使用的
         * 是第一参数它是一个float的数组保存的是圆角的半径，它是按照top-left顺时针保存的八个值
         */
        //创建圆弧形状
//        RoundRectShape rectShape = new RoundRectShape(outRectr, null, null);
        //创建drawable
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setStroke(mStrokeWidth, mBgPressedColor);
        pressedDrawable.setCornerRadii(outRectr);
        pressedDrawable.setColor(mSolidPressColor);

        //添加到状态管理里面
        drawable.addState(mPressState, pressedDrawable);

        GradientDrawable disableDrawable2 = new GradientDrawable();
        disableDrawable2.setStroke(mStrokeWidth, mBgDisableColor);
        disableDrawable2.setCornerRadii(outRectr);
        disableDrawable2.setColor(mSolidDisableColor);
//        disableDrawable2.setAlpha(0Xee);
        drawable.addState(mDisableState, disableDrawable2);


//        GradientDrawable disableDrawable = new GradientDrawable();
//        disableDrawable.setStroke(mStrokeWidth, mBgDisableColor);
//        disableDrawable.setCornerRadii(outRectr);
////        disableDrawable.setColor(mSolidDisableColor);
////        disableDrawable.setAlpha(0X99);
//        drawable.addState(mDisableState, disableDrawable);

        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setStroke(mStrokeWidth, mBgNormalColor);
        normalDrawable.setCornerRadii(outRectr);
        normalDrawable.setColor(mSolidNormalColor);
        drawable.addState(mNormalState, normalDrawable);
        //设置我们的背景，就是xml里面的selector
        setBackgroundDrawable(drawable);


        //设置api版本大于23的设置字体颜色透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StateListDrawable drawable1 = new StateListDrawable();
            GradientDrawable fontDrawable = new GradientDrawable();
            fontDrawable.setAlpha(50);
            fontDrawable.setCornerRadii(outRectr);
            fontDrawable.setColor(mSolidPressColor);
            drawable1.addState(mPressState, fontDrawable);
            setForeground(drawable1);
        }
    }

    /**
     * 设置圆角矩形
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.mRadius = radius;
        buildDraweableState();
    }

    /**
     * 设置描边宽度
     *
     * @param StrokeWidth
     */
    public void setStrokeWidth(int StrokeWidth) {
        this.mStrokeWidth = StrokeWidth;
        buildDraweableState();
    }

    /**
     * 设置按钮背景颜色
     *
     * @param normalColor
     * @param prssedClor
     */
    public void setBgNormalPressedcolor(int normalColor, int prssedClor) {

        mBgNormalColor = normalColor;
        mBgPressedColor = prssedClor;
        buildDraweableState();

    }

    /**
     * 设置按钮文字颜色
     *
     * @param normalColor
     * @param pressedColor
     */
    public void setTextNormalPressedcolor(int normalColor, int pressedColor, int disableColor) {
//        mTextPressedColor = pressedColor;
        mTextNormalColor = normalColor;
        mTextDisableColor = disableColor;
        buildColorDrawableState();

    }


    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        this.setPadding(0, 0, 0, 0);
        setGravity(Gravity.CENTER);
    }

    public void setSize(String size) {
        if (size == null || size.isEmpty()) {
            return;
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switch (size) {
            case BIG:
                setTextSize(17);
                lp.width = (int) (ResourceUtil.getDeviceWidth((Activity) context) * 0.9);
                lp.height = ResourceUtil.Dp2Px(45);
                break;
            case MIDDLE:
                setTextSize(17);
                lp.width = (int) (ResourceUtil.getDeviceWidth((Activity) context) * 0.5);
                lp.height = ResourceUtil.Dp2Px(45);
                break;
            case SMALL:

                setTextSize(12);
                lp.width = (int) (ResourceUtil.getDeviceWidth((Activity) context) * 0.15);
                lp.height = ResourceUtil.Dp2Px(35);
                break;
        }
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        lp.setMargins(0, ResourceUtil.Dp2Px(7), 0, ResourceUtil.Dp2Px(7));
        setLayoutParams(lp);
    }
//    public void setTrans(String )

    private final static String DEFAULT = "default";
    private final static String WARNING = "warning";
    private final static String PIRMARY = "pirmary";
    private final static String PLAIN_DEFAULT = "plaindefault";
    private final static String PLAIN_PRIMARY = "plainprimary";

    public void init(JSONObject objects) {

        HashMap<String, Object> object = MyJsonUtil.JSONObject2HashMap(objects);
        Boolean state = (Boolean) object.get("state");
        String title = (String) object.get("title");
        String size = (String) object.get("size");
        String type = (String) object.get("type");

        String noramlTextColor = (String) object.get("noramlTextColor");
        String normalStrokeColor = (String) object.get("normalStrokeColor");
        String pressStrokeColor = (String) object.get("pressStrokeColor");
        String normalBackground = (String) object.get("normalBackground");
        String pressBackground = (String) object.get("pressBackground");

        if (StringUtils.isNull(noramlTextColor)) {
            this.mTextNormalColor = Color.parseColor(noramlTextColor);
        }
        if (StringUtils.isNull(noramlTextColor)) {
            this.mBgNormalColor = Color.parseColor(normalStrokeColor);
        }

        if (StringUtils.isNull(noramlTextColor)) {
            this.mBgPressedColor = Color.parseColor(pressStrokeColor);
        }

        if (StringUtils.isNull(noramlTextColor)) {
            this.mSolidNormalColor = Color.parseColor(normalBackground);
        }

        if (StringUtils.isNull(noramlTextColor)) {
            this.mSolidPressColor = Color.parseColor(pressBackground);
        }

        if (StringUtils.isNull(noramlTextColor)) {
            this.mSolidDisableColor = Color.parseColor(normalBackground);
        }


        if (state != null) {
            setEnabled(state);
        }
        setTypeStyle(type);
        setSize(size);
        buildDraweableState();
        this.setEnabled(state);
        buildColorDrawableState();
        this.setText(title);

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            this.mTextNormalColor = getResources().getColor(R.color.disable_text_color);
//            setAlpha(0.9f);
        }
    }

    public int getColor(int ResId) {
        int color = getResources().getColor(ResId);
        return color;
    }

    public void setTypeStyle(String type) {
        if(!StringUtils.isNull(type)){
            return;
        }
        switch (type) {
            case DEFAULT:
                mTextNormalColor = Color.BLACK;
                mSolidNormalColor = getColor(R.color.btn_default_bg);
                mSolidPressColor = getColor(R.color.btn_default_active);
                mSolidDisableColor = getColor(R.color.btn_default_disable);
                mBgNormalColor = getColor(R.color.color_b2b2b2);
                mBgPressedColor = getColor(R.color.color_b2b2b2);
//                this.setTextAppearance(getContext(), R.style.btn_default);
                break;
            case WARNING:
                mTextNormalColor = Color.WHITE;
                mSolidNormalColor = getColor(R.color.btn_warn_bg);
                mSolidPressColor = getColor(R.color.btn_warn_active);
                mSolidDisableColor = getColor(R.color.btn_warn_disable);
                mBgNormalColor = getColor(R.color.red);
                mBgPressedColor = getColor(R.color.red);
//                this.setTextAppearance(getContext(), R.style.btn_warn);
                break;
            case PIRMARY:
                mTextNormalColor = Color.WHITE;
                mSolidNormalColor = getColor(R.color.btn_primary_bg);
                mSolidPressColor = getColor(R.color.btn_primary_active);
                mSolidDisableColor = getColor(R.color.btn_primary_disable);
                mBgNormalColor = getColor(R.color.color_008b00);
                mBgPressedColor = getColor(R.color.color_008b00);
//                this.setTextAppearance(getContext(), R.style.btn_primary);
                break;

            case PLAIN_DEFAULT:
                mTextNormalColor = Color.BLACK;
                mSolidNormalColor = Color.WHITE;
                mSolidPressColor = Color.WHITE;
                mSolidDisableColor = Color.WHITE;
                mBgNormalColor = Color.BLACK;
                mBgPressedColor = Color.BLACK;
                break;

            case PLAIN_PRIMARY:
                mTextNormalColor = getColor(R.color.btn_primary_active);
                mSolidNormalColor = Color.WHITE;
                mSolidPressColor = Color.WHITE;
                mSolidDisableColor = Color.WHITE;
                mBgNormalColor = getColor(R.color.btn_primary_active);
                mBgPressedColor = getColor(R.color.btn_primary_bg);
                break;

//            default:
//                mSolidDisableColor = getColor(R.color.btn_default_disable);
//                mTextDisableColor = getColor(R.color.btn_default_active);
//                mBgDisableColor = getColor(R.color.btn_default_active);
        }
        setTextSize(18);
    }


    public void setSubmitButton(JSONObject object, final ArrayList<LinkedHashMap<String, Object>> inputWeight) {
        init(object);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("?");
                try {
                    ArrayList<ParamAndSelect> list = featchValue(inputWeight);

                    for (int i = 0; i < list.size(); i++) {
                        ParamAndSelect weight = list.get(i);
                        String s = weight.toString();
                        buffer.append(s);
                        if (i != list.size() - 1) {
                            buffer.append("&");
                        }

                    }

                    //TODO 得到了所有控件的参数和值,接下来要传给服务器或做其他处理
                    Log.e("zhengheng paramandvalue", buffer.toString());
                    Toast.makeText(getContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public ArrayList<ParamAndSelect> featchValue(ArrayList<LinkedHashMap<String, Object>> weights) throws JSONException {
        ArrayList<ParamAndSelect> list = new ArrayList<>();
        for (LinkedHashMap<String, Object> weight : weights) {
            String type = (String) weight.get("type");
            switch (type) {
                //paramName,value为String类型
                case "Form":
                    JSONArray formobject = (JSONArray) weight.get("objects");
                    ArrayList<ParamAndSelect> objects1 = new ArrayList<>();
                    for (int i = 0; i < formobject.length(); i++) {

                        JSONObject object = (JSONObject) formobject.get(i);
                        String paramName = (String) object.get("paramName");
                        String value = (String) object.get("value");
                        if (value == null || value.isEmpty()) {
                            break;
                        }
                        ParamAndSelect paramAndSelect = new ParamAndSelect(paramName, value);
                        objects1.add(paramAndSelect);
                    }
                    list.addAll(objects1);
                    break;
                case "TextView":
                case "TextArea":
                case "ChooseInputView":
                case "SwitchView":
                    JSONArray objects = (JSONArray) weight.get("objects");
                    for (int i = 0; i < objects.length(); i++) {
                        ArrayList<ParamAndSelect> map = new ArrayList<>();
                        JSONObject o = (JSONObject) objects.get(i);
                        String paramName = (String) o.get("paramName");
                        if (type.equalsIgnoreCase("SwitchView")) {
                            Boolean value = (Boolean) o.get("selectedValue");
                            map.add(new ParamAndSelect(paramName, value));
                        } else {
                            String value = (String) o.get("value");
                            if (value != null && !value.isEmpty()) {
                                map.add(new ParamAndSelect(paramName, value));
                            } else {
                                if (type.equalsIgnoreCase("Form")) {
                                    break;
                                }
                            }
                        }
                        list.addAll(map);
                    }
                    break;
                //paramName可能是String,JsonArray,selectedValue可能是String,JsonArray,ArrayList
                case "SingleCheckList":
                case "MoreCheckList":
                    Object paramName = weight.get("paramName");
                    Object selectedValue = weight.get("selectedValue");
                    if (paramName instanceof String && selectedValue instanceof String) {
                        ArrayList<ParamAndSelect> map = new ArrayList<>();
                        String param = (String) paramName;
                        String value = (String) selectedValue;
                        map.add(new ParamAndSelect(param, value));
                        list.addAll(map);
                    } else if (paramName instanceof String) {
                        String param = (String) paramName;
                        if (selectedValue instanceof JSONArray) {
                            JSONArray values = (JSONArray) selectedValue;
                            for (int i = 0; i < values.length(); i++) {
                                ArrayList<ParamAndSelect> map = new ArrayList<>();
                                JSONObject valueObject = (JSONObject) values.get(i);
                                String value = (String) valueObject.get("value");
                                map.add(new ParamAndSelect(param, value));
                                list.addAll(map);
                            }
                        } else if (selectedValue instanceof ArrayList) {
                            ArrayList values = (ArrayList) selectedValue;
                            for (int i = 0; i < values.size(); i++) {
                                ArrayList<ParamAndSelect> map = new ArrayList<>();
                                String value = (String) values.get(i);
                                map.add(new ParamAndSelect(param, value));
                                list.addAll(map);
                            }
                        }
                    }
                    break;
                case "AboutCheckBox":
                    boolean value = (boolean) weight.get("selectedValue");
                    String param = (String) weight.get("paramName");
                    ParamAndSelect paramAndSelect = new ParamAndSelect(param, value);
                    list.add(paramAndSelect);
                    break;
                default:
                    break;
            }

        }
        return list;
    }

}
