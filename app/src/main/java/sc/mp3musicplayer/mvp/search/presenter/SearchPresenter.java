package sc.mp3musicplayer.mvp.search.presenter;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.lang.ref.WeakReference;
import java.util.List;

import sc.mp3musicplayer.models.STrack;
import sc.mp3musicplayer.mvp.search.interactor.ISearchInteractorFinishedListener;
import sc.mp3musicplayer.mvp.search.interactor.SearchInteractor;
import sc.mp3musicplayer.mvp.search.view.ISearchView;

/**
 * Created by regulosarmiento on 30/05/16.
 */
public class SearchPresenter implements ISearchPresenter, ISearchInteractorFinishedListener {
    /******************************************************************************************
      SearchPresenter retrieves data {@link STrack} from the model {@link SearchInteractor } and
      notifies the view {@link sc.mp3musicplayer.ui.fragments.SearchFragment } to display it.
     ******************************************************************************************/

    private final SearchInteractor mInteractor;
    private final WeakReference<ISearchView> mView;

    public SearchPresenter(@NonNull final ISearchView view){
       this.mView = new WeakReference<>(view);
       this.mInteractor = new SearchInteractor(this);
    }

    @Override
    public void searchTrack(final String keyword) {
        mInteractor.loadTracks(keyword);
    }

    @Override
    public void onNetworkSuccess(@NonNull final List<STrack> STracks) {
        Preconditions.checkNotNull(STracks, "List of STracks cannot be null");
        if(mView.get() != null) mView.get().onSearchLoadedSuccess(STracks);
    }

    @Override
    public void onNetworkFailure(final Throwable message) {
        if(mView.get() != null) mView.get().onSearchLoadedFailure(message);
    }
}
