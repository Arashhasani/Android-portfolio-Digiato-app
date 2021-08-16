package ir.dotprint.digiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ir.dotprint.digiato.Fragments.Frg_App;
import ir.dotprint.digiato.Fragments.Frg_Cat;
import ir.dotprint.digiato.Fragments.Frg_Favorite;
import ir.dotprint.digiato.Fragments.Frg_News;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    AlertDialog.Builder builder;
    AppCompatButton btnyes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        findViews();
        starAc();
    }



    void findViews(){

        bottomNavigationView=findViewById(R.id.btnnavigation);
        frameLayout=findViewById(R.id.frmcontainer);
    }

    void starAc(){
        locadFragment(new Frg_News());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.navigationnews:locadFragment(new Frg_News());break;
                    case R.id.navigationcategory:locadFragment(new Frg_Cat());break;
                    case R.id.navigationapp:locadFragment(new Frg_App());break;
                    case R.id.navigationfavorite:locadFragment(new Frg_Favorite());break;
                }


                return true;
            }
        });

    }


    void locadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frmcontainer,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.alertdialog,null);
//        btnyes=view.findViewById(R.id.btnyes);
        builder=new AlertDialog.Builder(MainActivity.this);

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("خیر",null);

        AlertDialog alertDialog = builder.create();
        builder.setCancelable(true);
        builder.setView(view);
        builder.create().show();
    }
}