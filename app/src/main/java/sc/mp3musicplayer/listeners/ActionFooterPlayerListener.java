package sc.mp3musicplayer.listeners;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.lang.ref.WeakReference;

import static sc.mp3musicplayer.constants.Constants.UPDATE_FOOTER;
import sc.mp3musicplayer.mpservice.MediaPlayerService;
import sc.mp3musicplayer.ui.activities.MediaPlayerActivity;
import sc.mp3musicplayer.views.FooterPlayerView.IFooterPlayerAction;

/**
 * Handles media player operations (Start and Pause) on the FooterPlayerView.
 */
public class ActionFooterPlayerListener implements IFooterPlayerAction {

    private MediaPlayerService mService;
    private WeakReference<Activity> mActivity;

    public ActionFooterPlayerListener(@NonNull final MediaPlayerService service, final Activity mActivity){
        this.mService = Preconditions.checkNotNull(service, "Service cannot be null");
        this.mActivity = new WeakReference<>(mActivity);
    }

    @Override
    public void onStart() {
        mService.start();
    }

    @Override
    public void onPause() {
        mService.pause();
    }

    @Override
    public void onClick() {
        if(mActivity.get() != null){
           mActivity.get().startActivityForResult(new Intent(mActivity.get(), MediaPlayerActivity.class), UPDATE_FOOTER);
        }
    }
}
