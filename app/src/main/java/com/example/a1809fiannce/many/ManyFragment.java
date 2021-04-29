package com.example.a1809fiannce.many;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.LinView;
import com.example.framwork.call.FiannceARouter;

public class ManyFragment extends Fragment {
    private LinView us;
    private LinView tell;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_many, container, false);
        us = (LinView) view.findViewById(R.id.us);
        tell = view.findViewById(R.id.tell);
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceARouter.getFiannceARouter().getUserManager().OpenRegisterActivity(getActivity(),null);
            }
        });
        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+111111));
                startActivity(intent);

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:"+1111111));
//                startActivity(intent);
            }
        });
        return view;
    }

}