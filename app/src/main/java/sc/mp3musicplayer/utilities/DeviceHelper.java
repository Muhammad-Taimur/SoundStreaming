package sc.mp3musicplayer.utilities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

/**
 * Created by regulosarmiento on 24/07/16.
 */
public class DeviceHelper {

    /**
     * Checks the device orientation
     * @param context {@link sc.mp3musicplayer.ui.activities.MainActivity}
     * @return true if the device orientation is landscape or false otherwise.
     */
    public static boolean isDeviceOrientationLandscape(final Context context){
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    /**
     * Gets device's Android version.
     * @return Android version
     */
    public static int getAndroidVersion(){
        return Build.VERSION.SDK_INT;
    }
}
