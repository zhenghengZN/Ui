package so.bubu.ui.test.mylibrary.input.checkBox;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;

import so.bubu.ui.test.mylibrary.R;


/**
 * Created by zhengheng on 18/1/31.
 */
public class AboutCheckBox extends LinearLayout {
    public AboutCheckBox(Context context) {
        this(context, null);
    }

    private View inflate;
    private CheckBox checkbox;

    public AboutCheckBox(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setBackgroundColor(Color.TRANSPARENT);
        inflate = LayoutInflater.from(context).inflate(R.layout.aboutcheckbox, null, false);
        checkbox = (CheckBox) inflate.findViewById(R.id.aboutcheckbox);
        checkbox.setBackgroundColor(Color.TRANSPARENT);
        final TextView textView = checkbox.getTextView();
        String s = "阅读并同意 <<相关条款>>";
        SpannableString spannableString = new SpannableString(s);
        //设置文字的单击事件
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(context, "xgtk", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ds.linkColor);
                ds.setUnderlineText(false);
            }
        }, 6, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 6, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        checkbox.setText(spannableString);
        this.addView(inflate);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox.toggle();
                objects.put("selectedValue", true);

            }
        });
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox.toggle();
                objects.put("selectedValue", true);

            }
        });

    }

    private LinkedHashMap<String, Object> objects;

    public void init(LinkedHashMap<String, Object> objects) {
        this.objects = objects;
    }

    public boolean isCheck() {
        return checkbox.isChecked();
    }

}
