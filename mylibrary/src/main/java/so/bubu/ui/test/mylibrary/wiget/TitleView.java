package so.bubu.ui.test.mylibrary.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/6.
 */
public class TitleView extends TextView {
    public TitleView(Context context) {
        this(context, null);
    }

    private int mHeight = ResourceUtil.Dp2Px(10);
    private int mWidth = ResourceUtil.Dp2Px(5);
    private int mSmallTextSize = 15;
    private int mTextColor = getResources().getColor(R.color.color_b2b2b2);
    private int mBgColor = getResources().getColor(R.color.color_d8d8d8);
    private LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPadding(mHeight, mWidth, 0, mWidth);
        setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        setBackgroundColor(mBgColor);
        setTextSize(mSmallTextSize);
        setTextColor(mTextColor);
        setText("标题");
    }

    public void init(JSONObject objects){
        this.setLayoutParams(lp);
        try {
            String title = (String) objects.get("title");
            this.setText(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
