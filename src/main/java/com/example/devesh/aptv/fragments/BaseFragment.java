package com.example.devesh.aptv.fragments;

import android.app.Activity;
import android.app.Fragment;

import com.example.devesh.aptv.events.ScopedBus;

/**
 * Created by Devesh on 10/13/2014.
 */
public class BaseFragment extends Fragment {


    final ScopedBus scopedBus = new ScopedBus();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        scopedBus.resumed();
        scopedBus.register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        scopedBus.paused();
    }

    public boolean onBackClicked() {
        return false;
    }


}
