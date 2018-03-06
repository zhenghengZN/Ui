package so.bubu.ui.test.mylibrary.uploadimage.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import so.bubu.ui.test.mylibrary.Adapter.MyItemDecoration;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.uploadimage.UCrop;
import so.bubu.ui.test.mylibrary.uploadimage.adapter.PictureImageGridAdapter;
import so.bubu.ui.test.mylibrary.uploadimage.compress.CompressConfig;
import so.bubu.ui.test.mylibrary.uploadimage.compress.CompressImageOptions;
import so.bubu.ui.test.mylibrary.uploadimage.compress.CompressInterface;
import so.bubu.ui.test.mylibrary.uploadimage.compress.LubanOptions;
import so.bubu.ui.test.mylibrary.uploadimage.model.EventEntity;
import so.bubu.ui.test.mylibrary.uploadimage.model.FunctionConfig;
import so.bubu.ui.test.mylibrary.uploadimage.model.FunctionOptions;
import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMedia;
import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMediaFolder;
import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMediaLoader;
import so.bubu.ui.test.mylibrary.uploadimage.model.PictureConfig;
import so.bubu.ui.test.mylibrary.uploadimage.observable.ImagesObservable;
import so.bubu.ui.test.mylibrary.uploadimage.util.FileSizeUtils;
import so.bubu.ui.test.mylibrary.uploadimage.util.FileUtils;
import so.bubu.ui.test.mylibrary.uploadimage.util.ToolbarUtil;
import so.bubu.ui.test.mylibrary.uploadimage.util.Utils;
import so.bubu.ui.test.mylibrary.uploadimage.weight.MyItemAnimator;
import so.bubu.ui.test.mylibrary.uploadimage.weight.SweetAlertDialog;
import so.bubu.ui.test.mylibrary.wiget.DividerGridItemDecoration;
import so.bubu.ui.test.mylibrary.wiget.GridSpacingItemDecoration;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.ui
 * email：893855882@qq.com
 * data：16/12/31
 */
public class PictureImageGridActivity extends PictureBaseActivity implements View.OnClickListener, PictureImageGridAdapter.OnPhotoSelectChangedListener, CompoundButton.OnCheckedChangeListener {
    public final String TAG = PictureImageGridActivity.class.getSimpleName();
    private List<LocalMedia> images = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView tv_img_num;
    private TextView tv_ok, tv_mask;
    private RelativeLayout rl_bottom;
    private ImageView picture_left_back;
    private RelativeLayout rl_picture_title;
    private TextView picture_tv_title, picture_tv_right;
    private Animation animation = null;
    private TextView id_preview;
    private TextView tv_original_pic_des;
    private CheckBox cb_original_pic;
    private LinearLayout select_album;
    private PictureImageGridAdapter adapter;
    private String cameraPath;
    private SweetAlertDialog dialog;
    private List<LocalMediaFolder> folders = new ArrayList<>();
    private boolean is_top_activity;
    private boolean takePhoto = false;// 是否只单独调用拍照
    private boolean takePhotoSuccess = false;// 单独拍照是否成功


