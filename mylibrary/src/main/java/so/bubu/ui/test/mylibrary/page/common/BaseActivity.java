package so.bubu.ui.test.mylibrary.page.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.button.SolidButton;

/**
 * Created by zhengheng on 18/1/2.
 */
public abstract class BaseActivity extends BaseCompatActivity {

    private LinearLayout view;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base);
        view = (LinearLayout) findViewById(R.id.base_scroll_content);
        view.addView(addBaseContenetView(view));
        doInCreateView();
    }

    public abstract View addBaseContenetView(LinearLayout view);


    public abstract void doInCreateView();

    /**
     * 设置后退按钮
     */
    public void setBackClick() {
        findViewById(R.id.fl_back).setVisibility(View.VISIBLE);
        findViewById(R.id.fl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack(-1, null);
            }
        });
    }

    /**
     * 设置后退图标
     */
    public ImageView setBackImage(int ResId) {
        ImageView view = (ImageView) findViewById(R.id.tv_back);
        findViewById(R.id.fl_back).setVisibility(View.VISIBLE);
        view.setImageResource(ResId);
        return view;
    }

    /**
     * 设置标题
     */
    public TextView setTitleView(String title) {
        TextView view = (TextView) findViewById(R.id.tv_title);
        view.setText(title);
        view.setVisibility(View.VISIBLE);
        return view;
    }


    public SolidButton setDataButton(){
        SolidButton btn = (SolidButton) findViewById(R.id.data_button);
        btn.setVisibility(View.VISIBLE);
        return btn;
    }

}
