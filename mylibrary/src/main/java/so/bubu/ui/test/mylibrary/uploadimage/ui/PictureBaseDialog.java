package so.bubu.ui.test.mylibrary.uploadimage.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import so.bubu.ui.test.mylibrary.uploadimage.model.FunctionConfig;
import so.bubu.ui.test.mylibrary.uploadimage.model.FunctionOptions;
import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMedia;

/**
 * Created by zhengheng on 18/3/5.
 */
public class PictureBaseDialog extends Dialog {

    protected Context mContext;
    protected int type = 0;
    protected int maxSelectNum = 0;
    protected int minSelectNum = 0;
    protected int spanCount = 4;
    protected int copyMode = 0;
    protected boolean showCamera = false;
    protected boolean circularCut = false;
    protected boolean enablePreview = false;
    protected boolean enableCrop = false;
    protected boolean enablePreviewVideo = true;
    protected int selectMode = FunctionConfig.MODE_MULTIPLE;
    protected int backgroundColor = 0;
    protected int cb_drawable = 0;
    protected int qq_theme = 0;
    protected int cropW = 0;
    protected int cropH = 0;
    protected int recordVideoSecond = 0;
    protected int definition = 3;
    protected boolean isCompress;
    protected boolean isShowOriginal = true;
    protected boolean is_checked_num;
    protected int previewColor; // 底部预览字体颜色
    protected int completeColor; // 底部完成字体颜色
    protected int bottomBgColor; // 底部背景色
    protected int previewBottomBgColor; // 预览底部背景色
    protected int previewTopBgColor; // 预览图片标题背景色
    protected int compressQuality = 0;// 压缩图片质量
    protected List<LocalMedia> selectMedias = new ArrayList<>();
    protected FunctionOptions options;
    protected int compressFlag = 1;
    protected int mScreenWidth = 720;
    protected int mScreenHeight = 1280;
    protected int compressW;
    protected int compressH;
    protected int maxB = 0;
    protected int grade;

    protected Bundle bd;
    public PictureBaseDialog(Context context,Bundle bd) {
        super(context);
        this.bd = bd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        initScreenWidth();
        //TODO  获取imagegridactivity的intent
        options = (FunctionOptions) bd.getSerializable(FunctionConfig.EXTRA_THIS_CONFIG);
//        options = (FunctionOptions) getIntent().getSerializableExtra(FunctionConfig.EXTRA_THIS_CONFIG);
        if (options == null) {
            options = new FunctionOptions.Builder().create();
        }
        type = options.getType();
        showCamera = options.isShowCamera();
        enablePreview = options.isEnablePreview();
        selectMode = options.getSelectMode();
        enableCrop = options.isEnableCrop();
        maxSelectNum = options.getMaxSelectNum();
        minSelectNum = options.getMinSelectNum();
        circularCut = options.isCircularCut();
        copyMode = options.getCropMode();
        enablePreviewVideo = options.isPreviewVideo();
        backgroundColor = options.getThemeStyle();
        cb_drawable = options.getCheckedBoxDrawable();
        qq_theme = options.getQq_theme();
        isCompress = options.isCompress();
        isShowOriginal = options.isShowOriginal();
        spanCount = options.getImageSpanCount();
        cropW = options.getCropW();
        cropH = options.getCropH();
        maxB = options.getMaxB();
        grade = options.getGrade();
        recordVideoSecond = options.getRecordVideoSecond();
        definition = options.getRecordVideoDefinition();
        is_checked_num = options.isCheckNumMode();
        previewColor = options.getPreviewColor();
        completeColor = options.getCompleteColor();
        bottomBgColor = options.getBottomBgColor();
        previewBottomBgColor = options.getPreviewBottomBgColor();
        previewTopBgColor = options.getPreviewTopBgColor();
        compressQuality = options.getCompressQuality();
        selectMedias = options.getSelectMedia();
        compressFlag = options.getCompressFlag();
        compressW = options.getCompressW();
        compressH = options.getCompressH();
    }


    /**
     * 初始化屏幕宽高
     */
    protected void initScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = mContext.getResources().getDisplayMetrics();
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }

    /**
     * 启动相机
     */
    protected void startCamera() {

    }

    /**
     * 读取相册信息
     */
    protected void readLocalMedia() {

    }
}
