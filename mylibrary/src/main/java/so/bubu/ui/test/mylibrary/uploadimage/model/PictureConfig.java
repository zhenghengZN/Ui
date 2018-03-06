package so.bubu.ui.test.mylibrary.uploadimage.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.uploadimage.ui.PictureExternalPreviewActivity;
import so.bubu.ui.test.mylibrary.uploadimage.ui.PictureImageGridActivity;
import so.bubu.ui.test.mylibrary.uploadimage.util.Utils;

public class PictureConfig {
    public FunctionOptions options;
    public static PictureConfig sInstance;

    public static PictureConfig getInstance() {
        if (sInstance == null) {
            synchronized (PictureConfig.class) {
                if (sInstance == null) {
                    sInstance = new PictureConfig();
                }
            }
        }
        return sInstance;
    }

    public PictureConfig() {

    }

    public static OnSelectResultCallback resultCallback;

    public static OnSelectResultCallback getResultCallback() {
        return resultCallback;
    }

    public PictureConfig init(FunctionOptions options) {
        this.options = options;
        return this;
    }

    /**
     * 启动相册
     */
    public void openPhoto(Activity activity, OnSelectResultCallback resultCall) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        if (options == null) {
            options = new FunctionOptions.Builder().create();
        }
        Intent intent = new Intent(activity, PictureImageGridActivity.class);
//        intent.putExtra(FunctionConfig.EXTRA_THIS_CONFIG, options);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FunctionConfig.EXTRA_THIS_CONFIG, options);
        intent.putExtra(FunctionConfig.OPNE_IMAGE_GRID,bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.pull_right_in, 0);
        // 绑定图片接口回调函数事件
        resultCallback = resultCall;
    }

    /**
     * start to camera、preview、crop
     */
    public void startOpenCamera(Activity activity, OnSelectResultCallback resultCall) {
        if (options == null) {
            options = new FunctionOptions.Builder().create();
        }
        Intent intent = new Intent(activity, PictureImageGridActivity.class);
        intent.putExtra(FunctionConfig.EXTRA_THIS_CONFIG, options);
        intent.putExtra(FunctionConfig.FUNCTION_TAKE, true);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade, R.anim.hold);
        // 绑定图片接口回调函数事件
        resultCallback = resultCall;
    }

    /**
     * 外部图片预览
     *
     * @param position
     * @param medias
     */
    public void externalPicturePreview(Activity activity, int position, List<LocalMedia> medias) {
        if (medias != null && medias.size() > 0) {
            Intent intent = new Intent();
            intent.putExtra(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) medias);
            intent.putExtra(FunctionConfig.EXTRA_POSITION, position);
            intent.setClass(activity, PictureExternalPreviewActivity.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.toast_enter, 0);
        }
    }

    /**
     * 外部图片预览
     *
     * @param position
     * @param medias
     */
    public void externalPicturePreview(Activity activity, String directory_path, int position, List<LocalMedia> medias) {
        if (medias != null && medias.size() > 0) {
            Intent intent = new Intent();
            intent.putExtra(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) medias);
            intent.putExtra(FunctionConfig.EXTRA_POSITION, position);
            intent.putExtra(FunctionConfig.DIRECTORY_PATH, directory_path);
            intent.setClass(activity, PictureExternalPreviewActivity.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.toast_enter, 0);
        }
    }

//    /**
//     * 外部视频播放
//     *
//     * @param path
//     */
//    public void externalPictureVideo(Activity activity, String path) {
//        if (!Utils.isNull(path)) {
//            Intent intent = new Intent();
//            intent.putExtra("video_path", path);
//            intent.setClass(activity, PictureVideoPlayActivity.class);
//            activity.startActivity(intent);
//        }
//    }


    /**
     * 处理结果
     */
    public interface OnSelectResultCallback {
        /**
         * 处理成功
         * 多选
         *
         * @param resultList
         */
        void onSelectSuccess(List<LocalMedia> resultList);


    }
}
