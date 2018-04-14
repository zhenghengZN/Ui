package so.bubu.ui.test.mylibrary.item.inputlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import Util.GlideHelper;
import Util.MyJsonUtil;
import Util.StringUtils;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/5.
 */
public class MineView extends LinearLayout {
    public MineView(Context context) {
        this(context, null);
    }

    private ImageView leftIcon, rightIcon;
    private TextView name, detail;
    private RelativeLayout layout;

    public MineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.mine_cell, this, true);
        leftIcon = (ImageView) view.findViewById(R.id.left_icon);
        name = (TextView) view.findViewById(R.id.tv_name);
        detail = (TextView) view.findViewById(R.id.tv_detail);
        rightIcon = (ImageView) view.findViewById(R.id.right_icon);
        layout = (RelativeLayout) view.findViewById(R.id.img_icon);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), url, Toast.LENGTH_LONG).show();
            }
        });
    }

    //    private JSONObject objects;
    private String url;
    private ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public void init(JSONObject object) {
        HashMap<String, Object> objects = MyJsonUtil.JSONObject2HashMap(object);
        this.setLayoutParams(lp);
//        this.objects = object;
        String title = (String) objects.get("title");
        if (StringUtils.isNull(title)) {
            name.setVisibility(VISIBLE);
            name.setText(title);
        }
        String leftUrl = (String) objects.get("picUrl");
        if (StringUtils.isNull(leftUrl)) {
            layout.setVisibility(VISIBLE);
            GlideHelper.display(getContext(), leftUrl, leftIcon);
        }
        String detail = (String) objects.get("detail");
        if (StringUtils.isNull(detail)) {
            this.detail.setVisibility(VISIBLE);
            this.detail.setText(detail);
        }
        url = (String) objects.get("url");
        if (StringUtils.isNull(url)) {
            rightIcon.setVisibility(VISIBLE);
        }
    }


}
