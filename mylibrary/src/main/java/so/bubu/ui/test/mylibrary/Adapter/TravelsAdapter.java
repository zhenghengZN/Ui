package so.bubu.ui.test.mylibrary.Adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.LinkedList;

import Util.CommonMethod;
import Util.GlideHelper;
import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/10.
 */
public abstract class TravelsAdapter extends ComRecyclerViewAdapter {

    private int mWidth, mHeight;
    private LinkedList<HashMap<String, Object>> mDatasList;

    public TravelsAdapter(Context context, LinkedList mDatas) {
        super(context, mDatas, R.layout.travels_item);
        mWidth = ResourceUtil.Dp2Px(115);
        mHeight = ResourceUtil.Dp2Px(115);
        mDatasList = mDatas;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object item, int position) {
        HashMap<String, Object> object =  mDatasList.get(position);
        GlideHelper.displayImageByResizeasBitmap(mContext, CommonMethod.getThumbUrl((String)object.get("url"), mWidth, mHeight), mWidth, mHeight, (ImageView) holder.getView(R.id.product_img));
        holder.setText(R.id.taobao_pro_desc, (String)object.get("subtitle"));
        holder.setText(R.id.taobao_much, (String)object.get("title"));
        doOther(holder, mDatasList.get(position), position);
        holder.itemView.setTag(position);
    }

    public abstract void doOther(RecyclerViewHolder viewHolder,  HashMap<String, Object>  item, int position);

}
