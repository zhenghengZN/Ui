package so.bubu.ui.test.mylibrary.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.R;


/**
 * Created by zhengheng on 17/12/25.
 */
public class ImageAndTextItem {

    private Context context;
    private View view;

    public ImageAndTextItem(Context context) {
        this.context = context;
        this.view = initView();
    }

    public View getImageAndTextItem() {
        return this.view;
    }

    private View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_cell, null, false);
        return view;
    }

    public ImageView getLeftImageView() {
        ImageView imageView = (ImageView) view.findViewById(R.id.left_icon);
        return imageView;
    }

    public ImageView getRightImageView(){
        ImageView imageView = (ImageView) view.findViewById(R.id.right_icon);
        return imageView;
    }

    public TextView getTitleTextView(){
        TextView textView = (TextView) view.findViewById(R.id.tv_name);
        return textView;
    }

    public TextView getSubTitleTextView(){
        TextView textView = (TextView) view.findViewById(R.id.tv_detail);
        return textView;
    }

    public TextView getDraftNum(){
        TextView textView = (TextView) view.findViewById(R.id.tv_draft_num);
        return textView;
    }


}
