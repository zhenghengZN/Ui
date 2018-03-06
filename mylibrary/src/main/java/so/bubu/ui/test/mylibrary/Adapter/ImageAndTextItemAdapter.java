package so.bubu.ui.test.mylibrary.Adapter;

import android.content.Context;

import java.util.HashMap;
import java.util.LinkedList;

import so.bubu.ui.test.mylibrary.Adapter.common.ComRecyclerViewAdapter;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.bean.MineBean;

/**
 * Created by zhengheng on 17/12/26.
 */
public abstract class ImageAndTextItemAdapter extends ComRecyclerViewAdapter {
    private LinkedList<HashMap<String, Object>> mDatasList;

    public ImageAndTextItemAdapter(Context context, LinkedList<HashMap<String, Object>> mDatas, int itemLayoutId) {
        super(context, mDatas, R.layout.mine_cell);
        mDatasList = mDatas;
    }

    @Override
    public void convert(RecyclerViewHolder viewHolder, Object item, int position) {
        HashMap<String, Object> object = mDatasList.get(position);
        viewHolder.setText(R.id.tv_name, (String) object.get("title"));
        doOther(viewHolder, mDatasList.get(position), position);
    }

    public abstract void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position);

}
