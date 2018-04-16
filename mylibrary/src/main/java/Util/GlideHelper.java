package Util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

//import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import so.bubu.ui.test.mylibrary.R;


/**
 * Created by wangwn on 2016/4/22.
 */
public class GlideHelper {


    public void BitmapTask(ImageView img, String url) {
        new BitmapAsyncTask(img).execute(url);
    }

    class BitmapAsyncTask extends AsyncTask<String, String, Bitmap> {

        private ImageView imageView;

        public BitmapAsyncTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap httpBitmap = getHttpBitmap(params[0]);
            return httpBitmap;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;
        try {
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;

    }

//    public static void display(Context context, int resId, ImageView tagetView) {
//
//        Glide
//                .with(context)
//                .load(resId)
//                .apply(options)
//                .into(tagetView);
//    }

//    /**
//     * 加载圆角图片
//     *
//     * @param context
//     * @param resId
//     * @param targetWidth
//     * @param targetHeight
//     * @param radis
//     * @param targetView
//     */
//    public static void displayRoundedCornersImage(Context context, int resId, int targetWidth, int targetHeight, int radis, ImageView targetView) {
//
//        Picasso
//                .with(context)
//                .load(resId)
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.loading)
//                .resize(targetWidth, targetHeight)
//                .centerCrop()
//                .transform(new Util.transformation.RoundedCornersTransformation(radis, 0))
//                .into(targetView);
//
//    }


    public static void displayRoundedCornersImageNoError(Context context, String url, int targetWidth, int targetHeight, int radis, ImageView targetView) {

        RequestOptions options =new RequestOptions()
                .centerCrop()
                .override(targetWidth, targetHeight)
                .placeholder(R.drawable.loading)
                .bitmapTransform(new RoundedCornersTransformation(context, radis, 0))
                .error(R.drawable.loading);

        Glide
                .with(context)
                .load(url)
                .apply(options)
                .into(targetView);

    }

    public static void displayRoundedCornersImage702(Context context, String url, int targetWidth, int targetHeight, int radis, ImageView targetView) {

        Picasso
                .with(context)
                .load(url)
                .placeholder(R.drawable.loading_702)
                .error(R.drawable.loading_702)
                .resize(targetWidth, targetHeight)
                .centerCrop()
                .transform(new Util.transformation.RoundedCornersTransformation(radis, 0))
                .into(targetView);

    }

    public static void displayRoundedCornersImage702(Context context, String url, int targetWidth, int targetHeight, int radis, ImageView targetView, Util.transformation.RoundedCornersTransformation.CornerType cornerType) {

        Picasso
                .with(context)
                .load(url)
                .placeholder(R.drawable.loading_702)
                .error(R.drawable.loading_702)
                .resize(targetWidth, targetHeight)
                .centerCrop()
                .transform(new Util.transformation.RoundedCornersTransformation(radis, 0, cornerType))
                .into(targetView);

    }

    public static void display(Context context, String url, int width, int height, ImageView tagetView) {
        RequestOptions options = new RequestOptions()
                .override(width, height)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading);
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .into(tagetView);
    }

    public static void display(Context context, String url, ImageView tagetView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading);

        Glide
                .with(context)
                .load(url)
                .apply(options)
                .into(tagetView);
    }

    /**
     * 显示本地视频
     *
     * @param context
     * @param videoPath
     * @param tagetView
     */
    public static void loadLocalVideo(Context context, String videoPath, ImageView tagetView) {

        Glide
                .with(context)
                .load(Uri.fromFile(new File(videoPath)))
                .into(tagetView);
    }

    public static void displayImageByResize(Context context, String url, int targetWidth, int targetHeight, ImageView targetView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .override(targetWidth, targetHeight)
                .centerCrop();
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .into(targetView);
    }


    public static void displayImageByResizeasBitmap(Context context, String url, int targetWidth, int targetHeight, ImageView targetView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .override(targetWidth, targetHeight)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();

        Glide
                .with(context)
                .asBitmap()
                .load(url)
                .into(targetView);
    }

    public static void displayGridByUrl(Context context, String url, ImageView targetView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();

        Glide
                .with(context)
                .asBitmap()
                .load(url)
                .into(targetView);
    }
}
