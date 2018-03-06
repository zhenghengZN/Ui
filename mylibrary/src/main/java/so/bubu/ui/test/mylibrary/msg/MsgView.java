package so.bubu.ui.test.mylibrary.msg;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/9.
 */
public class MsgView extends LinearLayout {
    public MsgView(Context context) {
        this(context, null);
    }

    private View view;
    private TextView msgTitle, msgDesc;
    private ImageView msgIcon;

    public MsgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.msglayout, this, true);
        msgIcon = (ImageView) view.findViewById(R.id.msg_icon);
        msgTitle = (TextView) view.findViewById(R.id.msg_title);
        msgDesc = (TextView) view.findViewById(R.id.msg_desc);
    }

    private JSONObject object;

    public void init(JSONObject object) {
        this.object = object;
        try {
            Boolean resultCode = (Boolean) object.get("resultCode");
            String title = (String) object.get("resultTitle");
            String desc = (String) object.get("desc");
            Drawable drawable;
            if (resultCode) {
                drawable = getResources().getDrawable(R.drawable.success);
            } else {
                drawable = getResources().getDrawable(R.drawable.warn);
            }
            msgIcon.setImageDrawable(drawable);
            msgTitle.setText(title);
            msgDesc.setText(desc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
