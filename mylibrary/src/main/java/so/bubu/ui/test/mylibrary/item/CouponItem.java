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
public class CouponItem {

    private Context context;
    private View view;
    public CouponItem(Context context) {
        this.context = context;
        this.view = initView();
    }

    public View getCouponItem(){
        return this.view;
    }

    private View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.taobao_item, null, false);
//        view.setOnClickListener(this);
        return view;
    }

    public ImageView getProImage(){
        ImageView imageview = (ImageView) view.findViewById(R.id.product_img);
        return imageview;
    }

    public TextView getProDesc(){
        TextView textview = (TextView) view.findViewById(R.id.taobao_pro_desc);
        return textview;
    }

    public ImageView getPlatformImage(){
        ImageView imageview = (ImageView) view.findViewById(R.id.taobao_platform);
        return imageview;
    }

    public TextView getDiscountPrice(){
        TextView textview = (TextView) view.findViewById(R.id.taobao_discountPrice);
        return textview;
    }

    public TextView getDiz30Day(){
        TextView textview = (TextView) view.findViewById(R.id.biz30Day);
        return textview;
    }

    public TextView getMuch(){
        TextView textview = (TextView) view.findViewById(R.id.taobao_much);
        return textview;
    }

    public TextView getFinalPrice(){
        TextView textview = (TextView) view.findViewById(R.id.taobao_finalPrice);
        return textview;
    }

    public TextView getCouponAmount(){
        TextView textview = (TextView) view.findViewById(R.id.couponAmount);
        return textview;
    }

//    public View getLine(){
//       return view.findViewById(R.id.weight_line);
//    }


}
