package so.bubu.ui.test.mylibrary.Adapter.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import Util.GlideHelper;

/**
 * Created by zhengheng on 17/12/29.
 */
public class ComViewHolder {

    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;

    private ComViewHolder(Context context, ViewGroup parent, int layoutId,
                          int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        this.context = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ComViewHolder get(Context context, View convertView,
                                    ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ComViewHolder(context, parent, layoutId, position);
        }
        return (ComViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ComViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ComViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ComViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ComViewHolder setImageByUrl(int viewId, String url) {
        if (imagefunction != null) {
            imagefunction.setImageByURl(viewId, url, (ImageView) getView(viewId));
        } else {
            GlideHelper.displayGridByUrl(context, url, (ImageView) getView(viewId));
        }
        return this;
    }

    interface ImageFunction {
        void setImageByURl(int viewId, String url, ImageView imgV);
    }

    private ImageFunction imagefunction;

    public void setImageFunction(ImageFunction imagefunction) {
        this.imagefunction = imagefunction;
    }

    public int getPosition() {
        return mPosition;
    }

}
