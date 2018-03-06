package so.bubu.ui.test.mylibrary.page.tab;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Utils.MyJsonUtil;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.wiget.FatherViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class TabBaseFragment<T extends Fragment> extends Fragment {


    public TabBaseFragment() {
    }

    private View view;
    private SlidingTabLayout tab;
    private FatherViewPager pager;
    private ArrayList<T> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tabview_layout, null);
        pager = (FatherViewPager) view.findViewById(R.id.tab_viewpager);
        TabAdapter tabAdapter = new TabAdapter(getChildFragmentManager(), titles);
        tabAdapter.setFragment(setFragment(list));
        pager.setAdapter(tabAdapter);
        tab = (SlidingTabLayout) view.findViewById(R.id.taobao_slidingTabLayout);
        tab.setViewPager(pager);
        doInOnCreateView(view, pager);
        return view;
    }

    private ArrayList<String> titles = new ArrayList<>();

    public void init(JSONArray array) {
        ArrayList<JSONObject> jsonObjects = MyJsonUtil.JsonArray2JsonObject(array);
        for (JSONObject object : jsonObjects) {
            try {
                String title = (String) object.get("title");
                titles.add(title);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract <T extends Fragment> ArrayList<T> setFragment(ArrayList<T> list);

    public abstract void doInOnCreateView(View parentView, FatherViewPager pager);
}
