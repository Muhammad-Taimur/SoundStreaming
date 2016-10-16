package sc.mp3musicplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sc.mp3musicplayer.constants.TabsID;
import sc.mp3musicplayer.ui.fragments.DownloadFragment;
import sc.mp3musicplayer.ui.fragments.SearchFragment;

/**
 * Created by regulosarmiento on 17/05/16.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_TABS = 2;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        final TabsID tabsID = TabsID.getTabs(position);
        switch (tabsID){
            case SEARCH:
                return new SearchFragment();
            case DOWNLOAD:
                return new DownloadFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final TabsID tabsID = TabsID.getTabs(position);
        switch (tabsID){
            case SEARCH:
                return TabsID.SEARCH.getTabTitle();
            case DOWNLOAD:
                return TabsID.DOWNLOAD.getTabTitle();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }
}
