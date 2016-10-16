package sc.mp3musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.common.base.Preconditions;

import java.util.List;

import sc.mp3musicplayer.constants.TabsID;
import sc.mp3musicplayer.models.ITrack;
import sc.mp3musicplayer.models.STrack;
import sc.mp3musicplayer.views.TracksRowItemView;

/**
 * Created by regulosarmiento on 13/06/16.
 */
public class TracksListAdapter extends ArrayAdapter<ITrack>{

    private final List<ITrack> mTracks;

    public TracksListAdapter(final Context context, final List<? extends ITrack> tracks) {
        super(context, 0, (List<ITrack>) tracks);
        this.mTracks = (List<ITrack>) tracks;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        TracksRowItemView tracksRowItemView = (TracksRowItemView) convertView;

        if(tracksRowItemView == null){
            tracksRowItemView = TracksRowItemView.inflate(parent, getContext());
        }
        tracksRowItemView.setCellValues(getItem(position), position);
        tracksRowItemView.setTracks(getTracks());
        return tracksRowItemView;
    }

    @NonNull
    public List<ITrack> getTracks(){
        return Preconditions.checkNotNull(mTracks, "List of Tracks cannot be null");
    }

    public void updateListTracks(final List<? extends ITrack> tracks){
        clear();
        addAll(tracks);
        notifyDataSetChanged();
    }
}
