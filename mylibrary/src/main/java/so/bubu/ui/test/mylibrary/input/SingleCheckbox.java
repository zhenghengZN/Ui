package so.bubu.ui.test.mylibrary.input;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;

import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/29.
 */
public class SingleCheckbox extends LinearLayout {

    private TextView title;
    private RadioButton checkImage;
    private View view, parent;
    private String titleName;
    private boolean ischeck;

    public SingleCheckbox(Context context) {
        this(context, null);
    }

    public SingleCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(VERTICAL);
        this.view = LayoutInflater.from(context).inflate(R.layout.singlecheckbox, this, true);
        parent = view.findViewById(R.id.parent);
//        parent.setOnClickListener(this);
        title = (TextView) view.findViewById(R.id.title);
        checkImage = (RadioButton) view.findViewById(R.id.check_img);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SingleCheckbox);
        titleName = ta.getString(R.styleable.SingleCheckbox_check_title);
        title.setText(titleName);

        View view = new View(context);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ResourceUtil.Dp2Px(0.5f));
        lp.setMargins(ResourceUtil.Dp2Px(10f), 0, 0, 0);
        view.setLayoutParams(lp);
        view.setBackgroundColor(getResources().getColor(R.color.color_e2e2e2));
        this.addView(view);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkImage.setChecked(!ischeck);
            }
        });
    }

    public void init(HashMap<String,Object>  map) {
        title.setText((String)map.get("title"));
    }

    public void setTitle(String s) {
        title.setText(s);
    }

    public void setCheckChage(boolean check) {
        checkImage.setChecked(check);
    }

    public String getTtile() {
        return title.getText().toString();
    }
}
