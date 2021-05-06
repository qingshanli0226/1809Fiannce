package com.example.fiannce.fragment.morefragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fiannce.R;
import com.example.fiannce.fragment.morefragment.register.RegisterActivity;
import com.example.framework.FiannceARouter;

public class MoreFragment extends Fragment {

    private MoreView reg;
    private MoreView tell;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_more, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA},101);
        }

        reg = (MoreView) inflate.findViewById(R.id.reg);
        tell = (MoreView) inflate.findViewById(R.id.tell);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiannceARouter.getFiannceARouter().getUserManager().OpenRegisterActivity(getActivity(),null);
            }
        });

        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+123456));
                startActivity(intent);
            }
        });

        return inflate;
    }
}