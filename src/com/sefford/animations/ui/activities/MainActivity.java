package com.sefford.animations.ui.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import com.sefford.animations.R;
import com.sefford.animations.ui.fragments.*;

public class MainActivity extends Activity implements Handler.Callback {
    protected boolean downSampling = false;
    protected Handler handler = new Handler(this);
    private BaseFragment currentFragment;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addFragment(new Slide1Fragment(), "Slide1", 0, 0);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() != KeyEvent.KEYCODE_POWER) {
            if (!downSampling) {
                downSampling = true;
                handler.sendEmptyMessageDelayed(0xDEADBEEF, 1000);
                return delegateActionToFragment();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * Adds a fragment to the activity content viewgroup.
     *
     * @param frag  Fragment to add
     * @param title Title to set to the actionBar
     */
    public void addFragment(BaseFragment frag, String title, int animIn, int animOut) {
        if (frag != null) {
            currentFragment = frag;
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(animIn, animOut);
            ft.replace(R.id.fl_content, frag, title);
            ft.commit();
        }
    }

    public boolean delegateActionToFragment() {
        currentFragment.doNextStep();
        return true;
    }

    @Override
    public boolean handleMessage(Message msg) {
        downSampling = false;
        return true;
    }


}
