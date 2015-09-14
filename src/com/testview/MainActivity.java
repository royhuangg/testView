package com.testview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import com.testview.ContentFragment;
import com.viewpagerindicator.TabPageIndicator;
import grandroid.view.Face;
import grandroid.view.LayoutMaker;

public class MainActivity extends Face {

    private static final String[] CONTENT = new String[]{"头条", "娱乐", "体育", "财经", "科技", "汽车", "NBA"};
    LayoutMaker maker;
    LayoutInflater inflater;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maker = new LayoutMaker(this);
        maker.setDrawableDesignWidth(this, 640);
        //setContentView(R.layout.main);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.main, null);

        findView();
    }

    private void findView() {
        FragmentPagerAdapter adapter = new WYNewsAdapter(getSupportFragmentManager());

        maker.addColLayout(false, maker.layFF());
        {
            ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
            pager.setAdapter(adapter);

            TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
            indicator.setViewPager(pager);
            maker.add(view);

        }

    }

    class WYNewsAdapter extends FragmentPagerAdapter {

        public WYNewsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }

}
