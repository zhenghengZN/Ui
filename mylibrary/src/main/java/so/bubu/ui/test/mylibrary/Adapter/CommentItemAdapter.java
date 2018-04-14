package so.bubu.ui.test.mylibrary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Util.CommonMethod;
import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.page.helper.Helper;
import so.bubu.ui.test.mylibrary.wiget.CircleImageView;

/**
 * Created by zhengheng on 18/1/15.
 */
public abstract class CommentItemAdapter extends ComRecyclerViewAdapter {

    private LinkedList<HashMap<String, Object>> mDatasList;
    private Context mContext;

    public CommentItemAdapter(Context context, LinkedList<HashMap<String, Object>> mDatas) {
        super(context, mDatas, R.layout.comment_list_item);
        this.mContext = context;
        this.mDatasList = mDatas;
    }

    @Override
    public void convert(RecyclerViewHolder viewHolder, Object item, int position) {
        HashMap<String, Object> object = mDatasList.get(position);

        viewHolder.setText(R.id.comment_list_content, (String) object.get("content"));
        viewHolder.setText(R.id.comment_list_time, CommonMethod.showTime((String) object.get("time")));

        viewHolder.setText(R.id.comment_user_name, (String) object.get("userName"));

        GridView view = viewHolder.getView(R.id.review_item_gridview);
        final List<String> urls = (LinkedList) object.get("urls");
        view.setAdapter(new ReviewAdapter(mContext,urls));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ImagePageActivity.imageBrower((Activity)mContext, position, urls.toArray(new String[urls.size()]));
                setGridItemOnclick((Activity)mContext, position,urls,parent,view);
            }
        });



        final CircleImageView iconImg = viewHolder.getView(R.id.comment_user_icon);

        SimpleTarget target = new SimpleTarget<Bitmap>(ResourceUtil.Dp2Px(44), ResourceUtil.Dp2Px(44)) {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                iconImg.setImageBitmap(bitmap);
            }
        };

        if (Helper.isNotEmpty((String) object.get("userPicUrl"))) {
            Glide.with(mContext.getApplicationContext())
                    .load((String)object.get("userPicUrl"))
                    .asBitmap()
                    .into(target);
        } else {
            iconImg.setImageResource(R.drawable.pho_user_head);
        }


        doOther(viewHolder, mDatasList.get(position), position);
        viewHolder.itemView.setTag(position);
    }


    public abstract void setGridItemOnclick(Activity mContext, int position, List<String> urls, AdapterView<?> parent, View view);
    public abstract void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position);

}
