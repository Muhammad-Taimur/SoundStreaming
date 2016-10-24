package sc.mp3musicplayer.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.common.base.Optional;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import sc.mp3musicplayer.R;
import sc.mp3musicplayer.adapters.DrawerListAdapter;
import sc.mp3musicplayer.adapters.TabsPagerAdapter;
import static sc.mp3musicplayer.constants.Constants.UPDATE_FOOTER;
import sc.mp3musicplayer.listeners.ActionBarDrawerToggleListener;
import sc.mp3musicplayer.listeners.ActionFooterPlayerListener;
import sc.mp3musicplayer.listeners.listview.DrawerListListener;
import sc.mp3musicplayer.models.ITrack;

import sc.mp3musicplayer.mpservice.MediaPlayerConnection;
import sc.mp3musicplayer.slidingTabs.ScTabColorizer;
import sc.mp3musicplayer.slidingTabs.SlidingTabLayout;
import sc.mp3musicplayer.utilities.NetworkHelper;
import sc.mp3musicplayer.utilities.SaveInstanceHelper;
import sc.mp3musicplayer.utilities.ServiceHelper;

import sc.mp3musicplayer.utilities.ViewHelper;
import sc.mp3musicplayer.views.FooterPlayerView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    SlidingTabLayout mSlidingTabLayout;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Nullable
    @BindView(R.id.footer_player)
    FooterPlayerView mFooterPlayer;

    @BindView(R.id.list)
    ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    public static MediaPlayerConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpNavigationDrawer();
        setUpTabsSettings();
    }

    @Override
    protected void onStart() {
        super.onStart();
        NetworkHelper.addNoInternetView(findViewById(android.R.id.content),this);
        mServiceConnection = new MediaPlayerConnection();
        ServiceHelper.doBindService(new WeakReference<>(this), mServiceConnection);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mServiceConnection.getService() != null){
            SaveInstanceHelper.onSaveInstance(outState, mServiceConnection.getService());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SaveInstanceHelper.onRestoreInstance(savedInstanceState, mFooterPlayer, this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        ServiceHelper.doUnBindService(new WeakReference<>(this), mServiceConnection);
        super.onStop();
    }

    private void setUpToolbar(){
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void setUpTabsSettings(){
        final TabsPagerAdapter mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabsPagerAdapter);
        mSlidingTabLayout.setCustomTabColorizer(new ScTabColorizer(this));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private void setUpNavigationDrawer(){
        mDrawerToggle = new ActionBarDrawerToggleListener(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerList.setAdapter(new DrawerListAdapter(this));
        mDrawerList.setOnItemClickListener(new DrawerListListener(this, mDrawerLayout));
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setUpToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Makes the Footer Player {@link FooterPlayerView } visible at the bottom of the MainActivity's view
     * when the user clicks on an item of the Tracks List {@link sc.mp3musicplayer.views.TracksListView }.
     * @param visible True if the user clicks.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMakeFooterPlayerVisible(Boolean visible){
        ViewHelper.showOrHideView(Optional.fromNullable(mFooterPlayer), visible.booleanValue());
    }

    /**
     * Displays the track's image and title on the FooterPlayer and sets event listener
     * @param currentSTrack The track that the user has clicked on.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusDisplayTrackOnFooterPlayer(ITrack currentSTrack){
        if(mFooterPlayer == null) mFooterPlayer = new FooterPlayerView(this);
        mFooterPlayer.setCellValues(currentSTrack);
        mFooterPlayer.setFooterPlayerActionListener(new ActionFooterPlayerListener(mServiceConnection.getService(), this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == UPDATE_FOOTER){
            if(mServiceConnection.getService() != null){
              mFooterPlayer.setCellValues(mServiceConnection.getService().getCurrentTrack());
              mFooterPlayer.setFooterPlayerActionListener(new ActionFooterPlayerListener(mServiceConnection.getService(), this));
            }
        }
    }
}
