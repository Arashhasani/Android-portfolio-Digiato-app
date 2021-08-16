package ir.dotprint.digiato;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

public class ResultSearch extends AppCompatActivity {


    AppCompatImageView imgbackcat;
    AppCompatTextView txtcatText;
    AppBarLayout appbarresultcat;
    String catname;
    RecyclerView recyclerresultcat;
    NestedScrollView newstedresultcatnetworkerror,nestedresultcatnoitem,nestedresultcatshimmer;
    ArrayList<NewsItems> newsItems = new ArrayList<>();
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        findViews();
        startAc();

    }


    void findViews() {

        imgbackcat = findViewById(R.id.imgbackcat);
        txtcatText = findViewById(R.id.txtcatText);
        appbarresultcat = findViewById(R.id.appbarresultcat);
        recyclerresultcat = findViewById(R.id.recyclerresultcat);
        newstedresultcatnetworkerror = findViewById(R.id.newstedresultcatnetworkerror);
        nestedresultcatnoitem = findViewById(R.id.nestedresultcatnoitem);
        nestedresultcatshimmer = findViewById(R.id.nestedresultcatshimmer);




    }


    void startAc() {

        nestedresultcatshimmer.setVisibility(View.VISIBLE);
        recyclerresultcat.setVisibility(View.GONE);
        nestedresultcatnoitem.setVisibility(View.GONE);
        newstedresultcatnetworkerror.setVisibility(View.GONE);


        String catnum = getIntent().getStringExtra("catname");

        imgbackcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        appbarresultcat.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < 0) {
                    appbarresultcat.setElevation(16);
                } else if (verticalOffset == 0) {
                    appbarresultcat.setElevation(0);
                }
            }
        });

        switch (catnum) {
            case "1":
                txtcatText.setText("تکنولوژی");
                catname = "tech";
                break;
            case "2":
                txtcatText.setText("کنسول بازی");
                catname = "game";

                break;
            case "3":
                txtcatText.setText("بررسی ها");
                catname = "dgreview";

                break;
            case "4":
                txtcatText.setText("کسب و کار");
                catname = "business";

                break;
            case "5":
                txtcatText.setText("علم و دانش");
                catname = "science";

                break;
            case "6":
                txtcatText.setText("خودرو");
                catname = "car";

                break;
            default:
                txtcatText.setText("");
                break;
        }
        newsAdapter=new NewsAdapter(newsItems,ResultSearch.this);
        recyclerresultcat.setLayoutManager(new LinearLayoutManager(ResultSearch.this));
        recyclerresultcat.setAdapter(newsAdapter);

        getData(catname);


    }


    void getData(String catnamee) {

        String url="http://dotprint.ir/resume/digiato/catsearch.php";
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.isEmpty()){
                    nestedresultcatnoitem.setVisibility(View.VISIBLE);
                    newstedresultcatnetworkerror.setVisibility(View.GONE);
                    recyclerresultcat.setVisibility(View.GONE);
                    nestedresultcatshimmer.setVisibility(View.GONE);
                }else {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i =0;i<=jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            newsItems.add(new NewsItems(object.getString("id"),object.getString("title"),object.getString("shortdescription"),object.getString("fulldescription"),object.getString("smallpic"),object.getString("pic"),object.getString("date"),object.getString("authorname"),object.getString("authorpic"),object.getString("category")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    newsAdapter.notifyDataSetChanged();
                    recyclerresultcat.setVisibility(View.VISIBLE);
                    nestedresultcatshimmer.setVisibility(View.GONE);


                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                nestedresultcatshimmer.setVisibility(View.GONE);
                nestedresultcatnoitem.setVisibility(View.GONE);
                newstedresultcatnetworkerror.setVisibility(View.VISIBLE);
                recyclerresultcat.setVisibility(View.GONE);

            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,stringListener,errorListener){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("cat",catnamee);
                return hashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);




    }
}