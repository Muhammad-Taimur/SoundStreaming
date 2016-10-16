package sc.mp3musicplayer.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sc.mp3musicplayer.R;

/**
 * Created by regulosarmiento on 02/05/15.
 */

public class DrawerListAdapter extends BaseAdapter {

    private final Context mContext;
    private final String[] mItems;

    public DrawerListAdapter(final Context context){
        this.mContext = context;
        this.mItems = context.getResources().getStringArray(R.array.menu_items);
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public Object getItem(int position) {
        return mItems[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater inflater;
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.items_menu_lateral_listview, parent, false);
        }

        if (convertView != null && mItems != null) {
            setCells(convertView, position);
        }

        return convertView;
    }

    private void setCells(final View view, final int position){
        if (view != null) {
            try {
                setIcons(view, position);
                setTitles(view, position);
            }catch (final Exception e){
                Log.e("Error", "Error updating cells in Menu List!");
            }
        }
    }

    private void setIcons(final View view, final int position){
        final TypedArray icons = mContext.getResources().obtainTypedArray(R.array.menu_items_icons);
        final ImageView icon  = (ImageView) view.findViewById(R.id.item_icon);
        icon.setImageDrawable(icons.getDrawable(position));
        icons.recycle();
    }

    private void setTitles(final View view, final int position){
        final TextView nameTextView = (TextView) view.findViewById(R.id.item_title);
        nameTextView.setText(mItems[position]);
    }
}
