package com.hassan.curvedmotionexercise;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.attr.path;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    View fragmentView;
    int i = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        final ImageView imgView = (ImageView) fragmentView.findViewById(R.id.imageView);

        // Need a button as the view is not inflated yet (no return)
        Button btnRevealHide = (Button)fragmentView.findViewById(R.id.button);

        btnRevealHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float final_x = fragmentView.getWidth()-imgView.getBottom();
                float final_y = fragmentView.getHeight()-imgView.getBottom();
                Path path = new Path();

                if(i%2 != 0){
                    path.moveTo(0, 0);
                    path.quadTo(final_x/3, final_y, final_x, final_y);
                }else {
                    path.moveTo(final_x, final_y);
                    path.quadTo(final_x, final_y/3, 0, 0);
                }
                i++;

                ObjectAnimator mAnimator;
                mAnimator = ObjectAnimator.ofFloat(imgView, View.X, View.Y, path);
                mAnimator.setDuration(5000);
                mAnimator.start();
            }
        });

        return fragmentView;
    }
}
