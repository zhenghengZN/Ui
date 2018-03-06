package so.bubu.ui.test.mylibrary.Toast;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/10.
 */
public class ToastDialog extends Dialog {

    public ToastDialog(Context context, int duration) {
        super(context);
        this.mDuration = duration;
    }

    public ToastDialog(Context context) {
        super(context);
    }

    private static String loadString = "数据加载中";
    private static String successString = "已完成";

    public final static int TYPE_SUCCESS = 1;
    public final static int TYPE_LOAD = 2;

    private int mDuration = 3 * 1000;//不关闭
    private Handler mHandle = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hinttoast);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER;
//        attributes.alpha = 1f;
        attributes.dimAmount = 0f;
        window.setAttributes(attributes);

        ImageView img = (ImageView) findViewById(R.id.toast_img);
        SpinView progressBar = (SpinView) findViewById(R.id.toast_progressBar);
        TextView title = (TextView) findViewById(R.id.toast_title);

        switch (type) {
            case TYPE_SUCCESS:
                img.setVisibility(View.VISIBLE);
                title.setText(successString);
                break;
            case TYPE_LOAD:
                progressBar.setVisibility(View.VISIBLE);
                title.setText(loadString);
                break;
        }
    }

    private int type = 1;

    public void setType(int type) {
        this.type = type;
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };


    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    @Override
    public void show() {
        super.show();
        if (mDuration > 0) {
            mHandle.removeCallbacks(mRunnable);
            mHandle.postDelayed(mRunnable, mDuration);
        }
    }
}
