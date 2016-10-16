package sc.mp3musicplayer.slidingTabs;

import android.content.Context;

import sc.mp3musicplayer.R;
import sc.mp3musicplayer.utilities.ColorHelper;

/**
 * Created by regulosarmiento on 23/05/16.
 */
public class ScTabColorizer implements SlidingTabLayout.TabColorizer {

    private final int mIndicatorColor;
    private final int mDividerColor;

    public ScTabColorizer(final Context context) {
        this.mIndicatorColor = ColorHelper.getColor(context, R.color.tabIndicator);
        this.mDividerColor = ColorHelper.getColor(context, R.color.tabDivider);
    }

    @Override
    public int getIndicatorColor(final int position) {
        return mIndicatorColor;
    }

    @Override
    public int getDividerColor(final int position) {
        return mDividerColor;
    }

}
