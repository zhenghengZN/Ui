package so.bubu.ui.test.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;

import Iconfont.IconicFontDrawable;
import Iconfont.IconicFontUtil;
import Iconfont.icon.FontIcon;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icon);
        setContentView(R.layout.icon);
        IconicFontDrawable iconicFontDrawable = IconicFontUtil.createIconicFontDrawable(FontIcon.ICON_CODE_APPRECIATE);
        ImageView img = findViewById(R.id.icon);
        img.setBackground(iconicFontDrawable);
    }

}
