package so.bubu.ui.test.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import so.bubu.ui.test.mylibrary.page.tab.TabBaseFragment;
import so.bubu.ui.test.mylibrary.wiget.FatherViewPager;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
//        FrameLayout view = (FrameLayout) findViewById(R.id.parent);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction bt = manager.beginTransaction();
        TabBaseFragment tabBaseFragment = new TabBaseFragment() {
            public PagerAdapter setPagerAdapter() {
                MyPagerAdapter myPagerAdapter = new MyPagerAdapter(TabActivity.this.getSupportFragmentManager());
                return myPagerAdapter;
            }

            @Override
            public ArrayList setFragment(ArrayList list) {
                return null;
            }

            @Override
            public void doInOnCreateView(View view, FatherViewPager pager) {

            }
        };

        bt.add(R.id.parent, tabBaseFragment);
        bt.commit();
//        tabBaseFragment.tabViewSetViewPager();
    }


}
