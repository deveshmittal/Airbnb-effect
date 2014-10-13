package com.example.devesh.aptv.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devesh.aptv.R;
import com.example.devesh.aptv.ui.ExplorerCard;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Devesh on 10/13/2014.
 */
public class ExplorerFragment extends BaseFragment{
    private View rootView = null;
    private ArrayList<Card> cards;
    CardListView listView;
    CardArrayAdapter mCardArrayAdapter;

    public static ExplorerFragment newInstance() {
        ExplorerFragment fragment = new ExplorerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_explorer,container,false);
        cards = new ArrayList<Card>();

        for (int i=0;i<10;i++) {

            ExplorerCard card = new ExplorerCard(getActivity());
            card.init();
            cards.add(card);
        }

        listView = (CardListView) rootView.findViewById(R.id.mylist);
        mCardArrayAdapter= new CustomAdapter(getActivity(),cards);
        listView.setAdapter(mCardArrayAdapter);

        return  rootView;
    }
    class CustomAdapter extends CardArrayAdapter {

        public CustomAdapter(Context context, List<Card> cards) {
            super(context, cards);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return super.getView(position, convertView, parent);
        }
    }

}

