package myactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.wiget.Html5Webview;


/**
 * webview
 * Created by Auro on 15/9/22.
 */
public class YkWebViewActivity extends AppCompatActivity {

    public final static String URL = "url";

    private Html5Webview webview;

    private boolean hasMainActivity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webview = new Html5Webview(this);
        setContentView(webview.getLayout());
        String url = null;
        if (getIntent() != null) {
            url = getIntent().getStringExtra(URL);
        }

        if (url == null) {
//            ToastHelper.showToast(R.string.error_url);
            Toast.makeText(this, R.string.error_url, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (!url.startsWith("http"))
            url = "http://" + url;
        initView(url);
    }

//    @Override
//    protected void doBack(int keyCode, KeyEvent event) {
//        if (hasMainActivity) {
//            NavigationHelper.finish(this, RESULT_OK, null);
//        } else {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
//    }
//
//    @Override
//    protected boolean isSwipeback() {
//        return (hasMainActivity = AppManager.getAppManager().hasActivity(MainActivity.class));
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && 0 == event.getRepeatCount()) { // 按下的如果是BACK，同时没有重复
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView(String url) {
        webview.getSettings().setLoadsImagesAutomatically(true);// 设置可以自动加载图片
        WebSettings mWebSettings = webview.getSettings();
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        webview.setHorizontalScrollBarEnabled(true);//设置水平滚动条
        webview.setVerticalScrollBarEnabled(false);//设置竖直滚动条
        webview.setBackgroundColor(Color.BLACK);
        mWebSettings.setBuiltInZoomControls(false);
        ViewGroup.LayoutParams params = webview.getLayoutParams();
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        params.height = FrameLayout.LayoutParams.MATCH_PARENT;
        webview.setLayoutParams(params);
        //http://v.youku.com/v_show/id_XMTI4NjA3OTU5Ng==.html?spm=a2h0k.8191407.0.0&from=s1.8-1-1.2  XMTI4NjA3OTU5Ng==
        // http://player.youku.com/player.php/sid/XMTI4NjA3OTU5Ng/partnerid/b1b8d127374e42ee/v.swf
        webview.setWebViewClient(new MyWebViewClient());
        String vid = getVid(url);
        String html = "<div id=\"youkuplayer\" style=\"width:100%;height:100%\"></div>  \n" +
                "<script type=\"text/javascript\" src=\"http://player.youku.com/jsapi\">  \n" +
                "    player = new YKU.Player('youkuplayer',{  \n" +
                "        styleid: '0',  \n" +
                "        client_id: 'b1b8d127374e42ee',  \n" +
                "        vid: '" + vid + "',  \n" +
                "        autoplay: true,  \n" +
                "        show_related: false,  \n" +
                "        allowFullScreen: true , \n" +
                "        newPlayer: true \n" +
                "    });  \n" +
                "</script>  ";
        webview.loadData(html, "text/html; charset=UTF-8", null);
    }

    private class MyWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        public void onPageFinished(WebView view, String url) {

        }

    }

    private String getVid(String Url) {
        int index = Url.indexOf("id_");
        int endindex = Url.indexOf(".html?");
        String vid = Url.substring(index + 3, endindex);
//        LogUtil.log.e("vid","vid" + vid);
        return vid;
    }
}
