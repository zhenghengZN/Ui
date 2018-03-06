package so.bubu.ui.test.mylibrary.page;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;


import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.page.common.BaseCompatActivity;

/**
 * Created by zhengheng on 18/1/15.
 */
public abstract class TopImageActivity extends BaseCompatActivity {
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.avtivity_topimage);
        findViewById(R.id.fl_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFlPhotoLayoutClick(v);
            }
        });

        LinearLayout content = (LinearLayout) findViewById(R.id.content);
        content.addView(addBaseContenetView());
        doInCreateView();
    }


    public abstract View addBaseContenetView();

    public abstract void doInCreateView();

    public abstract void setFlPhotoLayoutClick(View v);

    @Override
    protected void doBack(int keyCode, KeyEvent event) {
        TopImageActivity.this.setBackAnim();
        TopImageActivity.this.finish();
    }


}
