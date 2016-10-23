package sc.mp3musicplayer.listeners.listview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import com.google.common.base.Preconditions;

import sc.mp3musicplayer.R;
import sc.mp3musicplayer.constants.Constants;
import sc.mp3musicplayer.mpservice.MediaPlayerService;
import sc.mp3musicplayer.utilities.DialogHelper;

/**
 * Created by regulosarmiento on 08/10/16.
 */
public class DrawerListListener implements AdapterView.OnItemClickListener {

    private final int HOW_IT_WORKS = 0;
    private final int ABOUT = 1;
    private final int SHARE = 2;
    private final int QUIT = 3;
    private Context mContext;

    public DrawerListListener(final @NonNull Context context){
        this.mContext = Preconditions.checkNotNull(context);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case HOW_IT_WORKS:
                DialogHelper.showMessageDialog(mContext, R.string.how, R.string.how_it_works);
                break;
            case ABOUT:
                DialogHelper.showMessageDialog(mContext, R.string.about_app, R.string.about);
                break;
            case SHARE:
                shareApp();
                break;
            case QUIT:
                (mContext).stopService(new Intent(mContext, MediaPlayerService.class));
                ((Activity)mContext).finish();
                break;
        }

    }

    public void shareApp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "App's url to share");
        sendIntent.setType(Constants.TYPE);
        mContext.startActivity(sendIntent);
    }
}
