package sc.mp3musicplayer.listeners.listview;

import sc.mp3musicplayer.adapters.TracksListAdapter;

/**
 * Created by regulosarmiento on 17/06/16.
 */
public interface IDetectScrollListener {

    void onUpScrolling();

    /**
     * Loads more tracks and adds them to the adapter {@link TracksListAdapter}.
     * @param getLastVisiblePosition The last track visible.
     * @param totalItemCount the number of tracks available.
     */
    void onDownScrolling(final int getLastVisiblePosition, final int totalItemCount);
}
