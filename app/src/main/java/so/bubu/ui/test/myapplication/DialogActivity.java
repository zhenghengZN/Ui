package so.bubu.ui.test.myapplication;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import so.bubu.ui.test.mylibrary.Dialog.ActionSheet;
import so.bubu.ui.test.mylibrary.Dialog.ConfirmDialog;
import so.bubu.ui.test.mylibrary.button.StrokeButton;
import so.bubu.ui.test.mylibrary.page.common.BaseActivity;

/**
 * Created by zhengheng on 18/2/8.
 */
public class DialogActivity extends BaseActivity {
    @Override
    public View addBaseContenetView(LinearLayout view) {
        StrokeButton button = new StrokeButton(this);
        button.setSize("big");
        button.setText("ActionSheet");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSheet actionSheet = new ActionSheet(DialogActivity.this);
//                actionSheet.init(titles);
//                actionSheet.show();
                ConfirmDialog confirmdialog = new ConfirmDialog(DialogActivity.this);
                confirmdialog.show();
            }
        });
        view.addView(button);
        return new View(this);
    }

    private ArrayList<String> titles = new ArrayList<>();

    public void initData() {
        titles.add("A");
        titles.add("B");
        titles.add("C");
    }

    @Override
    public void doInCreateView() {

    }

    @Override
    protected void doBack(int keyCode, KeyEvent event) {

    }
}
