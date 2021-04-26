package com.example.designed.event;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.designed.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment implements View.OnTouchListener{
    private SlideView slideView;




    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_event, container, false);
        slideView = (SlideView) inflate.findViewById(R.id.slideView);

        slideView.setOnTouchListener(this);


        return inflate;
    }




    int lastX,lastY;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) motionEvent.getX();
                lastY = (int) motionEvent.getY();
                slideView.getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:

                if ((lastX<50 || lastX>500) && (Math.abs(motionEvent.getRawY()-lastY)+20<Math.abs(motionEvent.getRawX()-lastX))){
                    slideView.getParent().requestDisallowInterceptTouchEvent(false);
                }else {
                    slideView.getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;
        }

        return true;
    }


}
