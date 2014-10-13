package com.example.devesh.aptv.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.devesh.aptv.R;

import java.util.Random;

/**
 * Created by Devesh on 10/14/2014.
 */
public class UiUtils {
    public static int getScreenOrientation(Context context){
        Display getOrient = ((Activity) context).getWindowManager().getDefaultDisplay();
        if(getOrient.getWidth() < getOrient.getHeight()){
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

    }
/*

    public static final ButterKnife.Action<View> INVISIBLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setVisibility(View.INVISIBLE);
        }
    };

    public static final ButterKnife.Action<View> GONE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setVisibility(View.GONE);
        }
    };

    public static final ButterKnife.Action<View> VISIBLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setVisibility(View.VISIBLE);
        }
    };
*/

    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics());
    }


    public static void showFeedback(View v) {
        if (v == null) {
            return;
        }
        v.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundColor(Color.parseColor("#54B5E9"));
                        break;
                    default:
                        v.setBackgroundColor(Color.TRANSPARENT);
                        break;
                }
                return false;
            }
        });
    }

    public static void showFeedbackOnSame(View v) {
        if (v == null) {
            return;
        }
        v.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundColor(Color.parseColor("#54B5E9"));
                        break;
                    default:
                        v.setBackgroundColor(Color.TRANSPARENT);
                        break;
                }
                return false;
            }
        });
    }
    public static Rect getScreenLocationMinusStatusBar(View view)
    {
        Rect rect = new Rect();
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        int i = getStatusbarHeight(view);
     //  rect.set(ai[0], ai[1] - i, ai[0] + view.getWidth(), (ai[1] + view.getHeight()) - i);
        rect.set(ai[0], ai[1] , ai[0] + view.getWidth(), (ai[1] + view.getHeight()) );

        return rect;
    }
    public static int getStatusbarHeight(View view)
    {
        Rect rect = new Rect();
        ((Activity)view.getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }


    public static void showToast(CharSequence aMsg, Context mContext) {

        Toast.makeText(mContext,
                aMsg,
                Toast.LENGTH_LONG).show();
    }


    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 48;
        }
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (result > 100) {
            result = 48;
        }
        return result;
    }

    public static void animate(float fromX, float toX, final View v,
                               final boolean showlist, int animationType, Context mContext) {
        if (v == null) {
            return;
        }
        AnimatorSet set = new AnimatorSet();
        if (animationType == 1) {
            set.play(ObjectAnimator.ofFloat(v, View.TRANSLATION_X, fromX, toX));
        } else {
            set.play(ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, fromX, toX));
        }
        set.setDuration(mContext.getResources().getInteger(
                android.R.integer.config_longAnimTime));
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub

            }
        });
        set.start();
    }


    public static void FitToRound(Context mContext, ImageView View, Bitmap bm) {
        try {
            Bitmap bitmap = bm;
            Bitmap bitmapRounded = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Canvas canvas = new Canvas(bitmapRounded);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            canvas.drawRoundRect((new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight())), 10, 10, paint);
            View.setImageBitmap(bitmapRounded);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

 /*   public static void prepareDisplayinfo(Activity activity) {
        try {


            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            AppApplication.getApplicationConfig().screenHeight = dm.heightPixels;
            AppApplication.getApplicationConfig().screenWidth = dm.widthPixels;
            AppApplication.getApplicationConfig().type = findDpi(dm.densityDpi);


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
*//*
    public static String findDpi(int density) {
        String value = ApplicationConfig.MDPI;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                value = ApplicationConfig.LDPI;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                value = ApplicationConfig.MDPI;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                value = ApplicationConfig.HDPI;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                value = ApplicationConfig.XHDPI;
                break;
            default:
                value = ApplicationConfig.XHDPI;
                break;
        }
        return value;
    }*/
    public static void removeFragment(Fragment fragment,FragmentManager fragmentManager)
    {
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
        } catch (Throwable e) {
            e.printStackTrace();

        }
    }

    public static void overlayFragment(Fragment fragment, FragmentManager fragmentManager, int layout) {
        if (fragment == null)
            return;
        try {

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(layout, fragment);
            transaction.commit();

        } catch (Throwable e) {
            e.printStackTrace();

        }
    }

    public static void pushFragment(Fragment fragment, FragmentManager fragmentManager, int layout) {
        if (fragment == null)
            return;
        try {

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_in_right,
                    R.anim.slide_out_right,0,0);
            transaction.add(layout, fragment);
            transaction.addToBackStack(null);

            transaction.commit();

        } catch (Throwable e) {
            e.printStackTrace();

        }
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
    public static int getActionBarHeight(Context context) {
        int actionBarHeight=0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
        }else{
            actionBarHeight=((int)dipToPixels(context,56));
        }
        return actionBarHeight;

    }
