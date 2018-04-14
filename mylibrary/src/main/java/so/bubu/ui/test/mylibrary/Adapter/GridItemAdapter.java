package so.bubu.ui.test.mylibrary.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.LinkedList;

import Util.GlideHelper;
import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/10.
 */
public abstract class GridItemAdapter extends ComRecyclerViewAdapter {

    private int mWidth, mHeight;
    private FrameLayout.LayoutParams leftLayoutParams, rightLayoutParams, nameLeft, nameRight;
    private LinkedList<HashMap<String, Object>> mDatasList;
    private Context mContext;

    public GridItemAdapter(Context context, LinkedList mDatas) {
        super(context, mDatas, R.layout.item_main_type_disney);
        mDatasList = mDatas;
        mContext = context;
        mWidth = ResourceUtil.Dp2Px(48);
        mHeight = ResourceUtil.Dp2Px(40);

        leftLayoutParams = new FrameLayout.LayoutParams(mWidth, mHeight);
        leftLayoutParams.rightMargin = ResourceUtil.Dp2Px(5f);
        rightLayoutParams = new FrameLayout.LayoutParams(mWidth, mHeight);
        rightLayoutParams.leftMargin = leftLayoutParams.rightMargin;

        nameLeft = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameLeft.rightMargin = ResourceUtil.Dp2Px(2f);
//        nameLeft.topMargin = nameLeft.leftMargin;
        nameLeft.gravity = Gravity.CENTER;
        nameRight = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameRight.leftMargin = nameLeft.rightMargin;
//        nameRight.topMargin = nameLeft.leftMargin;
        nameRight.gravity = Gravity.CENTER;
    }

    public abstract void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position);

    @Override
    public void convert(RecyclerViewHolder viewHolder, Object item, int position) {
        HashMap<String, Object> gridBean = mDatasList.get(position);
        viewHolder.setText(R.id.tv_chine_name, (String) gridBean.get("title"));
        GlideHelper.displayRoundedCornersImageNoError(mContext, (String)gridBean.get("url"), mWidth, mHeight, ResourceUtil.Dp2Px(4), (ImageView) viewHolder.getView(R.id.iv_type));
        doOther(viewHolder, mDatasList.get(position), position);
        viewHolder.itemView.setTag(position);
    }

}
