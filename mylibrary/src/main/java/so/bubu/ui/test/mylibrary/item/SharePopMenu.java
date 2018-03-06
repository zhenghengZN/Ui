package so.bubu.ui.test.mylibrary.item;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Utils.CommonMethod;
import Utils.DeviceHelper;
import Utils.GlideHelper;
import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.bean.ShareBean;
import so.bubu.ui.test.mylibrary.page.common.BaseApplication;

/**
 * @author linhuan on 2016/12/1 下午1:50
 */
public abstract class SharePopMenu<T> extends PopupWindow implements ComRecyclerViewAdapter.OnItemClickLitener {

    private View view;
    private LinearLayout llContent;
    private LinkedList<HashMap<String, Object>> shareBeanLists = new LinkedList<>();
    private int width = ResourceUtil.Dp2Px(43);

    public SharePopMenu(final Activity content, LinkedList<HashMap<String, Object>> shareBeanList) {
        this.shareBeanLists = shareBeanList;
        view = LayoutInflater.from(content).inflate(R.layout.share_popup_menu, null); //ResourceHelper.loadLayout(content, R.layout.share_popup_menu, null);
        llContent = (LinearLayout) view.findViewById(R.id.ll_content);
        RecyclerView rcvContent = (RecyclerView) view.findViewById(R.id.rcv_content);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(content);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvContent.setLayoutManager(linearLayoutManager);

        view.findViewById(R.id.iv_image).setOnClickListener(null);

        ComRecyclerViewAdapter adapter = new ComRecyclerViewAdapter(content, shareBeanList, R.layout.item_share_show) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, Object item, int position) {
                ((LinearLayout.LayoutParams) viewHolder.getView(R.id.ll_content).getLayoutParams()).width = DeviceHelper.getScreenWidth() / 4;
                HashMap<String, Object> object = shareBeanLists.get(position);
                viewHolder.setText(R.id.tv_share, (String) object.get("title"));
//                viewHolder.setImageResource(R.id.iv_share, )
                GlideHelper.displayImageByResizeasBitmap(mContext, CommonMethod.getThumbUrl((String) object.get("url"), width, width), width, width, (ImageView) viewHolder.getView(R.id.iv_share));


                doSomethingInconvert(viewHolder, item, position);
            }
        };

        rcvContent.setAdapter(adapter);

        adapter.setOnItemClickLitener(this);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);

        setContentView(view);
    }

    @Override
    public void onItemClick(View view, Object item, int position) {
        if (itemClick != null) {
            itemClick.onItemClick();
        }
    }

    @Override
    public void onItemLongClick(View view, Object item, int position) {
        if (itemClick != null) {
            itemClick.onItemLongClick();
        }
    }


    public interface PopWindowItemClick {
        void onItemClick();

        void onItemLongClick();
    }

    private PopWindowItemClick itemClick;

    public void setPopWindowItemClick(PopWindowItemClick itemClick) {
        this.itemClick = itemClick;
    }

    //    public abstract void addList(LinkedList<HashMap<String, Object>> shareBeanList);

    public abstract void doSomethingInconvert(RecyclerViewHolder viewHolder, Object item, int position);

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent, Context context) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow

            view.startAnimation(AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.fade_in));
            llContent.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in));

            this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            this.update();
        } else {
            this.dismiss();
        }
    }

}
