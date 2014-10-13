package com.example.devesh.aptv;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.devesh.aptv.config.ApplicationSettings;
import com.example.devesh.aptv.events.DetailsFragmentEvent;
import com.example.devesh.aptv.events.ScopedBus;
import com.example.devesh.aptv.fragments.DetailsFragment;
import com.example.devesh.aptv.fragments.ExplorerFragment;
import com.example.devesh.aptv.ui.UiUtils;
import com.squareup.otto.Subscribe;


public class MainActivity extends Activity {
    private DetailsFragment mCardDetailFragment;
     final ScopedBus scopedBus =new ScopedBus();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ApplicationSettings.ENABLE_DEBUG_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .detectAll()
                    .penaltyLog()
                    .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectLeakedSqlLiteObjects()
//                    .detectLeakedClosableObjects()
//                    .penaltyLog()
//                    .penaltyDeath()
//                    .build());
        }

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ExplorerFragment fragment = ExplorerFragment.newInstance();
        UiUtils.pushFragment(fragment,getFragmentManager(),R.id.content_card_detail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        scopedBus.resumed();
        scopedBus.register(this);
    }

    @Override public void onPause() {
        super.onPause();
        scopedBus.paused();
    }

    @Subscribe
    public void onDetailsEventReached(DetailsFragmentEvent event){
        mCardDetailFragment = DetailsFragment.newInstance(event.y);
        UiUtils.pushFragment(mCardDetailFragment,getFragmentManager(),R.id.content_card_detail);

    }
}
