package id.pekma.pekmavii.FragmentNews;
/**
 * Created by Muhammad Taqi on 2/7/2018.
 */

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import id.pekma.pekmavii.R;

public class AdapterNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    public List<NewsData> data= Collections.emptyList();

    // create constructor to innitilize context and data sent from MainActivity
    AdapterNews(Context context, List<NewsData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }



    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_news, parent,false);
        return new MyHolderNews(view);
    }


    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView);
        final String description = data.get(position).getDesc();
        final String image = data.get(position).getNewsImage();
        final String title = data.get(position).getTitle();


        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderNews myHolderNews = (MyHolderNews) holder;
        NewsData current=data.get(position);
        myHolderNews.title.setText(current.title);
        Picasso.with(context)
                .load(current.getNewsImage())
                .fit()
                .centerCrop()
                .transform(new GradientTransformation())
                .into(myHolderNews.newsIv);
        Picasso.with(context)
                .load(R.drawable.ic_w)
                .fit()
                .into(myHolderNews.iconpekmasmallnews);

        ((MyHolderNews) holder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailNewsActivity(title,description,image);
            }
        });
    }

    private void openDetailNewsActivity(String title, String description, String image) {
        Intent i = new Intent(context,DetailActivityNews.class);
        //PACK DATA TO SEND
        i.putExtra("TITLE_KEY",title);
        i.putExtra("NAME_KEY",description);
        i.putExtra("IMAGE_KEY",image);

        //open activity
        context.startActivity(i);
    }


    private void runEnterAnimation(View view) {
        view.setTranslationY(Resources.getSystem().getDisplayMetrics().heightPixels);
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(500)
                .start();

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolderNews extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView homeNewsIv;
        TextView homeNewsDesc;
        ImageView newsIv,iconpekmasmallnews;
        TextView newsDesc;
        TextView title;
        ItemClickListener itemClickListener;
        // create constructor to get widget reference
        MyHolderNews(View itemView) {
            super(itemView);
            iconpekmasmallnews = itemView.findViewById(R.id.iconpekmasmallnews);
            title= itemView.findViewById(R.id.newsTitleTxt);
            newsIv= itemView.findViewById(R.id.ivNews);
            homeNewsIv = itemView.findViewById(R.id.ivHomeNews);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener=itemClickListener;
        }
    }



}
