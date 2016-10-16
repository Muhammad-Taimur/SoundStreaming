package sc.mp3musicplayer.mvp.download.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import sc.mp3musicplayer.models.DTrack;
import sc.mp3musicplayer.models.STrack;
import sc.mp3musicplayer.mvp.download.interactor.DownloadInteractor;
import sc.mp3musicplayer.mvp.download.interactor.IDownloadInteractorFinishedListener;
import sc.mp3musicplayer.mvp.download.view.IDownloadView;

/**
 * Created by regulosarmiento on 24/07/16.
 */
public class DownloadPresenter implements IDownloadPresenter, IDownloadInteractorFinishedListener{
    /******************************************************************************************
     DownloadPresenter retrieves a list of tracks from the model {@link DownloadInteractor } and
     notifies the view {@link sc.mp3musicplayer.ui.fragments.DownloadFragment} to display it.
     ******************************************************************************************/

    private final DownloadInteractor mInteractor;
    private final WeakReference<IDownloadView> mView;

    public DownloadPresenter(@NonNull final IDownloadView view){
        this.mView = new WeakReference<>(view);
        this.mInteractor = new DownloadInteractor(this);
    }

    @Override
    public void getListOfTracks() {
        mInteractor.loadTracksFromDownloadPath();
    }

    @Override
    public void onSuccess(List<DTrack> tracks) {
        if(mView.get() != null) mView.get().onLoadedSuccess(tracks);
    }

    @Override
    public void onAlert() {
        if(mView.get() != null) mView.get().onLoadedFailure();
    }

}
