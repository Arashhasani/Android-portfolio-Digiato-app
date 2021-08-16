package ir.dotprint.digiato.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;

import ir.dotprint.digiato.R;

public class Frg_Favorite extends Fragment {

    AppBarLayout appbarfavorit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_frg__favorite,null);
        findViews(view);
        starFrg();
        return view;
    }

    void findViews(View view){
        appbarfavorit=view.findViewById(R.id.appbarfavorit);

    }

    void starFrg(){
        appbarfavorit.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset<0) {
                    appbarfavorit.setElevation(16);
                }else if (verticalOffset==0){
                    appbarfavorit.setElevation(0);
                }
            }
        });

    }
}