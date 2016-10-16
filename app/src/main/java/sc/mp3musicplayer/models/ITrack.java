package sc.mp3musicplayer.models;

/**
 * Created by regulosarmiento on 12/08/16.
 */
public interface ITrack {

    /**
     * Gets the Track's title that will be played.
     * @return The name of the track.
     */
    String getTitle();

    /**
     * Gets the Track's image that will be played.
     * @return Either a url or path.
     */
    String getImage();

    /**
     * Gets the streaming url or path
     * @return Either a url or path.
     */
    String getUrl();

    /**
     * @return Jpg format
     */
    String getJPG();

    /**
     * @return mp3 format
     */
    String getMP3();
}
