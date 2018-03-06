package myactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import so.bubu.ui.test.mylibrary.wiget.DividerGridItemDecoration;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.page.common.BaseCompatActivity;


/**
 * 浏览图片
 */
public class BrowseImageActivity extends BaseCompatActivity {

    public static final String URL = "url";

    private RecyclerView rcvImage;

    private String[] dataList;

    private BrowseImageAdapter browseImageAdapter;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_browse_image);
    }

    @Override
    protected void initView() {
        super.initView();

        rcvImage = findView(R.id.rcv_image);
        rcvImage.setLayoutManager(new GridLayoutManager(this, 3));
//        rcvImage.addOnScrollListener(new BaseOnRecyclerScrollListener());
        rcvImage.addItemDecoration(new DividerGridItemDecoration(this));

        findViewById(R.id.iv_back_poi_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack(-1, null);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        dataList = getIntent().getStringArrayExtra(URL);
//        dataList = new String[]{"http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg"};
//        rcvImage.addItemDecoration(new GridLayoutItemDecoration(ResourceHelper.Dp2Px(4), 0 == dataList.length % 3 ? dataList.length / 3 : dataList.length / 3 + 1, dataList.length));
        browseImageAdapter = new BrowseImageAdapter(this, dataList) {
            @Override
            public void ImageOnClick(Context ctx, int position, String[] dataList) {
//                ImagePageActivity.imageBrower(act, position, dataList);
//                setImagePhotoOnclick(ctx,position,dataList);
                ImagePageActivity.imageBrower(BrowseImageActivity.this, ImagePageActivity.class, position, dataList);

            }
        };
        rcvImage.setAdapter(browseImageAdapter);
    }

//    public abstract void setImagePhotoOnclick(Context ctx, int position, String[] dataList);

    @Override
    protected void doBack(int keyCode, KeyEvent event) {
        (BrowseImageActivity.this).setBackAnim();
        BrowseImageActivity.this.finish();
    }

}
