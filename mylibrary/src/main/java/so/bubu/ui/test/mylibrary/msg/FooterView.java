package so.bubu.ui.test.mylibrary.msg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/9.
 */
public class FooterView extends LinearLayout {
    public FooterView(Context context) {
        this(context, null);
    }

    private TextView footLink, footText;
    private View view;

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.footerview, this, true);
        footLink = (TextView) view.findViewById(R.id.footer_link);
        footText = (TextView) view.findViewById(R.id.footer_text);
    }

    private JSONObject object;

    public void init(JSONObject object) {
        this.object = object;
        try {
            String footerLinkText = (String) object.get("footerLinkText");
            String footerText = (String) object.get("footerText");

            footLink.setText(footerLinkText);
            footText.setText(footerText);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
