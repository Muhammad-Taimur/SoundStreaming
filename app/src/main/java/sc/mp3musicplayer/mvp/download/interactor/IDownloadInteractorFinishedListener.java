package sc.mp3musicplayer.mvp.download.interactor;

import java.util.List;

import sc.mp3musicplayer.models.DTrack;
import sc.mp3musicplayer.models.STrack;

/**
 * Created by regulosarmiento on 24/07/16.
 */
public interface IDownloadInteractorFinishedListener {

    /**
     * Gets a list of tracks from the Download folder.
     * @param tracks List of tracks.
     */
    void onSuccess(final List<DTrack> tracks);

    /**
     * Alerts when there's not any track in the download folder.
     */
    void onAlert();
}
