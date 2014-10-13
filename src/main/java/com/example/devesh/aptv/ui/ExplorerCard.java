package com.example.devesh.aptv.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.devesh.aptv.R;
import com.example.devesh.aptv.events.BusProvider;
import com.example.devesh.aptv.events.DetailsFragmentEvent;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Devesh on 10/13/2014.
 */
public class ExplorerCard extends Card{
    private Context mContext;
    public ExplorerCard(Context context) {
        super(context, R.layout.explorer_card);
        mContext = context;
        init();
    }

    public void init() {
        CardThumbnail cardThumbnail = new CardThumbnail(mContext);
        addCardThumbnail(cardThumbnail);
        setSwipeable(false);
        setOnClickListener(new OnCardClickListener() {
                               @Override
                               public void onClick(Card card, View view) {
                                  int diff= UiUtils.getScreenLocationMinusStatusBar(view).top-UiUtils.dpToPx(mContext.getResources(),70);
                                   BusProvider.getInstance().post(new DetailsFragmentEvent(diff));
                                   Toast.makeText(mContext,"yahaha:"+(UiUtils.getScreenLocationMinusStatusBar(view).top), Toast.LENGTH_SHORT).show();

                               }
                           }
        );
    }

    @Override
    public void setupInnerViewElements(final ViewGroup parent, View view) {

    }
}
