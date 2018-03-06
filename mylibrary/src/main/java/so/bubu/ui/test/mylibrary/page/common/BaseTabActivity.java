package so.bubu.ui.test.mylibrary.page.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/10.
 */
public abstract class BaseTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_base);
        LinearLayout view = (LinearLayout) findViewById(R.id.content);
        view.addView(addBaseContenetView());
        doInCreateView();
    }

    public abstract View addBaseContenetView();


    public abstract void doInCreateView();

    public SlidingTabLayout getSlidingTabLayout() {
        SlidingTabLayout view = (SlidingTabLayout) findViewById(R.id.taobao_slidingTabLayout);
        return view;
    }

    public LinearLayout getTabLayout() {
        LinearLayout view = (LinearLayout) findViewById(R.id.tab_layout);
        return view;
    }

}
