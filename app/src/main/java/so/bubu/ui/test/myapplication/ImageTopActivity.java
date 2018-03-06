package so.bubu.ui.test.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.page.TopImageActivity;

/**
 * Created by zhengheng on 18/1/15.
 */
public class ImageTopActivity extends TopImageActivity {
    @Override
    public View addBaseContenetView() {
        ImageView image = (ImageView) findViewById(R.id.iv_big_image);
        image.setImageResource(R.drawable.loading_702);
        ((TextView) findViewById(R.id.tv_img_num)).setText(getResources().getString(R.string.text_menu_img_num, 1));
        ImageView img = (ImageView) findViewById(R.id.iv_money);
        img.setImageResource(R.drawable.loading);
        findViewById(R.id.ll_img_num).setVisibility(View.VISIBLE);
        return new View(this);
    }

    @Override
    public void doInCreateView() {

    }

    @Override
    public void setFlPhotoLayoutClick(View v) {
        Intent intent = new Intent(ImageTopActivity.this, PhotoActivity.class);
        Bundle bundle = new Bundle();
        String[] img = {
                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg",
                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg",
                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg",
                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg",
                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg",
                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg",
                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg"
        };
        bundle.putStringArray(PhotoActivity.URL, img);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
