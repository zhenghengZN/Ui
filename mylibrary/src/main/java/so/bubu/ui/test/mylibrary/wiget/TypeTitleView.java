package so.bubu.ui.test.mylibrary.wiget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

import Utils.MyJsonUtil;
import Utils.ResourceUtil;
import Utils.StringUtils;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/6.
 */
public class TypeTitleView extends LinearLayout {
    public TypeTitleView(Context context) {
        this(context, null);
    }

    private int mHeight = ResourceUtil.Dp2Px(60);
    private int mWidth = ResourceUtil.Dp2Px(30);
    private int mBigTextSize = 17;
    private int mSmallTextSize = 15;
    private int mMargin = ResourceUtil.Dp2Px(3);
    private int mTextColor = getResources().getColor(R.color.color_b2b2b2);
    private int mBgColor = getResources().getColor(R.color.color_d8d8d8);
    private TextView title, titleName;
    private Context context;
    private LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public TypeTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(mWidth, mHeight, 0, mHeight);
        setBackgroundColor(Color.TRANSPARENT);
        this.context = context;
        initView();
    }

    private void initView() {
        title = new TextView(context);
        TextPaint tp = title.getPaint();
        tp.setFakeBoldText(true);
        title.setTextSize(mBigTextSize);
        title.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        title.setText("Title");
        title.setPadding(0, mMargin, 0, mMargin);

        titleName = new TextView(context);
        titleName.setText("标题");
        titleName.setTextColor(mTextColor);
        titleName.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        titleName.setTextSize(mSmallTextSize);
        title.setPadding(0, mMargin, 0, mMargin);

        this.addView(title);
        this.addView(titleName);
    }

    public void init(JSONObject object) {
        this.setLayoutParams(lp);
        HashMap<String, Object> objects = MyJsonUtil.JSONObject2HashMap(object);
        String title = (String) objects.get("title");
        String titleName = (String) objects.get("titleName");
        String backgroundColor = (String) objects.get("backgroundColor");
        if(StringUtils.isNull(backgroundColor)) {
            int color = Color.parseColor(backgroundColor);
            setBackgroundColor(color);
        }
        this.title.setText(title);
        this.titleName.setText(titleName);
    }
}
