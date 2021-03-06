package sc.mp3musicplayer.restclient;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sc.mp3musicplayer.models.STrack;

/**
 * Created by regulosarmiento on 30/05/16.
 */
public interface IRestClient {
    /**
     * Gets a list of tracks.
     * @param keyword Song or Artist that users put in the search view.
     * @param offset  Amount of tracks in a page.
     * @param client_id Developer ID that allows make use of the SoundCloud Api.
     * @return an {@link rx.Observable} with a response mapped to {@link java.util.List} of {@link STrack}.
     */
    @GET(RestUtils.TRACK_URL)
    Observable<List<STrack>> getTracks(@Query(RestUtils.QUERY) String keyword,
                                       @Query(RestUtils.LIMIT) int offset,
                                       @Query(RestUtils.ID) String client_id);
}
