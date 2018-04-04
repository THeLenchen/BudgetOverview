package com.example.rauch.malena.budgetoverview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Test on 16.03.2018.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1_depts tab1 = new Tab1_depts();
                return tab1;
            case 1:
                Tab2_home tab2 = new Tab2_home();
                return tab2;
            case 2:
                Tab3_transact tab3 = new Tab3_transact();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "DEPTS";
            case 1:
                return "HOME";
            case 2:
                return "TRANSAKT";
        }
        return null;
    }
}