package sc.mp3musicplayer.utilities;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.google.common.base.Preconditions;

import sc.mp3musicplayer.constants.Constants;

/**
 * Created by regulosarmiento on 24/05/16.
 */
public class ColorHelper {

    /**
     * Helper function that gets a colour resource.
     * @param context Context of the view.
     * @param resID   Colour resource.
     * @return a colour {@link android.graphics.Color}.
     */
    public static final int getColor(final Context context, final int resID) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        if (DeviceHelper.getAndroidVersion() >= Constants.ANDROID_VERSION) {
            return ContextCompat.getColor(context, resID);
        } else {
            return context.getResources().getColor(resID);
        }
    }

}
