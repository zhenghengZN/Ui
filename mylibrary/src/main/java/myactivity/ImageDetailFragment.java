package myactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;

import Utils.DeviceHelper;
import so.bubu.ui.test.mylibrary.R;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by wangwn on 2016/2/15.
 */

public class ImageDetailFragment extends Fragment {

	private String mImageUrl;
	private PhotoView mImageView;
	private ProgressBar progressBar;
	private PhotoViewAttacher mAttacher;
	private int type;
	private int mImageResId;
	private String mImageFilePath;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putInt("type", ImagePageActivity.IMAGE_TYPE_NET);
		args.putString("url", imageUrl);
		f.setArguments(args);

		return f;
	}

	public static ImageDetailFragment newInstance(int type,int resId) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
			args.putInt("type", type);
			args.putInt("resId", resId);
			f.setArguments(args);


		return f;
	}
	public static ImageDetailFragment newInstance(int type,String filePath) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putInt("type", type);
		args.putString("filePath", filePath);
		f.setArguments(args);


		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		type = getArguments() != null ? getArguments().getInt("type") : null;
		if (type == ImagePageActivity.IMAGE_TYPE_RES) {
			mImageResId = getArguments() != null ? getArguments().getInt("resId") : null;
		} else if (type == ImagePageActivity.IMAGE_TYPE_NET) {
			mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
		} else if (type == ImagePageActivity.IMAGE_TYPE_FILE) {
			mImageFilePath = getArguments() != null ? getArguments().getString("filePath") : null;
		}


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_image_detail, container, false);
		mImageView = (PhotoView) v.findViewById(R.id.image);
//		mAttacher = new PhotoViewAttacher(mImageView);

		mImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
			@Override
			public void onPhotoTap(View view, float x, float y) {
				getActivity().finish();
			}
		});

		progressBar = (ProgressBar) v.findViewById(R.id.loading);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		progressBar.setVisibility(View.GONE);
		if (type == ImagePageActivity.IMAGE_TYPE_RES) {
			Picasso.with(getActivity())
					.load(mImageResId)
					.resize(DeviceHelper.getScreenWidth(), DeviceHelper.getScreenHeight() )
					.centerInside()
					.into(mImageView);

		} else if (type == ImagePageActivity.IMAGE_TYPE_NET) {
			if (!TextUtils.isEmpty(mImageUrl)) {
				Glide.with(getActivity())
						.load(mImageUrl)
						.crossFade()
						.skipMemoryCache(true)
						.into(mImageView);
			}

		} else if (type == ImagePageActivity.IMAGE_TYPE_FILE) {

			if (!TextUtils.isEmpty(mImageFilePath)) {
				Glide.with(getActivity())
						.load(new File(mImageFilePath))
						.crossFade()
						.skipMemoryCache(true)
						.into(mImageView);
			}
		}



	}

}
