package sc.mp3musicplayer.mvp.download.view;

import java.util.List;

import sc.mp3musicplayer.models.DTrack;
import sc.mp3musicplayer.models.STrack;

/**
 * Created by regulosarmiento on 24/07/16.
 */
public interface IDownloadView {

    /**
     * Displays a ListView with a list of STracks
     * @param tracks list of songs/audios.
     */
    void onLoadedSuccess(final List<DTrack> tracks);

    /**
     * Reports that there are not tracks in the download folder.
     */
    void onLoadedFailure();
}
