package ir.dotprint.digiato.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;

import ir.dotprint.digiato.R;
import ir.dotprint.digiato.ResultSearch;

public class Frg_Cat extends Fragment {


    AppBarLayout appbarcat;
    AppCompatImageView cattech,catgame,catreview,catbusiness,catscience,catcar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_frg__cat,null);
        findviews(view);
        starFrag();
        return view;
    }


    void findviews(View view){
        appbarcat= view.findViewById(R.id.appbarcat);
        cattech= view.findViewById(R.id.cattech);
        catgame= view.findViewById(R.id.catgame);
        catreview= view.findViewById(R.id.catreview);
        catbusiness= view.findViewById(R.id.catbusiness);
        catscience= view.findViewById(R.id.catscience);
        catcar= view.findViewById(R.id.catcar);

    }



    void starFrag(){
        appbarcat.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset<0){
                    appBarLayout.setElevation(16);
                }else if (verticalOffset==0){
                    appBarLayout.setElevation(0);
                }
            }
        });

        cattech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(getActivity(), ResultSearch.class).putExtra("catname","1"));
            }
        });
        catgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ResultSearch.class).putExtra("catname","2"));

            }
        });
        catreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ResultSearch.class).putExtra("catname","3"));

            }
        });
        catbusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ResultSearch.class).putExtra("catname","4"));

            }
        });
        catscience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ResultSearch.class).putExtra("catname","5"));

            }
        });
        catcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ResultSearch.class).putExtra("catname","6"));

            }
        });
    }
}