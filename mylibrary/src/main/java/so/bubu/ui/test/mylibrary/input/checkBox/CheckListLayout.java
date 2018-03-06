package so.bubu.ui.test.mylibrary.input.checkBox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.LinkedHashMap;

import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/2/6.
 */
public class CheckListLayout extends LinearLayout {
    public CheckListLayout(Context context) {
        this(context, null);
    }

    private CheckGroup checkGroup;

    public CheckListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        View view = LayoutInflater.from(context).inflate(R.layout.checklistlayout, this, true);
        checkGroup = (CheckGroup) view.findViewById(R.id.checkgroup);
    }

    public void init(LinkedHashMap<String, Object> object, int type){
        checkGroup.init(object,type);
    }
}
