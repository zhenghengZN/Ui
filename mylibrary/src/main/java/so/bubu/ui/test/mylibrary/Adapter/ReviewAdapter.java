package so.bubu.ui.test.mylibrary.Adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import Util.DeviceHelper;
import Util.GlideHelper;
import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.CommonAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.ViewHolder;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/15.
 */
public class ReviewAdapter extends CommonAdapter {

    private List<String> urls = new LinkedList<>();
    private Context mContext;
    private int width = (DeviceHelper.getScreenWidth() - ResourceUtil.Dp2Px(56)) / 3;
    public ReviewAdapter(Context context, List mDatas) {
        super(context, mDatas, R.layout.visa_item_grid_item);
        this.urls = mDatas;
        this.mContext = context;
    }


    @Override
    public void convert(ViewHolder viewHolder, Object item, int position) {
        ImageView imageView = viewHolder.getView(R.id.grid_item_img);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = width;
        imageView.setLayoutParams(layoutParams);
        GlideHelper.displayRoundedCornersImage702(mContext,urls.get(position),width,width,ResourceUtil.Dp2Px(4),imageView);
    }
}
