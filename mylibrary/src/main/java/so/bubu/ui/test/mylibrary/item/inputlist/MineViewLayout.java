package so.bubu.ui.test.mylibrary.item.inputlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/11.
 */
public class MineViewLayout extends LinearLayout {
    public MineViewLayout(Context context) {
        this(context, null);
    }

    private MineViewList mineviewlist;

    public MineViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        View inflate = LayoutInflater.from(context).inflate(R.layout.mineviewlayout, this, true);
        mineviewlist = (MineViewList) inflate.findViewById(R.id.mineViewList);
    }

    public void init(JSONArray list) {
        mineviewlist.init(list);
    }
}
