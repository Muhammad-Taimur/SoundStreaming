package sc.mp3musicplayer.mvp.download.interactor;

import android.util.Log;

import com.google.common.collect.FluentIterable;

import sc.mp3musicplayer.utilities.FilesHelper;

/**
 * Created by regulosarmiento on 24/07/16.
 */
public class DownloadInteractor {
    /******************************************************************************************
     - DownloadInteractor is the model that gets data(list of tracks) from the Download folder.
     ******************************************************************************************/

    private final IDownloadInteractorFinishedListener mListener;

    public DownloadInteractor(IDownloadInteractorFinishedListener listener){
        mListener = listener;
    }

    public void loadTracksFromDownloadPath(){
        if(FilesHelper.getListOfTracks()!= null && !FilesHelper.getListOfTracks().isEmpty()){
             mListener.onSuccess(FilesHelper.getListOfTracks());
          } else {
             mListener.onAlert();
        }
    }
}
