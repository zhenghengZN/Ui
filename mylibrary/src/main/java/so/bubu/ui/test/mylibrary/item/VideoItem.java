package so.bubu.ui.test.mylibrary.item;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 17/12/26.
 */
public class VideoItem {
    private Context mContext;
    private View view;

    public VideoItem(Context context) {
        mContext = context;
        view = LayoutInflater.from(context).inflate(R.layout.video_item, null, false);
    }

    public View getItemView(){
        return view;
    }

    public FrameLayout getImgLayout() {
        FrameLayout layoutview = (FrameLayout) view.findViewById(R.id.fl_img);
        return layoutview;
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

    public ImageView getPlayImg() {
        ImageView img = (ImageView) view.findViewById(R.id.video_play);
        return img;
    }

    public TextView getVideoTitle() {
        TextView text = (TextView) view.findViewById(R.id.video_title);
        return text;
    }

}
