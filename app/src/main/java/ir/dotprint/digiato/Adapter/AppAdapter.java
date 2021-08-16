package ir.dotprint.digiato.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.dotprint.digiato.Detail;
import ir.dotprint.digiato.Model.AppItems;
import ir.dotprint.digiato.R;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder>{


    ArrayList<AppItems> appItems;
    Context context;

    public AppAdapter(ArrayList<AppItems> appItems, Context context) {
        this.appItems = appItems;
        this.context = context;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app,null);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        AppItems items = appItems.get(position);
        Picasso.get().load(items.getPic()).into(holder.imgAppImage);
        holder.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"More ...",Toast.LENGTH_SHORT).show();
            }
        });
        holder.txtTitle.setText(items.getTitle());
        holder.txtReleaseDate.setText(items.getDate());
        holder.txtAuthorName.setText(items.getAuthorname());
        holder.rtlapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String share_element=context.getString(R.string.transitionDetailActivityImage);
                final Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, ((AppViewHolder) holder).imgAppImage, share_element).toBundle();
                context.startActivity(new Intent(context, Detail.class).putExtra("imgPic",items.getPic()).putExtra("title",items.getTitle()).putExtra("txtAuthorName",items.getAuthorname()).putExtra("txtReleaseDate",items.getDate()).putExtra("txtdescription",items.getFulldescription()));

            }
        });


    }

    @Override
    public int getItemCount() {
        return appItems.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rtlapp;
        AppCompatTextView txtTitle,txtAuthorName,txtReleaseDate;
        AppCompatImageView imgAppImage,imgMore;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            rtlapp=itemView.findViewById(R.id.rtlapp);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtAuthorName=itemView.findViewById(R.id.txtAuthorName);
            txtReleaseDate=itemView.findViewById(R.id.txtReleaseDate);
            imgAppImage=itemView.findViewById(R.id.imgAppImage);
            imgMore=itemView.findViewById(R.id.imgMore);
        }
    }

}
