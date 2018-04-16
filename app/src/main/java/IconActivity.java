import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import Iconfont.IconicFontDrawable;
import Iconfont.IconicFontUtil;
import Iconfont.icon.FontIcon;
import so.bubu.ui.test.myapplication.R;

public class IconActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icon);
        IconicFontDrawable iconicFontDrawable = IconicFontUtil.createIconicFontDrawable(FontIcon.ICON_CODE_APPRECIATE);
        ImageView img = findViewById(R.id.icon);
        img.setBackground(iconicFontDrawable);
    }
}

