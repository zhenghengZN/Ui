package so.bubu.ui.test.mylibrary.page;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.button.StrokeButton;
import so.bubu.ui.test.mylibrary.page.common.BaseCompatActivity;

/**
 * Created by zhengheng on 18/2/9.
 */
public class MsgActivity extends BaseCompatActivity {

    private TextView msgTitle, msgDesc, footLink, footText;
    private ImageView msgIcon;
    private StrokeButton mBtnPrimary, mBtnDefault;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.msg_activity);
        msgIcon = (ImageView) findViewById(R.id.msg_icon);
        msgTitle = (TextView) findViewById(R.id.msg_title);
        msgDesc = (TextView) findViewById(R.id.msg_desc);
        mBtnPrimary = (StrokeButton) findViewById(R.id.btn_primary);
        mBtnDefault = (StrokeButton) findViewById(R.id.btn_default);
        footLink = (TextView) findViewById(R.id.footer_link);
        footText = (TextView) findViewById(R.id.footer_text);
    }

    @Override
    protected void doBack(int keyCode, KeyEvent event) {

    }

    public void init() {

    }
}
