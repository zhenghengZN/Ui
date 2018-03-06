package so.bubu.ui.test.mylibrary.uploadimage.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.uploadimage.model.FunctionConfig;
import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMedia;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureImagePreviewFragment extends Fragment {
    public static final String PATH = "path";
    private List<LocalMedia> selectImages = new ArrayList<>();
    private ImageView imageView;
    private PhotoViewAttacher mAttacher;

    public static PictureImagePreviewFragment getInstance(String path, List<LocalMedia> medias) {
        PictureImagePreviewFragment fragment = new PictureImagePreviewFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(saveState(bundle,path,medias));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.picture_fragment_image_preview, container, false);
        imageView = (ImageView) contentView.findViewById(R.id.preview_image);
        mAttacher = new PhotoViewAttacher(imageView);
        selectImages = (List<LocalMedia>) getArguments().getSerializable(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST);
        String path = getArguments().getString(PATH);
        replacePicture(path,selectImages);
        mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                if (getActivity() instanceof PicturePreviewActivity) {
                    activityFinish();
                } else {
                    getActivity().finish();
                    getActivity().overridePendingTransition(0, R.anim.toast_out);
                }
            }
        });
        return contentView;
    }

    public void replacePicture(String path, List<LocalMedia> medias){
        if (path != null && medias != null){
            Glide.with(getContext())
                    .load(path)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(new SimpleTarget<Bitmap>(480, 800) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (imageView != null && mAttacher != null) {
                                imageView.setImageBitmap(resource);
                                mAttacher.update();
                            }
                        }
                    });
            Bundle bundle = getArguments();
            saveState(bundle,path,medias);
        }
    }

    private static Bundle saveState(Bundle bundle,String path, List<LocalMedia> medias){
        bundle.putString(PATH, path);
        bundle.putSerializable(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) medias);
        return bundle;
    }


    protected void activityFinish() {
        getActivity().setResult(getActivity().RESULT_OK, new Intent().putExtra("type", 1).putExtra(FunctionConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) selectImages));
        getActivity().finish();
    }
}
