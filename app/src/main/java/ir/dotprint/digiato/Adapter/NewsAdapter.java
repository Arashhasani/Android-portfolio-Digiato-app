package ir.dotprint.digiato.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.dotprint.digiato.Detail;
import ir.dotprint.digiato.Model.NewsItems;
import ir.dotprint.digiato.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    ArrayList<NewsItems> newsItems;
    Context context;

    public NewsAdapter(ArrayList<NewsItems> newsItems, Context context) {
        this.newsItems = newsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,null);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItems items = newsItems.get(position);
        Picasso.get().load(items.getSmallpic()).into(holder.imgPostImage);
        Picasso.get().load(items.getAuthorpic()).into(holder.imgAvatar);
        holder.txtTitle.setText(items.getShortdescription());
        holder.txtDate.setText(items.getDate());
        holder.txtAuthorName.setText(items.getAuthorname());

        holder.crdContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show();
                String share_element=context.getString(R.string.transitionDetailActivityImage);
                final Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, ((NewsViewHolder) holder).imgPostImage, share_element).toBundle();
                context.startActivity(new Intent(context, Detail.class).putExtra("imgPic",items.getPic()).putExtra("title",items.getTitle()).putExtra("txtAuthorName",items.getAuthorname()).putExtra("txtReleaseDate",items.getDate()).putExtra("txtdescription",items.getFulldescription()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imgPostImage , imgMore;
                AppCompatTextView txtAuthorName ,txtDate ,txtTitle;
                        CircleImageView imgAvatar;
                                CardView crdContainer;



        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPostImage=itemView.findViewById(R.id.imgPostImage);
            imgMore=itemView.findViewById(R.id.imgMore);
            txtAuthorName=itemView.findViewById(R.id.txtAuthorName);
            txtDate=itemView.findViewById(R.id.txtReleaseDate);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            imgAvatar=itemView.findViewById(R.id.imgAvatar);
            crdContainer=itemView.findViewById(R.id.crdContainer);
        }
    }
}
