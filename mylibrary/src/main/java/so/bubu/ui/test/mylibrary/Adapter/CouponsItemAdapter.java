package so.bubu.ui.test.mylibrary.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.LinkedList;

import Util.CommonMethod;
import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;


/**
 * Created by zhengheng on 17/12/25.
 */
public abstract class CouponsItemAdapter extends ComRecyclerViewAdapter {

    private LinkedList<HashMap<String, Object>> mDatasList;

    public CouponsItemAdapter(Context context, LinkedList<HashMap<String, Object>> mDatas) {
        super(context, mDatas, R.layout.taobao_item);
        mDatasList = mDatas;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object item, int position) {

        HashMap<String, Object> object = mDatasList.get(position);
        holder.setText(R.id.taobao_pro_desc, (String)object.get("title"));
        holder.setText(R.id.taobao_finalPrice, object.get("finalPrice") + " ");

        holder.setText(R.id.biz30Day, "月销:" + object.get("biz30Day"));
        holder.setText(R.id.couponAmount, "立减 " + object.get("couponAmount") + " 元");

        if (("天猫").equalsIgnoreCase((String) object.get("platform"))) {
            holder.setImageResource(R.id.taobao_platform, R.drawable.tmall_logo_30);
            holder.setText(R.id.taobao_discountPrice, "天猫价 ¥" + object.get("discountPrice"));
        } else {
            holder.setImageResource(R.id.taobao_platform, R.drawable.taobao_logo_30);
            holder.setText(R.id.taobao_discountPrice, "淘宝价 ¥" + object.get("discountPrice"));
        }
        CouponsItemAdapter.displayImageByResizeasBitmap(mContext, CommonMethod.getThumbUrl((String)object.get("picUrl"), ResourceUtil.Dp2Px(115), ResourceUtil.Dp2Px(115)), ResourceUtil.Dp2Px(115), ResourceUtil.Dp2Px(115), (ImageView) holder.getView(R.id.product_img));
        doOther(holder, mDatasList.get(position), position);
        holder.itemView.setTag(position);
    }


    public abstract void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position);

    public static void displayImageByResizeasBitmap(Context context, String url, int targetWidth, int targetHeight, ImageView targetView) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.imagebackground)
                .error(R.drawable.imagebackground)
                .override(targetWidth, targetHeight);

        Glide
                .with(context)
                .asBitmap()
                .load(url)
                .apply(options)
                .into(targetView);
    }
}
