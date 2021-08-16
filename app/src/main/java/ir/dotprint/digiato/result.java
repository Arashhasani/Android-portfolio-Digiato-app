package ir.dotprint.digiato;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.appbar.AppBarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ir.dotprint.digiato.Adapter.NewsAdapter;
import ir.dotprint.digiato.Model.NewsItems;

public class result extends AppCompatActivity {

    AppBarLayout appbarresult;
    AppCompatImageView imgback;
    AppCompatTextView txtSearchedText;
    RecyclerView recyclerresult;
    NestedScrollView shimmerresult , networkerrorresult,noitemresult;
    SwipeRefreshLayout swiperesult;
    ArrayList<NewsItems> newsItems = new ArrayList<>();
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        findViews();
        startAc();

    }



    void findViews(){

        appbarresult=findViewById(R.id.appbarresult);
        imgback=findViewById(R.id.imgBack);
        txtSearchedText=findViewById(R.id.txtSearchedText);
        recyclerresult=findViewById(R.id.recuclerresult);
        shimmerresult=findViewById(R.id.shimmerresult);
        networkerrorresult=findViewById(R.id.networkerrorresult);
        swiperesult=findViewById(R.id.siperesult);
        noitemresult=findViewById(R.id.noitemresult);

    }




    void startAc(){

        shimmerresult.setVisibility(View.VISIBLE);
        networkerrorresult.setVisibility(View.GONE);
        recyclerresult.setVisibility(View.GONE);
        noitemresult.setVisibility(View.GONE);

        String texttosearch=getIntent().getStringExtra("texttosearch");
        appbarresult.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset<0){
                    appbarresult.setElevation(16);
                }
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtSearchedText.setText(texttosearch);

        appbarresult.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset<0){
                    appBarLayout.setElevation(16);
                }else if (verticalOffset==0){
                    appBarLayout.setElevation(0);
                }
            }
        });


        recyclerresult.setLayoutManager(new LinearLayoutManager(result.this));
        newsAdapter= new NewsAdapter(newsItems,result.this);
        recyclerresult.setAdapter(newsAdapter);
        getdata(texttosearch);



        swiperesult.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerresult.setVisibility(View.GONE);
                shimmerresult.setVisibility(View.VISIBLE);
                newsItems.clear();
                getdata(texttosearch);
                swiperesult.setRefreshing(false);
            }
        });


    }



    void getdata(String texttosearch){
//        String url="http://192.168.1.189/digiato/search.php";
        String url="http://dotprint.ir/resume/digiato/search.php";

        Response.Listener<String>  stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")){
                    shimmerresult.setVisibility(View.GONE);
                    noitemresult.setVisibility(View.VISIBLE);

                }else {
                    shimmerresult.setVisibility(View.GONE);
                    recyclerresult.setVisibility(View.VISIBLE);

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0 ; i<=jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            newsItems.add(new NewsItems(object.getString("id"),object.getString("title"),object.getString("shortdescription"),object.getString("fulldescription"),object.getString("smallpic"),object.getString("pic"),object.getString("date"),object.getString("authorname"),object.getString("authorpic"),object.getString("category")));

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                newsAdapter.notifyDataSetChanged();

            }
        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                shimmerresult.setVisibility(View.GONE);
                recyclerresult.setVisibility(View.GONE);
                networkerrorresult.setVisibility(View.VISIBLE);
            }
        };


        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,stringListener,errorListener){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("search",texttosearch);
                return hashMap;
            }
        };


        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}