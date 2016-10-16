package sc.mp3musicplayer.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import sc.mp3musicplayer.R;
import sc.mp3musicplayer.adapters.TracksListAdapter;
import sc.mp3musicplayer.constants.TabsID;
import sc.mp3musicplayer.listeners.ActionEditTextListener;
import sc.mp3musicplayer.listeners.listview.OnItemClickListener;
import sc.mp3musicplayer.listeners.listview.TracksListScrollListener;
import sc.mp3musicplayer.models.ITrack;
import sc.mp3musicplayer.models.STrack;
import sc.mp3musicplayer.mvp.search.presenter.SearchPresenter;
import sc.mp3musicplayer.mvp.search.view.ISearchView;
import sc.mp3musicplayer.utilities.DeviceHelper;
import sc.mp3musicplayer.utilities.ViewHelper;
import sc.mp3musicplayer.views.TracksListView;

/**
 * Provides a search engine to search music/audio by artist or song, and displays a list of tracks
 * when the user makes a query.
 */
public class SearchFragment extends Fragment implements ISearchView{

    @Nullable
    @BindView(R.id.img_search)
    ImageView mImgViewSearch;

    @Nullable
    @BindView(R.id.search_description)
    TextView mSearchDescription;

    @Nullable
    @BindView(R.id.search_et)
    EditText mSearchEditText;

    @Nullable
    @BindView(R.id.no_search_results)
    ImageView mNoSearchResults;

    @Nullable
    @BindView(R.id.no_search_results_title)
    TextView mNoSearchResultsTitle;

    @Nullable
    @BindView(R.id.no_search_results_description)
    TextView mNoSearchResultsDesc;

    @Nullable
    @BindView(R.id.search_results)
    TracksListView mListSearchResults;

    @Nullable
    @BindView(R.id.BallSpinLoader)
    AVLoadingIndicatorView mBallSpinLoader;

    private SearchPresenter mPresenter;
    private TracksListAdapter mListTracksAdapter;
    private TracksListScrollListener mListTrackScroll;
    private Unbinder mUnbinder;

    public SearchFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // Retains Fragment's state (i.e portrait to landscape)
        mPresenter = new SearchPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setSearchEditTextView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        setSearchEditTextView();
        onHideKeyBoard();
        rePopulateList();
    }

    @Override
    public void onSearchLoadedSuccess(List<STrack> tracks) {
        if(mListTracksAdapter == null){
            mListTracksAdapter = new TracksListAdapter(getActivity(), tracks);
            populateList(mListTracksAdapter);
        }else{
            mListTracksAdapter.updateListTracks(tracks);
            setListViewListener();
        }
        setBallSpinLoaderVisibility(false);
    }

    @Override
    public void onSearchLoadedFailure(Throwable message) {
        if(mListTracksAdapter != null) mListTracksAdapter.clear();
        setNoSearchResultsVisibility(true);
        setBallSpinLoaderVisibility(false);
    }

    @OnClick(R.id.ic_search)
    public void startSearching() {
        // Gets what the user types into the search engine and calls the api.
        assert mSearchEditText != null;
        if(ViewHelper.isEditTextEmpty(mSearchEditText)){
            setBallSpinLoaderVisibility(true);
            mPresenter.searchTrack(mSearchEditText.getText().toString());
            onHideKeyBoard();
        }
    }

    private void setSearchEditTextView() {
        if (mSearchEditText != null){
            mSearchEditText.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    // Gained Focus then hide the views above the edit text view.
                    setViewsVisibility(false);
                } else if (v instanceof EditText && Strings.isNullOrEmpty(((EditText) v).getText().toString())) {
                    // Lost Focus and there is nothing in the edit text view; therefore show the views above it again!
                    if (mListTracksAdapter == null) setViewsVisibility(true);
                    setNoSearchResultsVisibility(false);
                    onHideKeyBoard();
                }
            });
            mSearchEditText.setOnEditorActionListener(new ActionEditTextListener(mPresenter, mBallSpinLoader));
        }

        if(DeviceHelper.isDeviceOrientationLandscape(getActivity())){
            setViewsVisibility(false);
        }
    }

    private void populateList(final TracksListAdapter adapter){
        assert mListSearchResults != null;
        mListSearchResults.setAdapter(adapter);
        setListViewListener();
    }

    /**
     * Repopulates the ListView when:
     * - Screen configuration changes (i.e Portrait to Landscape).
     * - Another activity comes into foreground and then the user returns to our previous activity.
     */
    private void rePopulateList(){
        if(mListTracksAdapter != null){
            assert mListSearchResults != null;
            mListSearchResults.setAdapter(mListTracksAdapter);
            setListViewListener();
        }
    }

    private void setListViewListener(){
        assert mListSearchResults != null;
        mListSearchResults.setOnItemClickListener(new OnItemClickListener(getActivity()));
        mListSearchResults.setOnDetectScrollListener(setScrollList());
    }

    private TracksListScrollListener setScrollList(){
        assert mSearchEditText != null;
        if(mListTrackScroll == null){
            mListTrackScroll = new TracksListScrollListener(mSearchEditText.getText().toString(), mListTracksAdapter);
         } else{
            // Initialise variables with a new keyword to search for.
            mListTrackScroll.setNewSearch(mSearchEditText.getText().toString());
        }
        return mListTrackScroll;
    }

    private void setViewsVisibility(final boolean visible){
        ViewHelper.showOrMakeViewGone(Optional.fromNullable(mImgViewSearch), visible);
        ViewHelper.showOrMakeViewGone(Optional.fromNullable(mSearchDescription), visible);
    }

    private void setNoSearchResultsVisibility(final boolean visible){
        ViewHelper.showOrMakeViewGone(Optional.fromNullable(mNoSearchResultsTitle), visible);
        ViewHelper.showOrMakeViewGone(Optional.fromNullable(mNoSearchResultsDesc), visible);
        ViewHelper.showOrMakeViewGone(Optional.fromNullable(mNoSearchResults), visible);
    }

    private boolean isNoSearchResultsVisible(){
        return (ViewHelper.isViewVisible(Optional.fromNullable(mNoSearchResultsTitle)) &&
                ViewHelper.isViewVisible(Optional.fromNullable(mNoSearchResultsDesc)) &&
                ViewHelper.isViewVisible(Optional.fromNullable(mNoSearchResults)));
    }

    private void setBallSpinLoaderVisibility(final boolean visible){
        if(isNoSearchResultsVisible() && visible){
            setNoSearchResultsVisibility(false);
            showOrHideBallSpinLoader(true);
        }else if((!isNoSearchResultsVisible() && visible) || visible){
            showOrHideBallSpinLoader(true);
        }else{
            showOrHideBallSpinLoader(false);
        }
    }

    private void showOrHideBallSpinLoader(final boolean visible){
        ViewHelper.showOrMakeViewGone(Optional.fromNullable(mBallSpinLoader), visible);
    }

    private void onHideKeyBoard(){
        ViewHelper.hideKeyboard(Optional.fromNullable(mSearchEditText), getActivity());
    }

}
