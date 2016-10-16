package sc.mp3musicplayer.mvp.search.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import sc.mp3musicplayer.mvp.search.interactor.SearchInteractor;
import sc.mp3musicplayer.mvp.search.view.ISearchView;

import static org.junit.Assert.*;

/**
 * Created by regulosarmiento on 06/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock
    private ISearchView view;
    @Mock
    private SearchInteractor mSearchInteractor;
    private SearchPresenter mSearchPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mSearchPresenter = new SearchPresenter(view);
    }

    @Test
    public void shouldRequestTracksOnSearchTrack() throws Exception {

//        doThrow(new RuntimeException()).when(mSearchInteractor).loadTracks("shakira", 50);
////        when(mSearchInteractor.getmRestClient().getTracks("shakira",50, RestUtils.CLIENT_ID)).thenReturn(any());
//
//        mSearchPresenter.searchTrack("shakira", 50);
//        verify(mSearchInteractor).loadTracks("shakira", 50);
//        verify(mSearchInteractor).getmRestClient().getTracks("shakira",50,RestUtils.CLIENT_ID)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        tracks -> {
//                            list = tracks;
//                        }
//                );
//        assertNotNull(list);
//        when(mRestClient.getTracks("",50,"")).thenReturn(mObservableTracks);
//        when(mObservableTracks.subscribeOn(Schedulers.newThread())).thenReturn(mObservableTracks);
//        when(mObservableTracks.observeOn(AndroidSchedulers.mainThread())).thenReturn(mObservableTracks);
//
//           mSearchPresenter.searchTrack("", 50);
//           verify(mSearchInteractor, times(1)).loadTracks("", 50);
//           verify(mObservableTracks).subscribeOn(Schedulers.newThread());
//           verify(mObservableTracks).observeOn(AndroidSchedulers.mainThread());

    }

    @Test
    public void shouldShowAListOfTracksOnNetworkSuccess() throws Exception {
        assertNotNull(view);
        mSearchPresenter.onNetworkSuccess(any(List.class));
        verify(view, times(1)).onSearchLoadedSuccess(any(List.class));
    }

    @Test
    public void shouldShowAnErrorMessageOnNetworkFailure() throws Exception {
        assertNotNull(view);
        mSearchPresenter.onNetworkFailure(any(Throwable.class));
        verify(view, times(1)).onSearchLoadedFailure(any(Throwable.class));
    }
}