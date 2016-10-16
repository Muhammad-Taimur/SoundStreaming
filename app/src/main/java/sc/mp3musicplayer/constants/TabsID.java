package sc.mp3musicplayer.constants;

/**
 * Created by regulosarmiento on 17/05/16.
 */
public enum TabsID {

    SEARCH(Constants.ID_SEARCH_TAB, Constants.TITLE_SEARCH_TAB),
    DOWNLOAD(Constants.ID_DOWNLOAD_TAB, Constants.TITLE_DOWNLOAD_TAB);

    private final int tabID;
    private final String tabTitle;

    TabsID(final int id, final String title){
        this.tabID = id;
        this.tabTitle = title;
    }

    public int getTabID() {
        return tabID;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public static TabsID getTabs(final int index){
        switch (index){
            case Constants.ID_SEARCH_TAB:
                return SEARCH;
            case Constants.ID_DOWNLOAD_TAB:
                return DOWNLOAD;
            default:
                return null;
        }
    }

    private static class Constants{
        public static final int ID_SEARCH_TAB = 0;
        public static final int ID_DOWNLOAD_TAB = 1;
        public static final String TITLE_SEARCH_TAB = "Home";
        public static final String TITLE_DOWNLOAD_TAB = "Songs";
    }
}
