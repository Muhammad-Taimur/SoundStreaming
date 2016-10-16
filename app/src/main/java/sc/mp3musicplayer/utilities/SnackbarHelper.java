package sc.mp3musicplayer.utilities;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import sc.mp3musicplayer.R;

/**
 * Created by regulosarmiento on 03/09/16.
 */
public class SnackbarHelper {

    /**
     * Shows a message
     * @param view The view where the message is going to be shown.
     * @param resID {@link String} message.
     */
    public static void showMessage(final View view, final int resID){
        final Snackbar mSnackbar = Snackbar.make(view , resID, Snackbar.LENGTH_LONG);
        final TextView mTextview = (TextView) mSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        mTextview.setTextColor(Color.GRAY);
        mSnackbar.show();
    }
}
