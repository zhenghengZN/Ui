package so.bubu.ui.test.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by zhengheng on 18/1/15.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter{

    private String[]  title= {"title1","title2","title3"};
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
