package so.bubu.ui.test.mylibrary.uploadimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.uploadimage.adapter.GridImageAdapter;
import so.bubu.ui.test.mylibrary.uploadimage.compress.Luban;
import so.bubu.ui.test.mylibrary.uploadimage.model.FunctionConfig;
import so.bubu.ui.test.mylibrary.uploadimage.model.FunctionOptions;
import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMedia;
import so.bubu.ui.test.mylibrary.uploadimage.model.PictureConfig;
import so.bubu.ui.test.mylibrary.uploadimage.util.FullyGridLayoutManager;
import so.bubu.ui.test.mylibrary.uploadimage.util.SpaceItemDecoration;
import so.bubu.ui.test.mylibrary.uploadimage.util.StorageUtils;

/**
 * Created by zhengheng on 18/3/2.
 */
public class UploadImageView extends LinearLayout {
    public UploadImageView(Context context) {
        this(context, null);
    }

    private TextView UpLoadImageNum;
    private RecyclerView recyclerView;
    private Context ctx;
    private GridImageAdapter adapter;
    private static final int MAX_SELCET_NUM = 9;
    private static final int MAX_IMAGE_SIZE = 202400;
    private List<LocalMedia> selectMedia = new ArrayList<>();

    public UploadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        View view = LayoutInflater.from(context).inflate(R.layout.uploadimageview, this, true);
        UpLoadImageNum = (TextView) view.findViewById(R.id.upload_image_num);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        init();
    }

    public void init() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(ctx, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(20));
        adapter = new GridImageAdapter(ctx, onAddPicClickListener);
        adapter.setList(selectMedia);
        adapter.setSelectMax(MAX_SELCET_NUM);
        recyclerView.setAdapter(adapter);

        //TODO 图片缓存保存路径
        floderPath = StorageUtils.getOwnCacheDirectory(ctx, "/imageCache").toString();
    }

    private int previewColor, completeColor, checkedBoxDrawable, themeStyle, previewBottomBgColor, previewTopBgColor, bottomBgColor;
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {


        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    previewColor = ContextCompat.getColor(ctx, R.color.tab_color_true);
                    completeColor = ContextCompat.getColor(ctx, R.color.tab_color_true);
                    checkedBoxDrawable = R.drawable.select_cb;
                    bottomBgColor = Color.BLACK;
//                    checkedBoxDrawable = 0;
                    themeStyle = ContextCompat.getColor(ctx, R.color.bar_grey);
                    FunctionOptions options = new FunctionOptions.Builder()
                            .setType(FunctionConfig.TYPE_IMAGE) // 图片or视频 FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                            .setCompress(true) //是否压缩
                            .setEnablePixelCompress(true) //是否启用像素压缩
                            .setEnableQualityCompress(true) //是否启质量压缩
                            .setMaxSelectNum(MAX_SELCET_NUM) // 可选择图片的数量
                            .setMinSelectNum(0)// 图片或视频最低选择数量，默认代表无限制
                            .setSelectMode(FunctionConfig.MODE_MULTIPLE) // 单选 or 多选
                            .setShowCamera(true) //是否显示拍照选项 这里自动根据type 启动拍照或录视频
                            .setEnablePreview(true) // 是否打开预览选项
                            .setEnableCrop(false) // 是否打开剪切选项
                            .setCircularCut(false)// 是否采用圆形裁剪
                            .setCheckedBoxDrawable(checkedBoxDrawable)
                            .setRecordVideoDefinition(FunctionConfig.HIGH) // 视频清晰度
                            .setRecordVideoSecond(60) // 视频秒数
                            .setCustomQQ_theme(0)// 可自定义QQ数字风格，不传就默认是蓝色风格
                            .setGif(false)// 是否显示gif图片，默认不显示
//                            .setCropW(cropW) // cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
//                            .setCropH(cropH) // cropH-->裁剪高度 值不能小于100 如果值大于图片原始宽高 将返回原图大小
                            .setMaxB(MAX_IMAGE_SIZE) // 压缩最大值 例如:200kb  就设置202400，202400 / 1024 = 200kb
                            .setPreviewColor(R.drawable.color_preview_selector) //预览字体颜色
                            .setCompleteColor(completeColor) //已完成字体颜色
                            .setPreviewBottomBgColor(previewBottomBgColor) //预览图片底部背景色
                            .setPreviewTopBgColor(previewTopBgColor)//预览图片标题背景色
                            .setBottomBgColor(bottomBgColor) //图片列表底部背景色
                            .setGrade(Luban.CUSTOM_GEAR) // 压缩档次 默认三档
                            .setCheckNumMode(false)
                            .setCompressQuality(100) // 图片裁剪质量,默认无损
                            .setImageSpanCount(4) // 每行个数
//                            .setSelectMedia(selectMedia) // 已选图片，传入在次进去可选中，不能传入网络图片
                            .setCompressFlag(2) // 1 系统自带压缩 2 luban压缩
//                            .setCompressW(compressW) // 压缩宽 如果值大于图片原始宽高无效
//                            .setCompressH(compressH) // 压缩高 如果值大于图片原始宽高无效
                            .setThemeStyle(themeStyle) // 设置主题样式
                            .create();

                    PictureConfig.getInstance().init(options).openPhoto((Activity) ctx, resultCallback);

                    break;
                case 1:
                    selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
            }
        }
    };

    private String floderPath;
    /**
     * 图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia.addAll(resultList);
            Log.i("callBack_result", selectMedia.size() + "");
            for (LocalMedia media : resultList) {
                if (media.isCut() && !media.isCompressed()) {
//                    // 裁剪过
                    String path = media.getCutPath();
                    try {
                        File file = new File(path);
                        StorageUtils.saveFile(file, floderPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                    // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                    String path = media.getCompressPath();

                    try {
                        File file = new File(path);
                        StorageUtils.saveFile(file, floderPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // 原图地址
                    String path = media.getPath();

                    try {
                        File file = new File(path);
                        StorageUtils.saveFile(file, floderPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (selectMedia != null) {
                adapter.setList(selectMedia);
                adapter.notifyDataSetChanged();
                UpLoadImageNum.setText(selectMedia.size() + "/9");
            }
        }


    };

}
