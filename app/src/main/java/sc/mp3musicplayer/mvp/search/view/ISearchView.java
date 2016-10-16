package sc.mp3musicplayer.mvp.search.view;

import java.util.List;

import sc.mp3musicplayer.models.STrack;

/**
 * Created by regulosarmiento on 30/05/16.
 */
public interface ISearchView {

    /**
     * Displays a ListView with a list of STracks
     * @param STracks list of songs/audios.
     */
    void onSearchLoadedSuccess(final List<STrack> STracks);

    /**
     * Shows an ImageView and two TextViews
     * @param message An error from the server.
     */
    void onSearchLoadedFailure(final Throwable message);
}
