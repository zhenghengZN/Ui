package so.bubu.ui.test.mylibrary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Util.CommonMethod;
import Util.DeviceHelper;
import Util.GlideHelper;
import Util.ResourceUtil;
import Util.transformation.RoundedCornersTransformation;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.page.helper.Helper;
import so.bubu.ui.test.mylibrary.wiget.CircleImageView;
import so.bubu.ui.test.mylibrary.wiget.NoScrollGridview;

/**
 * Created by zhengheng on 18/1/16.
 */
public abstract class TypeDiffItemAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    private List list;
    private int mWidth, mHeight;
    private FrameLayout.LayoutParams leftLayoutParams, rightLayoutParams, nameLeft, nameRight;
    private WindowManager wm;

    public TypeDiffItemAdapter(List list) {
        super(list);
        this.list = list;

        addItemType(MultipleItem.COUPONITEM, R.layout.taobao_item);
        addItemType(MultipleItem.GRIDCOUPONITEM, R.layout.taobao_item2);
        addItemType(MultipleItem.RECOMMEND, R.layout.item_commended_content);
        addItemType(MultipleItem.HOTITEM, R.layout.item_main_hot_disney);
        addItemType(MultipleItem.VIDEOITEM, R.layout.video_item);
        addItemType(MultipleItem.COMMENT, R.layout.comment_list_item);
        addItemType(MultipleItem.TRAVELS, R.layout.travels_item);
        addItemType(MultipleItem.GRIDITEM, R.layout.item_main_type_disney);
        addItemType(MultipleItem.IMAGEANDTEXT, R.layout.mine_cell);
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = TypeDiffItemAdapter.this.getItemViewType(position);
                    switch (type) {
                        case MultipleItem.GRIDCOUPONITEM:
                            return gridManager.getSpanCount()/2;
                        case MultipleItem.GRIDITEM:
                            return gridManager.getSpanCount()/4;
                        default:
                            return gridManager.getSpanCount();
                    }
                }
            });
        }
    }

    @Override
    protected void convert(BaseViewHolder holder, MultipleItem item) {
        HashMap<String, Object> object = item.getObjects();
//        int position = getParentPosition(item);
//        LinkedList<HashMap<String, Object>> objects = item.getObjects();
//        HashMap<String, Object> object = objects.get(0);
        switch (holder.getItemViewType()) {
            case MultipleItem.COUPONITEM:
                holder.setText(R.id.taobao_pro_desc, (String) object.get("title"));
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
                CouponsItemAdapter.displayImageByResizeasBitmap(mContext, CommonMethod.getThumbUrl((String) object.get("picUrl"), ResourceUtil.Dp2Px(115), ResourceUtil.Dp2Px(115)), ResourceUtil.Dp2Px(115), ResourceUtil.Dp2Px(115), (ImageView) holder.getView(R.id.product_img));
                break;

            case MultipleItem.GRIDCOUPONITEM:
                wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                mWidth = wm.getDefaultDisplay().getWidth() / 2;
                holder.setText(R.id.taobao_pro_desc, (String) object.get("title"));
                holder.setText(R.id.taobao_finalPrice, object.get("finalPrice") + " ");

                ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_content).getLayoutParams();
//        layoutParams.width = mGridWidth;
                layoutParams.height = mWidth + ResourceUtil.Dp2Px(110);
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
                GridCouponItemAdapter.displayImageByResizeasBitmap(mContext, CommonMethod.getThumbUrl((String) object.get("picUrl"), mWidth, mWidth), mWidth, mWidth, (ImageView) holder.getView(R.id.product_img));
                break;

            case MultipleItem.RECOMMEND:
                mWidth = DeviceHelper.getScreenWidth() - ResourceUtil.Dp2Px(22 * 2);
                mHeight = mWidth * (320 + 11 * 2) / 628;
                holder.setImageResource(R.id.iv_go, R.drawable.back);
                holder.setText(R.id.tv_title, (String) object.get("title"));
                holder.setText(R.id.jtv_content, (String) object.get("content"));

                GlideHelper.displayRoundedCornersImage702(mContext, CommonMethod.getThumbUrl((String) object.get("url"), mWidth, mHeight), mWidth, mHeight, ResourceUtil.Dp2Px(4), (ImageView) holder.getView(R.id.iv_recommended));
                holder.setImageResource(R.id.iv_like, R.drawable.back);
                holder.setImageResource(R.id.iv_comment, R.drawable.back);
                holder.setText(R.id.tv_like, (String) object.get("like"));
                holder.setText(R.id.tv_comment, (String) object.get("comment"));
                break;

            case MultipleItem.HOTITEM:
                mWidth = DeviceHelper.getScreenWidth() - ResourceUtil.Dp2Px(12 * 2);
                mHeight = mWidth * 280 / 702;
                GlideHelper.displayRoundedCornersImage702(mContext, CommonMethod.getThumbUrl((String) object.get("backgroundImageUrl350"), mWidth, mHeight), mWidth, mHeight, ResourceUtil.Dp2Px(4), (ImageView) holder.getView(R.id.iv_hot), RoundedCornersTransformation.CornerType.ALL);

                holder.setText(R.id.tv_name_desc, (String) object.get("subtitle"));
                holder.setText(R.id.tv_name_cn, (String) object.get("subtitle"));
                break;

            case MultipleItem.VIDEOITEM:
                mWidth = DeviceHelper.getScreenWidth() - ResourceUtil.Dp2Px(12 * 2);
                mHeight = mWidth * 280 / 702;
                GlideHelper.displayRoundedCornersImage702(mContext, CommonMethod.getThumbUrl((String) object.get("backgroundImageUrl"), mWidth, mHeight), mWidth, mHeight, ResourceUtil.Dp2Px(4), (ImageView) holder.getView(R.id.iv_hot), RoundedCornersTransformation.CornerType.ALL);
                holder.setText(R.id.video_title, (String) object.get("subtitle"));
                break;

            case MultipleItem.COMMENT:
                holder.setText(R.id.comment_list_content, (String) object.get("content"));
//                holder.setText(R.id.comment_list_time, CommonMethod.showTime((String) object.get("time")));
                holder.setText(R.id.comment_list_time,  (String)object.get("time"));

                holder.setText(R.id.comment_user_name, (String) object.get("userName"));

                NoScrollGridview view = holder.getView(R.id.review_item_gridview);
                final List<String> urls = (LinkedList) object.get("urls");
                view.setAdapter(new ReviewAdapter(mContext, urls));
                view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ImagePageActivity.imageBrower((Activity) mContext, position, urls.toArray(new String[urls.size()]));
                        setGridItemOnclick((Activity) mContext, position, urls, parent, view);
                    }
                });


                final CircleImageView iconImg = holder.getView(R.id.comment_user_icon);

                SimpleTarget target = new SimpleTarget<Bitmap>(ResourceUtil.Dp2Px(44), ResourceUtil.Dp2Px(44)) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iconImg.setImageBitmap(resource);
                    }
                };

                if (Helper.isNotEmpty((String) object.get("userPicUrl"))) {
                    Glide.with(mContext.getApplicationContext())
                            .asBitmap()
                            .load((String) object.get("userPicUrl"))
                            .into(target);
                } else {
                    iconImg.setImageResource(R.drawable.pho_user_head);
                }
                break;
            case MultipleItem.TRAVELS:
                mWidth = ResourceUtil.Dp2Px(115);
                GlideHelper.displayImageByResizeasBitmap(mContext, CommonMethod.getThumbUrl((String) object.get("url"), mWidth, mWidth), mWidth, mWidth, (ImageView) holder.getView(R.id.product_img));
                holder.setText(R.id.taobao_pro_desc, (String) object.get("subtitle"));
                holder.setText(R.id.taobao_much, (String) object.get("title"));
                break;
            case MultipleItem.GRIDITEM:
                mWidth = ResourceUtil.Dp2Px(48);
                mHeight = ResourceUtil.Dp2Px(40);

                leftLayoutParams = new FrameLayout.LayoutParams(mWidth, mHeight);
                leftLayoutParams.rightMargin = ResourceUtil.Dp2Px(5f);
                rightLayoutParams = new FrameLayout.LayoutParams(mWidth, mHeight);
                rightLayoutParams.leftMargin = leftLayoutParams.rightMargin;

                nameLeft = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                nameLeft.rightMargin = ResourceUtil.Dp2Px(2f);
                nameLeft.gravity = Gravity.CENTER;
                nameRight = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                nameRight.leftMargin = nameLeft.rightMargin;
                nameRight.gravity = Gravity.CENTER;
                holder.setText(R.id.tv_chine_name, (String) object.get("title"));
                GlideHelper.displayRoundedCornersImageNoError(mContext, (String) object.get("url"), mWidth, mHeight, ResourceUtil.Dp2Px(4), (ImageView) holder.getView(R.id.iv_type));

                break;
            case MultipleItem.IMAGEANDTEXT:
                holder.setText(R.id.tv_name, (String) object.get("title"));
                holder.setText(R.id.tv_detail, (String) object.get("detail"));
                break;
        }
        doOther(holder, item);
    }

    public abstract void doOther(BaseViewHolder viewHolder, MultipleItem multipleItem);

    public abstract void setGridItemOnclick(Activity mContext, int position, List<String> urls, AdapterView<?> parent, View view);
}
