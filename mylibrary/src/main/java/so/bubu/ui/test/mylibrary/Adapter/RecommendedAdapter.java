package so.bubu.ui.test.mylibrary.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Utils.CommonMethod;
import Utils.DeviceHelper;
import Utils.GlideHelper;
import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.bean.ArticleRespBean;
import so.bubu.ui.test.mylibrary.wiget.ExpandableTextView;

/**
 * Created by zhengheng on 18/1/11.
 */
public abstract class RecommendedAdapter extends ComRecyclerViewAdapter  {

    private LinkedList<HashMap<String, Object>> mDatasList;
    private Context mContext;
    private int width, height;
    private Map<Integer, Boolean> moreMap;

    public RecommendedAdapter(Context context, LinkedList<HashMap<String, Object>> mDatas) {
        super(context, mDatas, R.layout.item_commended_content);
        this.mDatasList = mDatas;
        this.mContext = context;
        width = DeviceHelper.getScreenWidth() - ResourceUtil.Dp2Px(22 * 2);
        height = width * (320 + 11 * 2) / 628;
        moreMap = new HashMap<>();
    }

    @Override
    public void convert(RecyclerViewHolder viewHolder, Object item, int position) {
        HashMap<String, Object> objectHashMap = mDatasList.get(position);
        viewHolder.setImageResource(R.id.iv_go, R.drawable.back);
        viewHolder.setText(R.id.tv_title, (String) objectHashMap.get("title"));
        viewHolder.setText(R.id.jtv_content, (String) objectHashMap.get("content"));

        GlideHelper.displayRoundedCornersImage702(mContext, CommonMethod.getThumbUrl((String) objectHashMap.get("url"), width, height), width, height, ResourceUtil.Dp2Px(4), (ImageView) viewHolder.getView(R.id.iv_recommended));
        viewHolder.setImageResource(R.id.iv_like, R.drawable.back);
        viewHolder.setImageResource(R.id.iv_comment, R.drawable.back);
        viewHolder.setText(R.id.tv_like, (String) objectHashMap.get("like"));
        viewHolder.setText(R.id.tv_comment, (String) objectHashMap.get("comment"));
//        setContent(position, (String) objectHashMap.get("content"), viewHolder);
        doOther(viewHolder, mDatasList.get(position), position);
    }

    private void setContent(int position, String content, RecyclerViewHolder viewHolder) {
        if (moreMap.containsKey(position)) {
            setVisiblity(viewHolder,View.VISIBLE, View.GONE);
//            holder.jtvContent.setText(content);
            viewHolder.setText(R.id.jtv_content,content);
        } else {

            setVisiblity(viewHolder,View.GONE, View.VISIBLE);
            ExpandableTextView tvContent = viewHolder.getView(R.id.etx_content);
            tvContent.setText(content);
            tvContent.setOnExpandStateChangeListener(new RecommendedStateChange(position));
            tvContent.setIsShowAll(moreMap.containsKey(position) ? true : false, position);
        }
    }

    private void setVisiblity(RecyclerViewHolder viewHolder,int jtvStatus, int tvStatus) {
        viewHolder.getView(R.id.jtv_content).setVisibility(jtvStatus);
        viewHolder.getView(R.id.etx_content).setVisibility(tvStatus);
    }

    public abstract void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position);


    class RecommendedStateChange implements ExpandableTextView.OnExpandStateChangeListener {

        private int position;

        public RecommendedStateChange(int position) {
            this.position = position;
        }

        @Override
        public void onExpandStateChanged(TextView textView, boolean isExpanded) {
            moreMap.put(position, isExpanded);
        }

        @Override
        public void onGoneClick() {

        }

        @Override
        public void onOverLine() {
            moreMap.put(position, true);
            notifyDataSetChanged();
        }
    }
}
