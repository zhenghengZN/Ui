package so.bubu.ui.test.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;

import Iconfont.IconicFontDrawable;
import Iconfont.IconicFontUtil;
import Iconfont.icon.FontIcon;
import Util.IconFontUtil;
import so.bubu.ui.test.mylibrary.page.common.BaseApplication;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icon);
        setContentView(R.layout.icon);
//        IconicFontDrawable iconicFontDrawable = IconicFontUtil.createIconicFontDrawable(FontIcon.getFontIcon("0xe734"));
//        String s= "0xe"+"644";
//        Integer decode = Integer.decode(s);
//        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(BaseApplication.getInstance(), decode);
//        iconicFontDrawable.setIconColor(BaseApplication.getInstance().getResources().getColor(so.bubu.ui.test.mylibrary.R.color.color_82cd6b));
        IconicFontDrawable iconfont = IconFontUtil.createIconfont("&#xe734;", Color.BLACK);
        ImageView img = findViewById(R.id.icon);
        img.setBackground(iconfont);


        final String CUSTOM_FONT_PATH = "iconfont.ttf";

//        final char SPACE_SHUTTLE_CODE = '\uE644';
//        ImageView drawableImageView = (ImageView) findViewById(R.id.icon);
//        FontDrawable spaceShuttle = new FontDrawable.Builder(this, SPACE_SHUTTLE_CODE, CUSTOM_FONT_PATH)
//                .setSizeDp(100)
//                .setColor(Color.BLACK)
//                .build();
//        drawableImageView.setImageDrawable(spaceShuttle);
    }

}
