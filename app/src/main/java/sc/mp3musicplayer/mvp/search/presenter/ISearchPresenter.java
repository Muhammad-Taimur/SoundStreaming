package sc.mp3musicplayer.mvp.search.presenter;

/**
 * Created by regulosarmiento on 30/05/16.
 */
public interface ISearchPresenter {

    /** Calls the API to search for the keyword that the user has typed.
     * @param keyword Song or Artist that users put in the search view.
     */
    void searchTrack(final String keyword);
}
