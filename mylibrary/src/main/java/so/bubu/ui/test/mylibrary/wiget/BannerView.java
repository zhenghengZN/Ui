//package so.bubu.ui.test.mylibrary.wiget;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.FrameLayout;
//
//import com.youth.banner.Banner;
//
//import so.bubu.ui.test.mylibrary.R;
//
///**
// * Created by zhengheng on 18/1/24.
// */
//public class BannerView extends FrameLayout {
//
//    public BannerView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        banner = (Banner) LayoutInflater.from(context).inflate(R.layout.banner_layout, null, false);
//        init();
//    }
//
//    private Banner banner;
//    private int delayTime = 400;
//    private boolean isAutoPlay = true;
//
//    public BannerView(Context context) {
//        this(context, null);
//    }
//
//    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public void init() {
//        banner.setDelayTime(delayTime);
//        banner.isAutoPlay(isAutoPlay);
////        banner.setIn
//    }
//}
