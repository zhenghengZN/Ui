package so.bubu.ui.test.mylibrary.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhengheng on 18/1/16.
 */
public class BannerActivityAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public BannerActivityAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    public BannerActivityAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BannerActivityAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {

    }
}
