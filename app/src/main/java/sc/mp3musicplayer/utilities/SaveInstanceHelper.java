package sc.mp3musicplayer.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Optional;

import java.lang.ref.WeakReference;

import sc.mp3musicplayer.constants.Constants;
import sc.mp3musicplayer.mpservice.MediaPlayerService;
import sc.mp3musicplayer.views.FooterPlayerView;

/**
 * Created by regulosarmiento on 13/07/16.
 */
public class SaveInstanceHelper {

    /**
     * Saves the FooterPlayer data when the user changes the device orientation. (i.e Portrait to landscape)
     * @param outState {@link Bundle} The object which saves the data.
     * @param mService {@link MediaPlayerService}
     */
    public static void onSaveInstance(Bundle outState, @NonNull MediaPlayerService mService){
        if(mService.canISaveData()){
            outState.putString(Constants.TRACK_TITLE, mService.getCurrentTrack().getTitle());
            outState.putString(Constants.TRACK_IMAGE, mService.getCurrentTrack().getImage());
            outState.putInt(Constants.TRACK_POSITION, mService.getCurrentPosition());
            outState.putBoolean(Constants.CAN_I_SAVE_IT, mService.canISaveData());
        }
    }

    /**
     * Restores the data and displays it on the FooterPlayer.
     * @param savedInstanceState The object that restores the data.
     */
    public static void onRestoreInstance(Bundle savedInstanceState, @Nullable FooterPlayerView view, Activity mActivity){
        if(savedInstanceState != null && savedInstanceState.getBoolean(Constants.CAN_I_SAVE_IT)){
            if(view == null) {
                view = new FooterPlayerView(mActivity.getApplicationContext());
            }
            ViewHelper.showOrHideView(Optional.fromNullable(view), true);
            view.setTrackImage(savedInstanceState.getString(Constants.TRACK_IMAGE));
            view.setTrackTitle(savedInstanceState.getString(Constants.TRACK_TITLE));
            ServiceHelper.startService(new WeakReference<>(mActivity), savedInstanceState.getInt(Constants.TRACK_POSITION));
        }
    }
}
