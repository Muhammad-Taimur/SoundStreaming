package sc.mp3musicplayer.ui.fragments;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sc.mp3musicplayer.R;
import sc.mp3musicplayer.adapters.TracksListAdapter;
import sc.mp3musicplayer.constants.TabsID;
import sc.mp3musicplayer.listeners.listview.OnItemClickListener;
import sc.mp3musicplayer.models.DTrack;
import sc.mp3musicplayer.mvp.download.presenter.DownloadPresenter;
import sc.mp3musicplayer.mvp.download.view.IDownloadView;
import sc.mp3musicplayer.utilities.SaveInstanceHelper;
import sc.mp3musicplayer.utilities.SnackbarHelper;
import sc.mp3musicplayer.utilities.ViewHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment implements IDownloadView{

    @Nullable
    @BindView(R.id.download_results)
    ListView mListDownloadResults;

    @Nullable
    @BindView(R.id.no_tracks)
    TextView mNoTracks;


    private TracksListAdapter mListTracksAdapter;
    private DownloadPresenter mPresenter;
    private Unbinder mUnbinder;

    public DownloadFragment() {}

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DownloadPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getListOfTracks();
        getActivity().registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(onComplete);
    }


    @Override
    public void onLoadedSuccess(List<DTrack> tracks) {
        Preconditions.checkNotNull(mListDownloadResults, "List cannot be null");
        if(tracks.size() > 0){

            if(ViewHelper.isViewVisible(Optional.fromNullable(mNoTracks))){
                ViewHelper.showOrHideView(Optional.fromNullable(mNoTracks),false);
            }

            if(mListTracksAdapter == null){
                mListTracksAdapter = new TracksListAdapter(getActivity(), tracks);
                mListDownloadResults.setAdapter(mListTracksAdapter);
                mListDownloadResults.setOnItemClickListener(new OnItemClickListener(getActivity()));
            }else{
                mListTracksAdapter.updateListTracks(tracks);
            }

        }else{
            ViewHelper.showOrHideView(Optional.fromNullable(mNoTracks), true);
        }
    }

    @Override
    public void onLoadedFailure() {
        if(mListTracksAdapter != null){
            mListTracksAdapter.clear();
            mListTracksAdapter.notifyDataSetChanged();
        }
        ViewHelper.showOrHideView(Optional.fromNullable(mNoTracks), true);
    }

    /**
     * Updates the list of track when a new item has been downloaded.
     */
    private final BroadcastReceiver onComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Preconditions.checkNotNull(getView(), "View cannot be null");
            mPresenter.getListOfTracks();
            SnackbarHelper.showMessage(getView(), R.string.track_downloaded);
        }
    };

    /**
     * Indicates that an item from the list of tracks has been deleted.
     * @param remove {@link Boolean}
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusRemoveTrackFromList(Integer remove){
        Preconditions.checkNotNull(getView(), "View cannot be null");
        mListTracksAdapter.notifyDataSetChanged();
        mPresenter.getListOfTracks();
        SnackbarHelper.showMessage(getView(), R.string.track_deleted);
    }
}
