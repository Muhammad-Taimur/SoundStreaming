package sc.mp3musicplayer.listeners;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.lang.ref.WeakReference;

/**
 *  Gets reference to the Drawer layout and sets event listener
 */
public class ActionBarDrawerToggleListener extends ActionBarDrawerToggle {

    private WeakReference<Activity> mActivity;

    public ActionBarDrawerToggleListener(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.mActivity = new WeakReference<>(activity);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        if (mActivity.get() != null) mActivity.get().invalidateOptionsMenu();
        syncState();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        if (mActivity.get() != null) mActivity.get().invalidateOptionsMenu();
        syncState();
    }
}
