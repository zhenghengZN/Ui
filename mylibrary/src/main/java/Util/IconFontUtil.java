package Util;

import Iconfont.IconicFontDrawable;
import so.bubu.ui.test.mylibrary.page.common.BaseApplication;

public class IconFontUtil {
    public static IconicFontDrawable createIconfont(String fontCode, int color) {
        int i = fontCode.indexOf("x");
        String code = fontCode.substring(i, fontCode.length() - 1);
        Integer font = Integer.decode("0" + code);
        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(BaseApplication.getInstance(), font);
        iconicFontDrawable.setIconColor(color);
        return iconicFontDrawable;
    }
}

