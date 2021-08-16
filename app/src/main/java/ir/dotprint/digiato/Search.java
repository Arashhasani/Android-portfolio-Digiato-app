package ir.dotprint.digiato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {


    AppCompatImageView ic_vec_clear, ic_vec_arrow_back;
    AppCompatEditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        findView();
        startAc();
    }


    void findView(){
        ic_vec_clear=findViewById(R.id.imgClear);
        ic_vec_arrow_back=findViewById(R.id.imgBack);
        edtSearch=findViewById(R.id.edtSearch);


    }

    void startAc(){
        edtSearch.setText("");
        ic_vec_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ic_vec_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("");
            }
        });


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtSearch.getText().toString().equals(""))
                {
                    edtSearch.setTextColor(getResources().getColor(R.color.Color_2));
                    ic_vec_clear.setVisibility(View.GONE);
                }
                else
                {
                    edtSearch.setTextColor(getResources().getColor(R.color.black));
                    ic_vec_clear.setVisibility(View.VISIBLE);
                }

            }
        });


        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH){
                    String texttosearch= String.valueOf(edtSearch.getText());
                    if (!texttosearch.equals("")){

                        startresultAc(texttosearch);
                    }else {
                        Toast.makeText(Search.this,"یک متن وارد کنید ...", Toast.LENGTH_SHORT).show();
                    }
                }


                return false;
            }
        });





    }


    void startresultAc(String texttosearch){
        startActivity(new Intent(Search.this,result.class).putExtra("texttosearch",texttosearch));


    }
}