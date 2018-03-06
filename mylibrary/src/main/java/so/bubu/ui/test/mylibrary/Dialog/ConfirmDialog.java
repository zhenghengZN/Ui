package so.bubu.ui.test.mylibrary.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Utils.MyJsonUtil;
import Utils.StringUtils;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/8.
 */
public class ConfirmDialog extends Dialog implements View.OnClickListener {
    public ConfirmDialog(Context context) {
        super(context);
    }

    private TextView major, sup, content, dialogTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmdialog);
        dialogTitle = (TextView) findViewById(R.id.confirmdialog_title);
        content = (TextView) findViewById(R.id.confirmdialog_content);
        major = (TextView) findViewById(R.id.major);
        major.setOnClickListener(this);
        sup = (TextView) findViewById(R.id.sup);
        sup.setOnClickListener(this);

        if (StringUtils.isNull(title)) {
            dialogTitle.setText(title);
        }

        if (StringUtils.isNull(desc)) {
            content.setText(desc);
        }

        if (StringUtils.isNull(majorTitle)) {
            major.setText(majorTitle);
        }

        if (StringUtils.isNull(supTitle)) {
            sup.setText(supTitle);
        }

        setContentColor();
    }

    public void setContentColor() {
        if (!StringUtils.isNull(title)) {
            sup.setVisibility(View.GONE);
            content.setTextColor(Color.BLACK);
            dialogTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.major) {
            Toast.makeText(getContext(), majorUrl, Toast.LENGTH_LONG).show();
        } else if (i == R.id.sup) {
            Toast.makeText(getContext(), supUrl, Toast.LENGTH_LONG).show();
        }
        dismiss();
    }

    private String title, desc, majorUrl, supUrl, majorTitle, supTitle;

    public void init(JSONObject objects) {
        HashMap<String, Object> object = MyJsonUtil.JSONObject2HashMap(objects);
        title = (String) object.get("title");
        desc = (String) object.get("desc");
        majorUrl = (String) object.get("majorUrl");
        supUrl = (String) object.get("supUrl");
        majorTitle = (String) object.get("majorTitle");
        supTitle = (String) object.get("supTitle");

    }
}
