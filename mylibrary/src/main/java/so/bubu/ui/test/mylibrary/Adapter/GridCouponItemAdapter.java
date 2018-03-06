package so.bubu.ui.test.mylibrary.Adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.HashMap;
import java.util.LinkedList;

import Utils.CommonMethod;
import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.bean.CouponsItemBean;

/**
 * Created by zhengheng on 17/12/28.
 */
public abstract class GridCouponItemAdapter extends ComRecyclerViewAdapter {
    private LinkedList<HashMap<String, Object>>  mDatasList;
    private int mGridWidth;

    public GridCouponItemAdapter(Context context, LinkedList<HashMap<String, Object>> mDatas) {
        super(context, mDatas, R.layout.taobao_item2);
        mDatasList = mDatas;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        // 计算显示大小
        mGridWidth = wm.getDefaultDisplay().getWidth() / 2;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object item, int position) {
        HashMap<String, Object> object = mDatasList.get(position);

        holder.setText(R.id.taobao_pro_desc, (String) object.get("title"));
        holder.setText(R.id.taobao_finalPrice, object.get("finalPrice") + " ");

        ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_content).getLayoutParams();
//        layoutParams.width = mGridWidth;
        layoutParams.height = mGridWidth + ResourceUtil.Dp2Px(110);
        holder.getView(R.id.rl_content).setLayoutParams(layoutParams);
        holder.setText(R.id.biz30Day, "月销:" + object.get("biz30Day"));
        holder.setText(R.id.couponAmount, "立减 " + object.get("couponAmount") + " 元");
        if (("天猫").equalsIgnoreCase((String) object.get("platform"))) {
            holder.setImageResource(R.id.taobao_platform, R.drawable.tmall_logo_30);
            holder.setText(R.id.taobao_discountPrice, "天猫价 ¥" + object.get("discountPrice"));
        } else {
            holder.setImageResource(R.id.taobao_platform, R.drawable.taobao_logo_30);
            holder.setText(R.id.taobao_discountPrice, "淘宝价 ¥" + object.get("discountPrice"));
        }
        GridCouponItemAdapter.displayImageByResizeasBitmap(mContext, CommonMethod.getThumbUrl((String)object.get("picUrl"), mGridWidth, mGridWidth), mGridWidth, mGridWidth, (ImageView) holder.getView(R.id.product_img));
        doOther(holder, mDatasList.get(position), position);
        holder.itemView.setTag(position);
    }

    public abstract void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position);


    public static void displayImageByResizeasBitmap(Context context, String url, int targetWidth, int targetHeight, ImageView targetView) {

        Glide
                .with(context)
                .load(url)
//                .crossFade()
                .asBitmap()
                .placeholder(R.drawable.imagebackground)
                .error(R.drawable.imagebackground)
                .override(targetWidth, targetHeight)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
//                .animate(animationObject)
                .into(targetView);
    }
}
