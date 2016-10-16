package sc.mp3musicplayer.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import sc.mp3musicplayer.R;

/**
 * Created by regulosarmiento on 14/06/16.
 */
public class NetworkHelper {

    /**
     * Checks if the device has internet connection or not.
     * @param context {@link sc.mp3musicplayer.ui.activities.MainActivity}
     * @return true if the device is connected to internet, false otherwise.
     */
    public static boolean isInternetConnection(final Context context) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Adds a view on the top of the main Activity when there's not internet connection.
     * @param context {@link sc.mp3musicplayer.ui.activities.MainActivity}
     */
    public static void addNoInternetView(final Context context) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        final Activity activity = (Activity) context;
        final FrameLayout rootLayout = (FrameLayout) activity.findViewById(android.R.id.content);
        final View noConnectionMessage = rootLayout.findViewById(R.id.no_internet);
        if (!isInternetConnection(context)) {
            if (noConnectionMessage == null) {
               View.inflate(context, R.layout.no_internet_connection, rootLayout);
            }
        } else {
            if (noConnectionMessage != null) {
                rootLayout.removeView(noConnectionMessage);
            }
        }
    }

    public static void addNoInternetView(final View view, final Context context){
        final Snackbar mSnackbar = Snackbar.make(view , R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE);
        final TextView mTextview = (TextView) mSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        mTextview.setTextColor(Color.LTGRAY);

        if (!isInternetConnection(context)) {
            if(!mSnackbar.isShown()){
                mSnackbar.show();
            }
        }else{
            if(mSnackbar.isShown()){
                mSnackbar.dismiss();
            }
        }

        mSnackbar.setActionTextColor(Color.RED);
        mSnackbar.setAction(R.string.retry, v -> {
            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });

    }

}
