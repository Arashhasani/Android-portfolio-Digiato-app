package ir.dotprint.digiato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.utils.Utils;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import ir.dotprint.digiato.Utils.Utility;

public class Detail extends AppCompatActivity {

    AppCompatImageView imgPic , imgBack,imgBookmark,imgShare;
    AppCompatTextView txtTitle,txtAuthorName,txtReleaseDate,txtdescription;
    AppBarLayout app_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);




        findViews();
        startAc();
    }


    void findViews(){
        imgPic=findViewById(R.id.imgPic);
        imgBack=findViewById(R.id.imgBack);
        imgBookmark=findViewById(R.id.imgBookmark);
        imgShare=findViewById(R.id.imgShare);
        txtTitle=findViewById(R.id.txtTitle);
        txtAuthorName=findViewById(R.id.txtAuthorName);
        txtReleaseDate=findViewById(R.id.txtReleaseDate);
        txtdescription=findViewById(R.id.txtdescription);
        app_bar=findViewById(R.id.app_bar);

    }


    void startAc(){
        String imgpic=getIntent().getStringExtra("imgPic");
        txtTitle.setText(getIntent().getStringExtra("title"));
        txtAuthorName.setText(getIntent().getStringExtra("txtAuthorName"));
        txtReleaseDate.setText(getIntent().getStringExtra("txtReleaseDate"));
        txtdescription.setText(getIntent().getStringExtra("txtdescription"));



        Picasso.get().load(imgpic).into(imgPic);
        Picasso.get().load(imgpic).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int color= Utility.getDominantColor(bitmap,bitmap.getWidth(),bitmap.getHeight()/3);
                if (color>150){
                    setToolbarItemColor(Color.BLACK);


                }else {
                    setToolbarItemColor(Color.WHITE);


                }

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void setToolbarItemColor(int color){
        imgBack.setColorFilter(color);
        imgBookmark.setColorFilter(color);
        imgShare.setColorFilter(color);
    }
}