package so.bubu.ui.test.mylibrary.button;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.bean.ParamAndSelect;

/**
 * Created by zhengheng on 18/1/3.
 */
public class SolidButton extends Button {

    public static int[] mNormalState = new int[]{};
    public static int[] mPressState = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
    public static int[] mDisableState = new int[]{-android.R.attr.state_enabled};
    //    public static int[] mSelectedState = new int[]{android.R.attr.state_selected, android.R.attr.state_enabled};
    private int mRadius = ResourceUtil.Dp2Px(10);                                                                            //默认的圆角半径

    private final String BIG = "big";
    private final String MIDDLE = "middle";
    private final String SMALL = "small";

    //默认文字和背景颜色
    private int mBgNormalColor = getResources().getColor(R.color.color_82cd6b);
    private int mBgPressedColor = getResources().getColor(R.color.color_82cd6b);
    private int mBgDisableColor = getResources().getColor(R.color.color_disable);
    private int mTextNormalColor = Color.WHITE;
    private int mTextPressedColor = Color.WHITE;
    private int mTextDisableCollor = getResources().getColor(R.color.color_un_select);
    private Context context;

    public SolidButton(Context context) {
        super(context);
        this.context = context;
        initUI();
    }

    public SolidButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SolidButton);
        mRadius = (int) ta.getDimension(R.styleable.SolidButton_solid_radius, ResourceUtil.Dp2Px(50));
        mBgNormalColor = ta.getColor(R.styleable.SolidButton_solid_bg_normal_color, getResources().getColor(R.color.color_82cd6b));
        mBgPressedColor = ta.getColor(R.styleable.SolidButton_solid_bg_pressed_color, getResources().getColor(R.color.color_82cd6b));
        mTextNormalColor = ta.getColor(R.styleable.SolidButton_solid_text_normal_color, Color.WHITE);
        mTextPressedColor = ta.getColor(R.styleable.SolidButton_solid_text_pressed_color, Color.WHITE);
        ta.recycle();

        initUI();
    }

    public void init(JSONObject object) {
        try {
            String title = (String) object.get("title");
            boolean state = (boolean) object.get("state");
            String size = (String) object.get("size");

            setSize(size);

            setText(title);
            if (state) {
                String normalBackground = (String) object.get("normalBackground");
                String pressBackground = (String) object.get("pressBackground");
                this.mBgNormalColor = Color.parseColor(normalBackground);
                this.mBgPressedColor = Color.parseColor(pressBackground);
            }
            this.setEnabled(state);
            buildColorDrawableState();
            buildDraweableState();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void setSize(String size) {
        if (size == null || size.isEmpty()) {
            return;
        }
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switch (size) {
            case BIG:
                lp.width = (int) (ResourceUtil.getDeviceWidth((Activity) context) * 0.9);
                lp.height = ResourceUtil.Dp2Px(50);
                break;
            case MIDDLE:
                lp.width = (int) (ResourceUtil.getDeviceWidth((Activity) context) * 0.5);
                lp.height = ResourceUtil.Dp2Px(50);
                break;
            case SMALL:
                lp.width = ResourceUtil.Dp2Px(50);
                lp.height = ResourceUtil.Dp2Px(20);
                break;
        }
        setLayoutParams(lp);
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
        ColorStateList colorStateList = new ColorStateList(new int[][]{mPressState, mNormalState, mDisableState},
                new int[]{mTextPressedColor, mTextNormalColor, mTextDisableCollor});
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
        RoundRectShape rectShape = new RoundRectShape(outRectr, null, null);
        //创建drawable
        ShapeDrawable pressedDrawable = new ShapeDrawable(rectShape);
        //设置我们按钮背景的颜色
        pressedDrawable.getPaint().setColor(mBgPressedColor);
        //添加到状态管理里面
        drawable.addState(mPressState, pressedDrawable);

        ShapeDrawable disableDrawable = new ShapeDrawable(rectShape);
        disableDrawable.getPaint().setColor(mBgDisableColor);
        disableDrawable.getPaint().setAlpha(125);
        drawable.addState(mDisableState, disableDrawable);

        ShapeDrawable normalDrawable = new ShapeDrawable(rectShape);
        normalDrawable.getPaint().setColor(mBgNormalColor);
        drawable.addState(mNormalState, normalDrawable);
        //设置我们的背景，就是xml里面的selector
        setBackgroundDrawable(drawable);
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
    public void setTextNormalPressedcolor(int normalColor, int pressedColor) {
        mTextPressedColor = pressedColor;
        mTextNormalColor = normalColor;
        buildColorDrawableState();

    }

    public int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        this.setPadding(0, 0, 0, 0);
        setGravity(Gravity.CENTER);
    }

    public void setSubmitButton(JSONObject object, final ArrayList<LinkedHashMap<String, Object>> inputWeight) {
        mRadius = 5;
        mBgPressedColor = getResources().getColor(R.color.color_006400);
        buildDraweableState();
        try {
            String title = (String) object.get("title");
            setText(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setTextSize(dp2px(context, 6));
//        setGravity(Gravity.CENTER);
//        this.setPadding(0, 0, 0, 0);
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
//        LinkedHashMap<String, ParamAndSelect> map = new LinkedHashMap<>();
//        ArrayList<HashMap<String, ParamAndSelect>> list = new ArrayList<>();
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
//                        HashMap<String, ParamAndSelect> pas = new HashMap<>();
//                        pas.put(type, paramAndSelect);
//                        objects1.add(pas);
                    }
                    list.addAll(objects1);

                    break;
                case "TextView":
                case "TextArea":
                case "ChooseInputView":
                case "SwitchView":
                    JSONArray objects = (JSONArray) weight.get("objects");
                    for (int i = 0; i < objects.length(); i++) {
//                        HashMap<String, ParamAndSelect> map = new HashMap<>();
                        ArrayList<ParamAndSelect> map = new ArrayList<>();
                        JSONObject o = (JSONObject) objects.get(i);
                        String paramName = (String) o.get("paramName");
                        if (type.equalsIgnoreCase("SwitchView")) {
                            Boolean value = (Boolean) o.get("selectedValue");
//                            map.put(type, new ParamAndSelect(paramName, value));
                            map.add(new ParamAndSelect(paramName, value));
                        } else {
                            String value = (String) o.get("value");
                            if (value != null && !value.isEmpty()) {
//                                map.put(type, new ParamAndSelect(paramName, value));
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
//                        HashMap<String, ParamAndSelect> map = new HashMap<>();
                        ArrayList<ParamAndSelect> map = new ArrayList<>();
                        String param = (String) paramName;
                        String value = (String) selectedValue;
//                        map.put(type, new ParamAndSelect(param, value));
                        map.add(new ParamAndSelect(param, value));
                        list.addAll(map);
                    } else if (paramName instanceof String) {

//                        JSONArray paramNameArray = (JSONArray) paramName;
                        String param = (String) paramName;
                        if (selectedValue instanceof JSONArray) {

                            JSONArray values = (JSONArray) selectedValue;
                            for (int i = 0; i < values.length(); i++) {
                                ArrayList<ParamAndSelect> map = new ArrayList<>();
                                JSONObject valueObject = (JSONObject) values.get(i);
                                String value = (String) valueObject.get("value");
//                                String param = (String) paramNameArray.get(i);
                                map.add(new ParamAndSelect(param, value));
                                list.addAll(map);
                            }

                        } else if (selectedValue instanceof ArrayList) {

                            ArrayList values = (ArrayList) selectedValue;
                            for (int i = 0; i < values.size(); i++) {
                                ArrayList<ParamAndSelect> map = new ArrayList<>();
                                String value = (String) values.get(i);
//                                String param = (String) paramNameArray.get(i);
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
