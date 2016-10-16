package sc.mp3musicplayer.utilities;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

import java.io.File;
import java.lang.ref.WeakReference;


import sc.mp3musicplayer.R;
import sc.mp3musicplayer.constants.Constants.Folder;
import sc.mp3musicplayer.models.STrack;

import static sc.mp3musicplayer.utilities.FilesHelper.createDirectory;
import static sc.mp3musicplayer.utilities.FilesHelper.isExists;


/**
 * Created by regulosarmiento on 01/08/16.
 */
public class DownloadHelper {


    /**
     * Download tracks and images.
     * @param STrackToDownload The track that will be downloaded.
     * @param downloadManager A system service that handles long-running HTTP downloads {@link DownloadManager}
     */
    public static void DownloadTrack(final STrack STrackToDownload, final WeakReference<Context> context,
                                     final DownloadManager downloadManager){

        if(!isExists(STrackToDownload.getTitle())){
            // Download Mp3
            setUpDownloadManager(STrackToDownload.getStream_url(),
            createDirectory(Folder.TRACKS) + STrackToDownload.getMP3(), downloadManager);

            // Download Image
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  setUpDownloadManager(STrackToDownload.getArtwork_url(),
                  createDirectory(Folder.IMAGES) + STrackToDownload.getJPG(), downloadManager);
              } else {
                  ImageHelper.downloadImage(STrackToDownload, context);
            }
        }else{
            DialogHelper.showMessageDialog(context.get(), R.string.title_dialog, R.string.description_dialog);
        }

    }

    /**
     * Download and stores files (i.e Images or Mp3 files).
     * @param url  The url where the file is downloaded.
     * @param path The path where the file will be stored.
     * @param mDownManager A system service that handles long-running HTTP downloads.
     */
    public static void setUpDownloadManager(@NonNull final String url, @NonNull final String path, final DownloadManager mDownManager){
        Uri mUrl = Uri.parse(url);
        Uri mPath = Uri.fromFile(new File(path));
        DownloadManager.Request request = new DownloadManager.Request(mUrl);
        request.setDestinationUri(mPath);
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        mDownManager.enqueue(request);
    }
}
