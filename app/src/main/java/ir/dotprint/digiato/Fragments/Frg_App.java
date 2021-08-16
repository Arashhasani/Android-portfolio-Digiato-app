package ir.dotprint.digiato.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.appbar.AppBarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ir.dotprint.digiato.Adapter.AppAdapter;
import ir.dotprint.digiato.AppController;
import ir.dotprint.digiato.Model.AppItems;
import ir.dotprint.digiato.R;

public class Frg_App extends Fragment {


    RecyclerView recyclerapp;
    AppCompatButton btnRetry;
    ArrayList<AppItems> appItems = new ArrayList<>();
    AppAdapter appAdapter;
//    AppBarLayout appbarapp;
    NestedScrollView nestedappnetworkerror,shimmerapp;
    SwipeRefreshLayout swipeapp;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_frg__app,null);
        findViews(view);
        startFrg();
        return view;
    }



    void findViews(View view){
        recyclerapp=view.findViewById(R.id.recyclerapp);
//        appbarapp=view.findViewById(R.id.appbarapp);
        nestedappnetworkerror=view.findViewById(R.id.nestedappnetworkerror);
        shimmerapp=view.findViewById(R.id.shimmerapp);
        swipeapp=view.findViewById(R.id.swipeapp);
        btnRetry=view.findViewById(R.id.btnRetry);

    }


    void startFrg(){
        recyclerapp.setVisibility(View.GONE);
        shimmerapp.setVisibility(View.VISIBLE);
        nestedappnetworkerror.setVisibility(View.GONE);
        recyclerapp.setLayoutManager(new LinearLayoutManager(getActivity()));
        appAdapter=new AppAdapter(appItems,getActivity());
        recyclerapp.setAdapter(appAdapter);
        getData();


//        recyclerapp.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                int recyclercompute=recyclerapp.computeVerticalScrollOffset();
//                if (recyclercompute==0){
//                    appbarapp.setElevation(0);
//                }
//
//            }
//        });
//        appbarapp.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                int recyclercompute=recyclerapp.computeVerticalScrollOffset();
//                if (verticalOffset<0){
//                    appBarLayout.setElevation(16);
//                }else if (verticalOffset==0 & recyclercompute==0){
//                    appBarLayout.setElevation(0);
//                }
//            }
//        });

        swipeapp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerapp.setVisibility(View.GONE);
                shimmerapp.setVisibility(View.VISIBLE);
                nestedappnetworkerror.setVisibility(View.GONE);
                appItems.clear();
                getData();
                swipeapp.setRefreshing(false);
            }
        });
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerapp.setVisibility(View.GONE);
                shimmerapp.setVisibility(View.VISIBLE);
                nestedappnetworkerror.setVisibility(View.GONE);
                appItems.clear();
                getData();
            }
        });


    }

    void getData(){

//        String url="http://192.168.1.189/digiato/selectapp.php";
        String url="http://dotprint.ir/resume/digiato/selectapp.php";

        Response.Listener<JSONArray> jsonArrayListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0 ; i<=response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        appItems.add(new AppItems(object.getString("id"),object.getString("title"),object.getString("shortdescription"),object.getString("fulldescription"),object.getString("smallpic"),object.getString("pic"),object.getString("date"),object.getString("authorname"),object.getString("authorpic")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                appAdapter.notifyDataSetChanged();
                recyclerapp.setVisibility(View.VISIBLE);
                shimmerapp.setVisibility(View.GONE);
                nestedappnetworkerror.setVisibility(View.GONE);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                nestedappnetworkerror.setVisibility(View.VISIBLE);
                shimmerapp.setVisibility(View.GONE);
                recyclerapp.setVisibility(View.GONE);


            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,jsonArrayListener,errorListener);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);


    }
}