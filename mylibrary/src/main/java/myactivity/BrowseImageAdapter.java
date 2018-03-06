package myactivity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import Utils.CommonMethod;
import Utils.DeviceHelper;
import Utils.GlideHelper;
import so.bubu.ui.test.mylibrary.R;

/**
 * 图片适配器
 *
 * @author linhuan on 16/4/12 上午11:51
 */
public abstract class BrowseImageAdapter extends RecyclerView.Adapter {

    private Activity act;

    private int width;
    private String[] dataList;

    public BrowseImageAdapter(Activity act, String[] dataList) {
        this.act = act;
        this.dataList = dataList;
        width = DeviceHelper.getScreenWidth() / 3;
    }

    @Override
    public int getItemCount() {
        if (dataList == null || dataList.length == 0) {
            return 0;
        } else {
            return dataList.length;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(act).inflate(R.layout.item_browse_img, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.ivShowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageOnClick(act, position, dataList);
            }
        });
        GlideHelper.displayImageByResize(act, CommonMethod.getThumbUrl(dataList[position], width, width), width, width, viewHolder.ivShowImg);
    }

    public abstract void ImageOnClick(Context ctx, int position, String[] dataList);
//    private class ImageOnClick implements View.OnClickListener {
//
//        private int position;
//
//        public ImageOnClick(int position) {
//            this.position = position;
//        }

//        @Override
//        public void onClick(View v) {
//            ImagePageActivity.imageBrower(act, position, dataList);
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivShowImg;

        private ViewHolder(View v) {
            super(v);
            ivShowImg = (ImageView) v.findViewById(R.id.iv_show_img);
            ViewGroup.LayoutParams layoutParams = ivShowImg.getLayoutParams();
            layoutParams.height = width;
            layoutParams.width = width;
        }

    }

}
