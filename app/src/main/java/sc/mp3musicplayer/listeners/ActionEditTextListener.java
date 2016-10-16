package sc.mp3musicplayer.listeners;

import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.wang.avi.AVLoadingIndicatorView;
import sc.mp3musicplayer.mvp.search.presenter.SearchPresenter;
import sc.mp3musicplayer.utilities.ViewHelper;

/**
 * ActionEditTextListener is triggered when the user presses either the ENTER or DONE keys.
 */
public class ActionEditTextListener implements OnEditorActionListener {

    private SearchPresenter mPresenter;
    private AVLoadingIndicatorView mBallSpinLoader;

    public ActionEditTextListener(@NonNull final SearchPresenter mPresenter, @NonNull final AVLoadingIndicatorView mBallSpinLoader){
        this.mPresenter = Preconditions.checkNotNull(mPresenter,"mPresenter cannot be null");
        this.mBallSpinLoader = Preconditions.checkNotNull(mBallSpinLoader,"mBallSpinLoader cannot be null");
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                || (actionId == EditorInfo.IME_ACTION_DONE)) {

            ViewHelper.showOrMakeViewGone(Optional.fromNullable(mBallSpinLoader), true);
            this.mPresenter.searchTrack( ((EditText) v).getText().toString() );

        }
        return false; // False, Hides the keyboard.
    }
}
