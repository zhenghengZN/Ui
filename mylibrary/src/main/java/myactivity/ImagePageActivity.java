package myactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.page.common.BaseCompatActivity;
import so.bubu.ui.test.mylibrary.wiget.HackyViewPager;

/**
 * Created by wangwn on 2016/2/15.
 */
public class ImagePageActivity extends BaseCompatActivity {


    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    public static final String EXTRA_IMAGE_RESID = "image_urls";
    public static final String EXTRA_IMAGE_TYPE = "image_type";
    public static final int IMAGE_TYPE_RES = 10;
    public static final int IMAGE_TYPE_NET = 11;
    public static final int IMAGE_TYPE_FILE = 12;

    private HackyViewPager mPager;
    private int pagerPosition;
    private TextView indicator;

    /**
     * 查看图片详情
     *
     * @param position
     * @param resIds
     */
    public static void imageResBrower(Activity activity, int position, int[] resIds) {

        Intent intent = new Intent(activity, ImagePageActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        Bundle bundle = new Bundle();
        bundle.putIntArray(EXTRA_IMAGE_URLS, resIds);
        intent.putExtra("bundle", bundle);
        intent.putExtra(ImagePageActivity.EXTRA_IMAGE_RESID, position);
        intent.putExtra(ImagePageActivity.EXTRA_IMAGE_TYPE, IMAGE_TYPE_RES);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.view_bottom_in, R.anim.view_bottom_out);
    }

//    @Override
//    protected void setStatusBar() {
//        super.setStatusBar();
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
//    }

    /**
     * 查看图片详情
     *
     * @param position
     * @param filePaths
     */
    public static void imageFileBrower(Activity activity, int position, String[] filePaths) {

        Intent intent = new Intent(activity, ImagePageActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        Bundle bundle = new Bundle();
        bundle.putStringArray(EXTRA_IMAGE_URLS, filePaths);
        intent.putExtra("bundle", bundle);
//        intent.putExtra(ImagePageActivity.EXTRA_IMAGE_URLS, urls);
        intent.putExtra(ImagePageActivity.EXTRA_IMAGE_RESID, position);
        intent.putExtra(ImagePageActivity.EXTRA_IMAGE_TYPE, IMAGE_TYPE_FILE);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.view_bottom_in, R.anim.view_bottom_out);
    }

    /**
     * 查看图片详情
     *
     * @param position
     * @param urls
     */
    public static void imageBrower(Activity activity, Class clazz, int position, String[] urls) {

        Bundle bundle = new Bundle();
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        bundle.putStringArray(ImagePageActivity.EXTRA_IMAGE_URLS, urls);
        bundle.putInt(ImagePageActivity.EXTRA_IMAGE_INDEX, position);
        bundle.putInt(ImagePageActivity.EXTRA_IMAGE_TYPE, IMAGE_TYPE_NET);

        Intent intent = new Intent(activity, clazz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.pull_right_in, R.anim.pull_right_out);
//        NavigationHelper.pushupActivity(activity, ImagePageActivity.class, bundle, false);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.view_bottom_in, R.anim.view_bottom_out);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * onCreateView:初始化界面
     *
     * @param savedInstanceState
     * @author linhuan 2016/1/27 0027 11:27
     */
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_image_pager);
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);

        int type = getIntent().getIntExtra(EXTRA_IMAGE_TYPE, 0);

        mPager = (HackyViewPager) findViewById(R.id.pager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(
                getSupportFragmentManager(), type, getIntent());
        mPager.setAdapter(mAdapter);
        indicator = (TextView) findViewById(R.id.indicator);

        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager
                .getAdapter().getCount());
        indicator.setText(text);
        // 更新下标
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator,
                        arg0 + 1, mPager.getAdapter().getCount());
                indicator.setText(text);
            }

        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);

    }

    /**
     * function: 后退处理
     *
     * @param keyCode
     * @param event
     * @author:linhuan 2014年8月5日 下午7:59:01
     */
    @Override
    protected void doBack(int keyCode, KeyEvent event) {
        (ImagePageActivity.this).setBackAnim();
        ImagePageActivity.this.finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        private int type;
        private String[] urls;
        private int[] resIds;
        private String[] filePaths;

        public ImagePagerAdapter(FragmentManager fm, int type, Intent intent) {
            super(fm);
            this.type = type;
            switch (type) {
                case IMAGE_TYPE_RES:
                    Bundle bundle = intent.getBundleExtra("bundle");
                    resIds = bundle.getIntArray(EXTRA_IMAGE_URLS);
                    break;
                case IMAGE_TYPE_NET:
                    urls = intent.getStringArrayExtra(EXTRA_IMAGE_URLS);
                    break;
                case IMAGE_TYPE_FILE:
                    Bundle bundl2 = intent.getBundleExtra("bundle");
                    filePaths = bundl2.getStringArray(EXTRA_IMAGE_URLS);
                    break;
                default:
                    break;
            }
        }

        @Override
        public int getCount() {
            if (type == IMAGE_TYPE_RES) {
                return resIds == null ? 0 : resIds.length;
            } else if (type == IMAGE_TYPE_NET) {
                return urls == null ? 0 : urls.length;
            } else if (type == IMAGE_TYPE_FILE) {
                return filePaths == null ? 0 : filePaths.length;
            } else {
                return 0;
            }


        }

        @Override
        public Fragment getItem(int position) {
            if (type == IMAGE_TYPE_RES) {
                int resId = resIds[position];
                return ImageDetailFragment.newInstance(type, resId);
            } else if (type == IMAGE_TYPE_NET) {
                String url = urls[position];
                return ImageDetailFragment.newInstance(url);
            } else if (type == IMAGE_TYPE_FILE) {
                String filePath = filePaths[position];
                return ImageDetailFragment.newInstance(type, filePath);
            } else {
                return null;
            }


        }

    }
}