    //EventBus 3.0 回调
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBus(EventEntity obj) {
        switch (obj.what) {
            case FunctionConfig.CLOSE_FLAG:
                // 关闭activity
                finish();
                overridePendingTransition(0, R.anim.pull_right_out);
                break;
            case FunctionConfig.UPDATE_FLAG:
                // 预览时勾选图片更新回调
                List<LocalMedia> selectImages = obj.medias;
                adapter.bindSelectImages(selectImages);
                break;
            case FunctionConfig.CROP_FLAG:
                // 裁剪返回的数据

                if (takePhoto || options.getSelectMode() == FunctionConfig.MODE_SINGLE) {
                    List<LocalMedia> result = obj.medias;
                    if (result == null)
                        result = new ArrayList<>();
                    handleCropResult(result);
                }
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        takePhoto = getIntent().getBooleanExtra(FunctionConfig.FUNCTION_TAKE, false);
        if (savedInstanceState != null) {
            getOnSaveValues(savedInstanceState);
        }
        if (takePhoto) {
            // 只拍照
            if (savedInstanceState == null) {
                onTakePhoto();
            }
            if (!enableCrop && isCompress) {
                // 如果单独拍照，并且没有裁剪 但压缩 这里显示一个蒙版过渡一下
                //TODO crmara not compress
                setContentView(R.layout.picture_empty);
                ToolbarUtil.setColorNoTranslucent(this, Color.BLACK);
            }
        } else {
            setContentView(R.layout.picture_activity_image_grid);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
            picture_left_back = (ImageView) findViewById(R.id.picture_left_back);
            rl_picture_title = (RelativeLayout) findViewById(R.id.rl_picture_title);
            picture_tv_title = (TextView) findViewById(R.id.picture_tv_title);
            picture_tv_right = (TextView) findViewById(R.id.picture_tv_right);
            tv_original_pic_des = (TextView) findViewById(R.id.original_pic_des);
            cb_original_pic = (CheckBox) findViewById(R.id.original_pic_checkBox);
            cb_original_pic.setOnCheckedChangeListener(this);
            rl_picture_title.setBackgroundColor(backgroundColor);
            ToolbarUtil.setColorNoTranslucent(this, backgroundColor);
            tv_ok = (TextView) findViewById(R.id.tv_ok);
            id_preview = (TextView) findViewById(R.id.id_preview);
            tv_img_num = (TextView) findViewById(R.id.tv_img_num);
            tv_mask = (TextView) findViewById(R.id.tv_mask);
            select_album = (LinearLayout) findViewById(R.id.select_album);
            id_preview.setText(getString(R.string.picture_preview));
            tv_ok.setText(getString(R.string.picture_please_select));
            animation = AnimationUtils.loadAnimation(this, R.anim.modal_in);
            id_preview.setOnClickListener(this);
            tv_ok.setOnClickListener(this);
            picture_left_back.setOnClickListener(this);
            //TODO album
            select_album.setOnClickListener(this);
            picture_tv_right.setOnClickListener(this);
            Bundle bundle = getIntent().getBundleExtra(FunctionConfig.OPNE_IMAGE_GRID);
            if (bundle != null) {
                this.bd = bundle;
            }


            //TODO recyclerview
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, Dp2Px(2), false));
            GridLayoutManager manager = new GridLayoutManager(this, spanCount);
            recyclerView.setLayoutManager(manager);
            ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
            adapter = new PictureImageGridAdapter(this, options.isGif(), showCamera, maxSelectNum, selectMode, enablePreview, enablePreviewVideo, cb_drawable, is_checked_num, type);
            recyclerView.setAdapter(adapter);
            init();
        }

    }


    private void init() {
//        is_top_activity = getIntent().getBooleanExtra(FunctionConfig.EXTRA_IS_TOP_ACTIVITY, false);
        is_top_activity = bd.getBoolean(FunctionConfig.EXTRA_IS_TOP_ACTIVITY, false);

        if (!is_top_activity) {
            // 第一次启动ImageActivity，没有获取过相册列表
            // 先判断手机是否有读取权限，主要是针对6.0已上系统
            if (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                readLocalMedia();
            } else {
                requestPermission(FunctionConfig.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        } else {
//            selectMedias = (List<LocalMedia>) getIntent().getSerializableExtra(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST);
            selectMedias = (List<LocalMedia>) bd.getSerializable(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST);
        }

//        String folderName = getIntent().getStringExtra(FunctionConfig.FOLDER_NAME);
        String folderName = bd.getString(FunctionConfig.FOLDER_NAME);
        folders = ImagesObservable.getInstance().readLocalFolders();
        if (folders == null) {
            folders = new ArrayList<>();
        }

        // 获取图片
        images = ImagesObservable.getInstance().readLocalMedias();
        if (images == null) {
            images = new ArrayList<>();
        }

        Log.e("zhengheng", "init " + images.size());

        if (selectMedias == null) {
            selectMedias = new ArrayList<>();
        }
        if (enablePreview && selectMode == FunctionConfig.MODE_MULTIPLE) {
            if (type == FunctionConfig.TYPE_VIDEO) {
                // 如果是视频不能预览
                id_preview.setVisibility(View.GONE);
            } else {
                id_preview.setVisibility(View.VISIBLE);
            }
        } else if (selectMode == FunctionConfig.MODE_SINGLE) {
            rl_bottom.setVisibility(View.GONE);
        } else {
            id_preview.setVisibility(View.GONE);
        }

        //暂时支持图片，没有考虑视频压缩
        if (isShowOriginal && type == FunctionConfig.TYPE_IMAGE) {
            cb_original_pic.setVisibility(View.VISIBLE);
            tv_original_pic_des.setVisibility(View.VISIBLE);
        } else {
            cb_original_pic.setVisibility(View.GONE);
            tv_original_pic_des.setVisibility(View.GONE);
        }

        if (folderName != null && !folderName.equals("")) {
            picture_tv_title.setText(folderName);
        } else {
            switch (type) {
                case FunctionConfig.TYPE_IMAGE:
                    picture_tv_title.setText(getString(R.string.picture_lately_image));
                    break;
                case FunctionConfig.TYPE_VIDEO:
                    picture_tv_title.setText(getString(R.string.picture_lately_video));
                    break;
            }
        }
        rl_bottom.setBackgroundColor(bottomBgColor);

        try {
            ColorStateList csl = ContextCompat.getColorStateList(getApplicationContext(), previewColor);
            id_preview.setTextColor(csl);
            tv_original_pic_des.setTextColor(csl);
        } catch (Exception e) {
            id_preview.setTextColor(previewColor);
            tv_original_pic_des.setTextColor(previewColor);
        }

        tv_ok.setTextColor(completeColor);
        picture_tv_right.setText(getString(R.string.picture_cancel));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, Dp2Px(2), false));
//        GridLayoutManager manager = new GridLayoutManager(this, spanCount);
//            recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
//        recyclerView.setLayoutManager(manager);
        // 解决调用 notifyItemChanged 闪烁问题,取消默认动画
//        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        if (!is_checked_num) {
            recyclerView.setItemAnimator(new MyItemAnimator());
        } else {
            // 如果是显示数据风格，则默认为qq选择风格
            tv_img_num.setBackgroundResource(cb_drawable);
            tv_img_num.setSelected(true);
        }
        String titleText = picture_tv_title.getText().toString().trim();
        if (showCamera) {
            if (!Utils.isNull(titleText) && titleText.startsWith("最近") || titleText.startsWith("Recent")) {
                // 只有最近相册 才显示拍摄按钮，不然相片混乱
                showCamera = true;
            } else {
                showCamera = false;
            }
        }
