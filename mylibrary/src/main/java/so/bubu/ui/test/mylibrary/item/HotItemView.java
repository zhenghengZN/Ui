package so.bubu.ui.test.mylibrary.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 17/12/26.
 */
public class HotItemView extends LinearLayout {
    private Context mContext;
    private View view;

    public HotItemView(Context context) {
        this(context, null);
    }

    public HotItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.item_main_hot_disney, null, false);
//        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.);
    }

    public void init(){

    }

    public View getItemView() {
        return view;
    }

    public FrameLayout getImageFrameLayout() {
        FrameLayout view = (FrameLayout) this.view.findViewById(R.id.fl_img);
        return view;
    }

    public ImageView getHotImg() {
        ImageView imageview = (ImageView) view.findViewById(R.id.iv_hot);
        return imageview;
    }

    public ImageView getIvNoImg() {
        ImageView imageview = (ImageView) view.findViewById(R.id.iv_no_img);
        return imageview;
    }

    public View getImgBackground() {
        View bgview = view.findViewById(R.id.vw_bg);
        return bgview;
    }

    public TextView getTitleTextView() {
        TextView textview = (TextView) view.findViewById(R.id.tv_name_cn);
        return textview;
    }

    public TextView getSubTitleTextView() {
        TextView textview = (TextView) view.findViewById(R.id.tv_name_desc);
        return textview;
    }

}
