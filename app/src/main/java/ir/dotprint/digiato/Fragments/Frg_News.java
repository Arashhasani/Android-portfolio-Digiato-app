package ir.dotprint.digiato.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.appbar.AppBarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ir.dotprint.digiato.Adapter.NewsAdapter;
import ir.dotprint.digiato.AppController;
import ir.dotprint.digiato.MainActivity;
import ir.dotprint.digiato.Model.NewsItems;
import ir.dotprint.digiato.R;
import ir.dotprint.digiato.Search;

public class Frg_News extends Fragment {


    Toolbar toolbarnews;
    AppCompatImageView imgSearch;
    RecyclerView recyclernews;
    ArrayList<NewsItems> newsItems = new ArrayList<>();
    NewsAdapter newsAdapter;
    NestedScrollView newstednews,nestednewserror;
    AppCompatButton btnRetry;
    SwipeRefreshLayout swipenews;
    AppBarLayout appbarnews;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_frg__news,null);

        findViews(view);
        startFrg();


        return view;

    }

    void findViews(View view){
        toolbarnews=view.findViewById(R.id.toolbarnews);
        imgSearch=view.findViewById(R.id.imgSearch);
        recyclernews=view.findViewById(R.id.recyclernews);
        newstednews=view.findViewById(R.id.newstednews);
        nestednewserror=view.findViewById(R.id.nestednewserror);
        swipenews=view.findViewById(R.id.swipenews);
        btnRetry=view.findViewById(R.id.btnRetry);
        appbarnews=view.findViewById(R.id.appbarnews);

    }
    void startFrg(){
        newstednews.setVisibility(View.VISIBLE);
        recyclernews.setVisibility(View.GONE);
        nestednewserror.setVisibility(View.GONE);
        recyclernews.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsAdapter=new NewsAdapter(newsItems,getActivity());
        recyclernews.setAdapter(newsAdapter);
        getData();



        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(getActivity(), Search.class));
            }
        });


        swipenews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclernews.setVisibility(View.GONE);
                nestednewserror.setVisibility(View.GONE);
                newstednews.setVisibility(View.VISIBLE);
                newsItems.clear();
                swipenews.setRefreshing(false);
                getData();

            }
        });
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclernews.setVisibility(View.GONE);
                nestednewserror.setVisibility(View.GONE);
                newstednews.setVisibility(View.VISIBLE);
                newsItems.clear();
                getData();
            }
        });



        recyclernews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int recyclerviewscroll=recyclernews.computeVerticalScrollOffset();

                if (recyclerviewscroll==0){
                    appbarnews.setElevation(0);
                }
            }
        });



        appbarnews.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                int recyclerviewscroll=recyclernews.computeVerticalScrollOffset();
                if (verticalOffset<0 ){
                    appBarLayout.setElevation(16);
                }else if (verticalOffset==0 & recyclerviewscroll==0){
                    appBarLayout.setElevation(0);
                }



            }
        });


    }



    void getData(){


//        String url="http://192.168.1.189/digiato/selectall.php";
        String url="http://dotprint.ir/resume/digiato/selectall.php";

        Response.Listener<JSONArray> jsonArrayListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i<=response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        newsItems.add(new NewsItems(object.getString("id"),object.getString("title"),object.getString("shortdescription"),object.getString("fulldescription"),object.getString("smallpic"),object.getString("pic"),object.getString("date"),object.getString("authorname"),object.getString("authorpic"),object.getString("category")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    newstednews.setVisibility(View.GONE);
                    recyclernews.setVisibility(View.VISIBLE);
                    newsAdapter.notifyDataSetChanged();
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                newstednews.setVisibility(View.GONE);
                swipenews.setVisibility(View.GONE);
                recyclernews.setVisibility(View.GONE);
                nestednewserror.setVisibility(View.VISIBLE);

            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,jsonArrayListener,errorListener);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);


    }
}