//        adapter = new PictureImageGridAdapter(this, options.isGif(), showCamera, maxSelectNum, selectMode, enablePreview, enablePreviewVideo, cb_drawable, is_checked_num, type);
//        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (selectMedias.size() > 0) {
            ChangeImageNumber(selectMedias);
            adapter.bindSelectImages(selectMedias);
        }
        adapter.bindImagesData(images);
        adapter.setOnPhotoSelectChangedListener(PictureImageGridActivity.this);
    }

    public static int Dp2Px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);

    }

    /**
     * 取拍照时 此activity被暂时回收存储的值
     *
     * @param savedInstanceState
     */
    private void getOnSaveValues(Bundle savedInstanceState) {
        cameraPath = savedInstanceState.getString(FunctionConfig.BUNDLE_CAMERA_PATH);
        takePhoto = savedInstanceState.getBoolean(FunctionConfig.FUNCTION_TAKE);
        takePhotoSuccess = savedInstanceState.getBoolean(FunctionConfig.TAKE_PHOTO_SUCCESS);
        takePhotoSuccess = true;
        options = (FunctionOptions) savedInstanceState.getSerializable(FunctionConfig.EXTRA_THIS_CONFIG);
        enableCrop = options.isEnableCrop();
        isCompress = options.isCompress();
        selectMode = options.getSelectMode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (picture_tv_right != null && !picture_tv_right.isEnabled()) {
            picture_tv_right.setEnabled(true);
        }
    }

    private void handleCropResult(List<LocalMedia> result) {
        if (result != null) {
            if (isCompress && type == FunctionConfig.TYPE_IMAGE) {
                // 压缩图片
                compressImage(result);
            } else {
                onSelectDone(result);
            }
        }
    }


    /**
     * 释放回调 导致的内存泄漏
     */
    protected void releaseCallBack() {
        PictureConfig.resultCallback = null;
    }

    @Override
    protected void readLocalMedia() {
        /**
         * 根据type决定，查询本地图片或视频。
         */
        showPleaseDialog(getString(R.string.picture_please));
        new LocalMediaLoader(this, type, options.isGif()).loadAllImage(new LocalMediaLoader.LocalMediaLoadListener() {

            @Override
            public void loadComplete(List<LocalMediaFolder> folders) {
                dismiss();
                if (folders.size() > 0) {
                    // 取最近相册或视频数据
                    LocalMediaFolder folder = folders.get(0);
                    images = folder.getImages();
                    adapter.bindImagesData(images);
                    PictureImageGridActivity.this.folders = folders;
                    ImagesObservable.getInstance().saveLocalFolders(folders);
                    ImagesObservable.getInstance().notifyFolderObserver(folders);
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        int id = view.getId();
        if (id == R.id.picture_left_back) {
            //TODO finish
//            activityFinish(1);
            finish();
        } else if (id == R.id.select_album) {
            //TODO album
            activityFinish(1);
        } else if (id == R.id.picture_tv_right) {
            activityFinish(2);
        } else if (id == R.id.id_preview) {
            if (Utils.isFastDoubleClick()) {
                return;
            }
            List<LocalMedia> selectedImages = adapter.getSelectedImages();
            List<LocalMedia> medias = new ArrayList<>();
            for (LocalMedia media : selectedImages) {
                medias.add(media);
            }
            options.setCompress(isCompress);
            intent.putExtra(FunctionConfig.EXTRA_PREVIEW_LIST, (Serializable) medias);
            intent.putExtra(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) selectedImages);
            intent.putExtra(FunctionConfig.EXTRA_POSITION, 0);
            intent.putExtra(FunctionConfig.EXTRA_BOTTOM_PREVIEW, true);
            intent.putExtra(FunctionConfig.EXTRA_THIS_CONFIG, options);
            intent.setClass(mContext, PicturePreviewActivity.class);
            startActivityForResult(intent, FunctionConfig.REQUEST_PREVIEW);
        } else if (id == R.id.tv_ok) {
            List<LocalMedia> images = adapter.getSelectedImages();
            // 如果设置了图片最小选择数量，则判断是否满足条件
            int size = images.size();
            if (minSelectNum > 0 && selectMode == FunctionConfig.MODE_MULTIPLE) {
                if (size < minSelectNum) {
                    switch (type) {
                        case FunctionConfig.TYPE_IMAGE:
                            showToast(getString(R.string.picture_min_img_num, options.getMinSelectNum()));
                            return;
                        case FunctionConfig.TYPE_VIDEO:
                            showToast(getString(R.string.picture_min_video_num, options.getMinSelectNum()));
                            return;
                        default:
                            break;
                    }
                }
            }

            // 图片才压缩，视频不管
            if (isCompress && type == FunctionConfig.TYPE_IMAGE) {
                compressImage(images);
            } else {
                resultBack(images);
            }

        }
    }

    private void resultBack(List<LocalMedia> images) {
        onResult(images);
    }

    @Override
    public void onTakePhoto() {
        // 启动相机拍照,先判断手机是否有拍照权限
        if (hasPermission(Manifest.permission.CAMERA)) {
            startCamera();
        } else {
            requestPermission(FunctionConfig.CAMERA, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onChange(List<LocalMedia> selectImages) {
        ChangeImageNumber(selectImages);

    }

    /**
     * 图片选中数量
     *
     * @param selectImages
     */
    public void ChangeImageNumber(List<LocalMedia> selectImages) {

        boolean enable = selectImages.size() != 0;
        if (enable) {
            tv_ok.setEnabled(true);
            id_preview.setEnabled(true);
            tv_img_num.startAnimation(animation);
            tv_img_num.setVisibility(View.VISIBLE);
            tv_img_num.setText(selectImages.size() + "");
            //TODO 改为完成(0/9)
            tv_ok.setText(getString(R.string.picture_completed));
            cb_original_pic.setEnabled(true);
            tv_original_pic_des.setEnabled(true);
            updateOriginalFilesSize();
        } else {
            tv_ok.setEnabled(false);
            id_preview.setEnabled(false);
            tv_img_num.setVisibility(View.INVISIBLE);
            tv_ok.setText(getString(R.string.picture_please_select));
            cb_original_pic.setEnabled(false);
            tv_original_pic_des.setEnabled(false);
            tv_original_pic_des.setText("原图");
        }

    }

    @Override
    public void startCamera() {
        if (!Utils.isFastDoubleClick()) {
            switch (type) {
                case FunctionConfig.TYPE_IMAGE:
                    // 拍照
                    startOpenCamera();
                    break;
                case FunctionConfig.TYPE_VIDEO:
                    // 录视频
                    startOpenCameraVideo();
                    break;
            }
        }
    }

    @Override
    public void onPictureClick(LocalMedia media, int position) {
        if (!Utils.isFastDoubleClick2()) {
            startPreview(adapter.getImages(), position);
        }
    }

    public void startPreview(List<LocalMedia> previewImages, int position) {
        LocalMedia media = previewImages.get(position);
        int type = media.getType();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (type) {
            case FunctionConfig.TYPE_IMAGE:
                if (enableCrop && selectMode == FunctionConfig.MODE_SINGLE) {
//                    startCopy(media.getPath());
                } else if (!enableCrop && selectMode == FunctionConfig.MODE_SINGLE) {
                    ArrayList<LocalMedia> result = new ArrayList<>();
                    LocalMedia m = new LocalMedia();
                    m.setPath(media.getPath());
                    m.setType(type);
                    result.add(m);
                    if (isCompress) {
                        compressImage(result);
                    } else {
                        onSelectDone(result);
                    }
                } else {
                    // 图片可以预览
                    if (Utils.isFastDoubleClick()) {
                        return;
                    }
                    ImagesObservable.getInstance().saveLocalMedia(previewImages);
                    List<LocalMedia> selectedImages = adapter.getSelectedImages();
                    intent.putExtra(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) selectedImages);
                    intent.putExtra(FunctionConfig.EXTRA_POSITION, position);
                    intent.putExtra(FunctionConfig.EXTRA_THIS_CONFIG, options);
                    intent.setClass(mContext, PicturePreviewActivity.class);
                    startActivityForResult(intent, FunctionConfig.REQUEST_PREVIEW);
                }
                break;
            //TODO Upload Video
//            case FunctionConfig.TYPE_VIDEO:
//                // 视频
//                if (selectMode == FunctionConfig.MODE_SINGLE) {
//                    // 单选
//                    List<LocalMedia> result = new ArrayList<>();
//                    LocalMedia m = new LocalMedia();
//                    m.setPath(media.getPath());
//                    m.setDuration(media.getDuration());
//                    m.setType(type);
//                    result.add(m);
//                    onSelectDone(result);
//                } else {
//                    if (Utils.isFastDoubleClick()) {
//                        return;
//                    }
//                    bundle.putString("video_path", media.getPath());
//                    bundle.putSerializable(FunctionConfig.EXTRA_THIS_CONFIG, options);
//                    startActivity(PictureVideoPlayActivity.class, bundle);
//                }
//                break;
        }

    }

    /**
     * 裁剪
     *
     * @param path
     */
    protected void startCopy(String path) {
        // 如果开启裁剪 并且是单选
        // 去裁剪
        if (Utils.isFastDoubleClick()) {
            return;
        }
        UCrop uCrop = UCrop.of(Uri.parse(path), Uri.fromFile(new File(getCacheDir(), System.currentTimeMillis() + ".jpg")));
        UCrop.Options options = new UCrop.Options();
        switch (copyMode) {
            case FunctionConfig.CROP_MODEL_DEFAULT:
                options.withAspectRatio(0, 0);
                break;
            case FunctionConfig.CROP_MODEL_1_1:
                options.withAspectRatio(1, 1);
                break;
            case FunctionConfig.CROP_MODEL_3_2:
                options.withAspectRatio(3, 2);
                break;
            case FunctionConfig.CROP_MODEL_3_4:
                options.withAspectRatio(3, 4);
                break;
            case FunctionConfig.CROP_MODEL_16_9:
                options.withAspectRatio(16, 9);
                break;
        }

        // 圆形裁剪
        if (circularCut) {
            options.setCircleDimmedLayer(true);// 是否为椭圆
            options.setShowCropFrame(false);// 外部矩形
            options.setShowCropGrid(false);// 内部网格
            options.withAspectRatio(1, 1);
        }
        options.setCompressionQuality(compressQuality);
        options.withMaxResultSize(cropW, cropH);
        options.background_color(backgroundColor);
        options.localType(type);
        options.setIsCompress(isCompress);
        options.setIsTakePhoto(takePhoto);
        options.setCircularCut(circularCut);
        uCrop.withOptions(options);
        uCrop.start(PictureImageGridActivity.this);
    }


    /**
     * start to camera、preview、crop
     */
    public void startOpenCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File cameraFile = FileUtils.createCameraFile(this, type);
            cameraPath = cameraFile.getAbsolutePath();
            Uri imageUri;
            String authority = getPackageName() + ".fileProvider";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                imageUri = FileProvider.getUriForFile(mContext, authority, cameraFile);//通过FileProvider创建一个content类型的Uri
            } else {
                imageUri = Uri.fromFile(cameraFile);
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, FunctionConfig.REQUEST_CAMERA);
        }
    }

    /**
     * start to camera、video
     */
    public void startOpenCameraVideo() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File cameraFile = FileUtils.createCameraFile(this, type);
            cameraPath = cameraFile.getAbsolutePath();
            Uri imageUri;
            String authority = getPackageName() + ".fileProvider";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                imageUri = FileProvider.getUriForFile(mContext, authority, cameraFile);//通过FileProvider创建一个content类型的Uri
            } else {
                imageUri = Uri.fromFile(cameraFile);
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, recordVideoSecond);
            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, definition);
            startActivityForResult(cameraIntent, FunctionConfig.REQUEST_CAMERA);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // on take photo success
            if (requestCode == FunctionConfig.REQUEST_CAMERA) {
                // 拍照返回
                File file = new File(cameraPath);
                int degree = FileUtils.readPictureDegree(file.getAbsolutePath());
                rotateImage(degree, file);
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                takePhotoSuccess = true;

                // 生成新拍照片或视频对象
                LocalMedia media = new LocalMedia();
                media.setPath(cameraPath);
                media.setType(type);
                List<LocalMedia> result;
                // 因为加入了单独拍照功能，所有如果是单独拍照的话也默认为单选状态
                if (selectMode == FunctionConfig.MODE_SINGLE || takePhoto) {
                    // 如果是单选 拍照后直接返回
                    if (type == FunctionConfig.TYPE_IMAGE) {
                        if (enableCrop) {
                            // 去裁剪
                            startCopy(cameraPath);
                        } else if (isCompress) {
                            // 去压缩
                            result = new ArrayList<>();
                            result.add(media);
                            compressImage(result);
                        } else {
                            // 不裁剪 不压缩 直接返回结果
                            result = new ArrayList<>();
                            result.add(media);
                            onSelectDone(result);
                        }
                    } else {
                        // 视频
                        result = new ArrayList<>();
                        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                        mmr.setDataSource(cameraPath);
                        long duration = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
                        media.setDuration(duration);
                        result.add(media);
                        onSelectDone(result);
                    }
                } else {
                    // 多选 返回列表并选中当前拍照的
                    int duration = 0;
                    if (type == FunctionConfig.TYPE_VIDEO) {
                        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                        mmr.setDataSource(file.getPath());
                        duration = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
                    }
                    createNewFolder(folders);
                    // 生成拍照图片对象
                    media = new LocalMedia(file.getPath(), duration, duration, type);
                    // 根据新拍照生成的图片，插入到对应的相册当中，避免重新查询一遍数据库
                    LocalMediaFolder folder = getImageFolder(media.getPath(), folders);
                    // 更新当前图片所属文件夹
                    folder.getImages().add(0, media);// 插入到第一个位置
                    folder.setImageNum(folder.getImageNum() + 1);
                    folder.setFirstImagePath(media.getPath());
                    folder.setType(type);

                    // 取出最近文件夹 插入刚拍的照片或视频，并且如果大于100张，先移除最后一条在保存，因为最近文件夹中只显示100条数据
                    LocalMediaFolder mediaFolder = folders.get(0);
                    mediaFolder.setFirstImagePath(media.getPath());
                    mediaFolder.setType(type);
                    List<LocalMedia> localMedias = mediaFolder.getImages();
//                    if (localMedias.size() >= 100) {
//                        localMedias.remove(localMedias.size() - 1);
//                    }
                    List<LocalMedia> images = adapter.getImages();
                    images.add(0, media);
                    mediaFolder.setImages(images);
                    mediaFolder.setImageNum(mediaFolder.getImages().size());
                    // 没有到最大选择量 才做默认选中刚拍好的
                    if (adapter.getSelectedImages().size() < maxSelectNum) {
                        List<LocalMedia> selectedImages = adapter.getSelectedImages();
                        selectedImages.add(media);
                        adapter.bindSelectImages(selectedImages);
                        ChangeImageNumber(adapter.getSelectedImages());
                    }
                    adapter.bindImagesData(images);
                }

            }
        } else if (resultCode == RESULT_CANCELED) {
            // 取消拍照
            if (takePhoto && !takePhotoSuccess) {
                recycleCallBack();
            }
        }
    }

    /**
     * 判断拍照 图片是否旋转
     *
     * @param degree
     * @param file
     */
    private void rotateImage(int degree, File file) {
        if (degree > 0) {
            // 针对相片有旋转问题的处理方式
            try {
                BitmapFactory.Options opts = new BitmapFactory.Options();//获取缩略图显示到屏幕上
                opts.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
                Bitmap bmp = FileUtils.rotaingImageView(degree, bitmap);
                FileUtils.saveBitmapFile(bmp, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放
     */
    private void recycleCallBack() {
        activityFinish(2);
        clearData();
    }


    /**
     * 如果没有任何相册，先创建一个最近相册出来
     *
     * @param folders
     */
    private void createNewFolder(List<LocalMediaFolder> folders) {
        if (folders.size() == 0) {
            // 没有相册 先创建一个最近相册出来
            LocalMediaFolder newFolder = new LocalMediaFolder();
            String folderName = "";
            switch (type) {
                case FunctionConfig.TYPE_IMAGE:
                    folderName = getString(R.string.picture_lately_image);
                    break;
                case FunctionConfig.TYPE_VIDEO:
                    folderName = getString(R.string.picture_lately_video);
                    break;
            }
            newFolder.setName(folderName);
            newFolder.setPath("");
            newFolder.setFirstImagePath("");
            newFolder.setType(type);
            folders.add(newFolder);
        }
    }


    public void onSelectDone(List<LocalMedia> result) {
        onResult(result);
    }

    public void onResult(List<LocalMedia> images) {
        // 因为这里是单一实例的结果集，重新用变量接收一下在返回，不然会产生结果集被单一实例清空的问题
        List<LocalMedia> result = new ArrayList<>();
        for (LocalMedia media : images) {
            result.add(media);
        }
        PictureConfig.OnSelectResultCallback resultCallback = PictureConfig.getInstance().getResultCallback();
        if (resultCallback != null) {
            resultCallback.onSelectSuccess(result);
            releaseCallBack();
        }
        EventEntity obj = new EventEntity(FunctionConfig.CLOSE_FLAG);
        EventBus.getDefault().post(obj);
        if ((takePhoto && takePhotoSuccess) || (enableCrop && isCompress && selectMode == FunctionConfig.MODE_SINGLE)) {
            // 如果是单独拍照并且压缩可能会造成还在压缩中，但此activity已关闭,或单选 裁剪压缩时等压缩完在关闭PictureSingeUCropActivity
            recycleCallBack();
            releaseCallBack();
            EventEntity obj1 = new EventEntity(FunctionConfig.CLOSE_SINE_CROP_FLAG);
            EventBus.getDefault().post(obj1);
        } else {
            clearData();
        }
        finish();
        overridePendingTransition(0, R.anim.pull_right_out);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(FunctionConfig.BUNDLE_CAMERA_PATH, cameraPath);
        outState.putBoolean(FunctionConfig.FUNCTION_TAKE, takePhoto);
        outState.putBoolean(FunctionConfig.TAKE_PHOTO_SUCCESS, takePhotoSuccess);
        outState.putSerializable(FunctionConfig.EXTRA_THIS_CONFIG, options);
    }


    private LocalMediaFolder getImageFolder(String path, List<LocalMediaFolder> imageFolders) {
        File imageFile = new File(path);
        File folderFile = imageFile.getParentFile();

        for (LocalMediaFolder folder : imageFolders) {
            if (folder.getName().equals(folderFile.getName())) {
                return folder;
            }
        }
        LocalMediaFolder newFolder = new LocalMediaFolder();
        newFolder.setName(folderFile.getName());
        newFolder.setPath(folderFile.getAbsolutePath());
        newFolder.setFirstImagePath(path);
        imageFolders.add(newFolder);
        return newFolder;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                activityFinish(1);
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Bundle bd;

    private void activityFinish(int type) {
        switch (type) {
            case 1:
                // 返回
                List<LocalMedia> selectedImages = adapter.getSelectedImages();
                if (selectedImages == null) {
                    selectedImages = new ArrayList<>();
                }
                // 这里使用Activity启动模式singleTask，所以启动过该activity 刚不会重复启动
//                startActivity(new Intent(mContext, PictureAlbumDirectoryActivity.class).putExtra(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) selectedImages).putExtra(FunctionConfig.EXTRA_THIS_CONFIG, options));
//                overridePendingTransition(R.anim.pull_right_in, R.anim.pull_right_out);
                Bundle bundle = new Bundle();
                bundle.putSerializable(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) selectedImages);
                bundle.putSerializable(FunctionConfig.EXTRA_THIS_CONFIG, options);
                final PictureAlbumDialog pictureAlbumDialog = new PictureAlbumDialog(this, bundle);
                pictureAlbumDialog.show();
                pictureAlbumDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        bd = pictureAlbumDialog.getClickBundle();
                        init();
                    }
                });
                ImagesObservable.getInstance().notifySelectLocalMediaObserver(selectedImages);
//                finish();
                break;
            case 2:
                // 取消
                clearData();
                EventEntity obj = new EventEntity(FunctionConfig.CLOSE_FLAG);
                EventBus.getDefault().post(obj);
                finish();
                overridePendingTransition(0, R.anim.pull_right_out);
                break;
        }
    }


    /**
     * 处理图片压缩
     */
    private void compressImage(List<LocalMedia> result) {
        showPleaseDialog(getString(R.string.picture_please));
        CompressConfig compress_config = CompressConfig.ofDefaultConfig();
        switch (compressFlag) {
            case 1:
                // 系统自带压缩
                compress_config.enablePixelCompress(options.isEnablePixelCompress());
                compress_config.enableQualityCompress(options.isEnableQualityCompress());
                compress_config.setMaxSize(maxB);
                break;
            case 2:
                // luban压缩
                LubanOptions option = new LubanOptions.Builder()
                        .setMaxHeight(compressH)
                        .setMaxWidth(compressW)
                        .setMaxSize(maxB)
                        .setGrade(grade)
                        .create();
                compress_config = CompressConfig.ofLuban(option);
                break;
        }

        CompressImageOptions.compress(this, compress_config, result, new CompressInterface.CompressListener() {
            @Override
            public void onCompressSuccess(List<LocalMedia> images) {
                // 压缩成功回调
                onResult(images);
                dismiss();
            }

            @Override
            public void onCompressError(List<LocalMedia> images, String msg) {
                // 压缩失败回调 返回原图
                List<LocalMedia> selectedImages = adapter.getSelectedImages();
                onResult(selectedImages);
                dismiss();
            }
        }).compress();
    }

    private void showPleaseDialog(String msg) {
        if (!isFinishing()) {
            dialog = new SweetAlertDialog(PictureImageGridActivity.this);
            dialog.setTitleText(msg);
            dialog.show();
        }
    }

    private void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
    }

    /**
     * 释放静态
     */
    protected void clearData() {
        PictureConfig.getInstance().resultCallback = null;
        ImagesObservable.getInstance().clearLocalFolders();
        ImagesObservable.getInstance().clearLocalMedia();
        ImagesObservable.getInstance().clearSelectedLocalMedia();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (animation != null) {
            animation.cancel();
            animation = null;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isShowOriginal && tv_original_pic_des != null) {
            if (isChecked) {
                updateOriginalFilesSize();
                isCompress = false;
            } else {
                tv_original_pic_des.setText("原图");
                isCompress = true;
            }
        }
    }

    private void updateOriginalFilesSize() {
        if (adapter != null && tv_original_pic_des != null && cb_original_pic != null &&
                cb_original_pic.isChecked() && isShowOriginal) {
            tv_original_pic_des.setText(new StringBuffer("原图(")
                    .append(FileSizeUtils.getMediaSize(adapter.getSelectedImages()))
                    .append(")"));
        }
    }


    /**
     * 针对6.0动态请求权限问题
     * 判断是否允许此权限
     *
     * @param permissions
     * @return
     */
    protected boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 动态请求权限
     *
     * @param code
     * @param permissions
     */
    protected void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case FunctionConfig.READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readLocalMedia();
                } else {
                    showToast("读取内存卡权限已被拒绝");
                }
                break;
            case FunctionConfig.CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    showToast("拍照权限已被拒绝");
                }
                break;
        }
    }
}
