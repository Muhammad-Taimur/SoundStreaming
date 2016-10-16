package sc.mp3musicplayer.mvp.search.interactor;

import java.util.List;

import sc.mp3musicplayer.models.STrack;

/**
 * Created by regulosarmiento on 30/05/16.
 */
public interface ISearchInteractorFinishedListener {
    /**
     *
     * @param STracks list of songs.
     */
    void onNetworkSuccess(final List<STrack> STracks);

    /**
     *
     * @param message An error from the server.
     */
    void onNetworkFailure(final Throwable message);
}
