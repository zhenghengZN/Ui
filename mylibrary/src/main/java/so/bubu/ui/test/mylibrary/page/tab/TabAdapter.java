package so.bubu.ui.test.mylibrary.page.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by zhengheng on 18/2/11.
 */
public class TabAdapter<T extends Fragment> extends FragmentStatePagerAdapter {

    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<T> list = new ArrayList<>();
    public TabAdapter(FragmentManager fm, ArrayList<String> title) {
        super(fm);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return (T)list.get(position);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    public void setFragment(ArrayList<T> list){
        this.list = list;
    }
}
