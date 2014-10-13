package com.example.devesh.aptv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.devesh.aptv.R;

/**
 * Created by Devesh on 10/13/2014.
 */
public class DetailsFragment extends BaseFragment{
    private RelativeLayout mDetailsCard;
    private RelativeLayout mLayoutLists;
    LinearLayout mLinearLayout;
    static int start ;

    public static DetailsFragment newInstance(int y) {
        DetailsFragment fragment = new DetailsFragment();
        start =y;
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_detail, container, false);
        mDetailsCard =(RelativeLayout)rootView.findViewById(R.id.detailsCard);
        mLayoutLists =(RelativeLayout)rootView.findViewById(R.id.layout_lists);

        mLinearLayout =(LinearLayout)rootView.findViewById(R.id.llout);
        final Animation animation4 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_move_card);
        final TranslateAnimation translate = new TranslateAnimation(
                Animation.ABSOLUTE,0, Animation.ABSOLUTE,
                0, Animation.ABSOLUTE,start,
                Animation.ABSOLUTE,0);
        translate.setDuration(450);//speed of the animation
        translate.setFillEnabled(true);
        translate.setFillAfter(true);
        mLinearLayout.startAnimation(translate);
               // mLinearLayout.startAnimation(animation4);



        return rootView;
    }
}