/*
    public static void loadImage(ImageView imageView, String uri) {

        if (imageView == null || TextUtils.isEmpty(uri)) {
            return;
        }

        if (imageView instanceof NetworkImageView) {
            uri = uri.replace("128x96", "myplex");
            ((NetworkImageView) imageView).setImageUrl(uri,
                    MyVolley.getImageLoader());
        }

    }*/

   /* public static void setViewGroupTypeFace(Context context, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View viewChild = viewGroup.getChildAt(i);
            if (viewChild instanceof ViewGroup) {
                setViewGroupTypeFace(context, (ViewGroup) viewChild);
            } else if (viewChild instanceof TextView) {
                FontUtil.setBoldFont((TextView) viewChild);
            }
        }
    }*/

    public static int getRandomColor() {
        Random rnd = new Random();
        int Low = 100;
        int High = 196;
        int color = Color.argb(255, rnd.nextInt(High - Low) + Low, rnd.nextInt(High - Low) + Low, rnd.nextInt(High - Low) + Low);
        return color;
    }

    public static void crossfade(final View from , final View to) {

        int mShortAnimationDuration = 1*1000;
        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        to.setAlpha(0f);
        to.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        to.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        from.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        from.setVisibility(View.GONE);
                    }
                });
    }
    public static Fragment getCurrentFragment(FragmentManager fragmentManager){
        Fragment fragment = new Fragment();
        String fragmentTag=null;
        if(fragmentManager.getBackStackEntryCount()>1 ) {
            fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        }
        if(fragmentTag!=null) {
            fragment = fragmentManager.findFragmentByTag(fragmentTag);
        }
        return fragment;

    }

    public static void animateVisible(View view){
        ValueAnimator fadeAnim2 = ObjectAnimator.ofFloat(view,
                "alpha", 0f, 1f);
        fadeAnim2.setDuration(800);
        fadeAnim2.start();
    }

    public static void animateInVisible(final View view){
        ValueAnimator fadeAnim2 = ObjectAnimator.ofFloat(view,
                "alpha", 1f, 0);
        fadeAnim2.setDuration(800);
        fadeAnim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        fadeAnim2.start();
    }
    public static String toSentenceCase(String inputString){
        String result = "";
        if (inputString.length() == 0) {
            return result;
        }
        char firstChar = inputString.charAt(0);
        char firstCharToUpperCase = Character.toUpperCase(firstChar);
        result = result + firstCharToUpperCase;
        boolean terminalCharacterEncountered = false;
        char[] terminalCharacters = {'.', '?', '!'};
        for (int i = 1; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (terminalCharacterEncountered) {
                if (currentChar == ' ') {
                    result = result + currentChar;
                } else {
                    char currentCharToUpperCase = Character.toUpperCase(currentChar);
                    result = result + currentCharToUpperCase;
                    terminalCharacterEncountered = false;
                }
            } else {
                char currentCharToLowerCase = Character.toLowerCase(currentChar);
                result = result + currentCharToLowerCase;
            }
            for (int j = 0; j < terminalCharacters.length; j++) {
                if (currentChar == terminalCharacters[j]) {
                    terminalCharacterEncountered = true;
                    break;
                }
            }
        }
        return result;    }

    public static Drawable getDynamicCircleDrawable(String color){
        float radius = 5.0f;

        float[] m_arrfTopHalfOuterRadii = new float[] {radius, radius, radius, radius, 0, 0, 0, 0};
        float[] m_arrfBottomHalfOuterRadii = new float[] {0, 0, 0, 0, radius, radius, radius, radius};
        int m_nTopColor;
        if(TextUtils.isEmpty(color)){
            m_nTopColor = Color.CYAN;
        }else {
            m_nTopColor = Color.parseColor(color);
        }

        OvalShape ovalShape = new OvalShape();
        ShapeDrawable ovalShapeDrawable = new ShapeDrawable(ovalShape);
        ovalShapeDrawable.getPaint().setColor(m_nTopColor);
        Drawable[] drawarray = {ovalShapeDrawable};

        LayerDrawable layerdrawable = new LayerDrawable(drawarray);
        //   layerdrawable.setLayerInset(0,0,m_nCellHeight,0,0);
          /*  RoundRectShape top_round_rect = new RoundRectShape(m_arrfTopHalfOuterRadii, null, null);
            ShapeDrawable top_shape_drawable = new ShapeDrawable(top_round_rect);
            top_shape_drawable.getPaint().setColor(m_nTopColor);

            RoundRectShape bottom_round_rect = new RoundRectShape(m_arrfBottomHalfOuterRadii, null, null);
            ShapeDrawable bottom_shape_drawable = new ShapeDrawable(bottom_round_rect);
            bottom_shape_drawable.getPaint().setColor(m_nBottomColor);


            Drawable[] drawarray = {top_shape_drawable, bottom_shape_drawable};
            LayerDrawable layerdrawable = new LayerDrawable(drawarray);

            int _nHalfOfCellHeight = m_nCellHeight/2;
            layerdrawable.setLayerInset(0, 0, 0, 0, _nHalfOfCellHeight); //top half
            layerdrawable.setLayerInset(1, 0, _nHalfOfCellHeight, 0, 0); //bottom half*/

        //  return layerdrawable;
        return layerdrawable;

    }

    public static void disableViewOnClick(final View view) {
        if (view == null) return;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 3000);
    }
}
