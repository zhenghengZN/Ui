package so.bubu.ui.test.mylibrary.Adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.HashMap;

/**
 * Created by zhengheng on 18/1/17.
 */
public class MultipleItem implements MultiItemEntity {
    public static final int COUPONITEM = 1;
    public static final int GRIDCOUPONITEM = 2;
    public static final int HOTITEM = 3;
    public static final int VIDEOITEM = 4;
    public static final int RECOMMEND = 5;
    public static final int COMMENT = 6;
    public static final int TRAVELS = 7;
    public static final int GRIDITEM = 8;
    public static final int IMAGEANDTEXT = 9;


    private int itemType;
    private HashMap<String, Object> objects;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public MultipleItem(int itemType, HashMap<String, Object> objects) {
        this.itemType = itemType;
        this.objects = objects;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public HashMap<String, Object> getObjects() {
        return objects;
    }

    public void setObjects(HashMap<String, Object> objects) {
        this.objects = objects;
    }
}
