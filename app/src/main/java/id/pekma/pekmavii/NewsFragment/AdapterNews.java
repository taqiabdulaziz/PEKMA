package id.pekma.pekmavii.NewsFragment;

/**
 * Created by Muhammad Taqi on 2/7/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import id.pekma.pekmavii.R;

public class AdapterNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<NewsData> data= Collections.emptyList();
    NewsData current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterNews(Context context, List<NewsData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.news_item, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        NewsData current=data.get(position);
        myHolder.newsTitle.setText(current.title);
        myHolder.newsDesc.setText(current.desc);
//        Glide.with (context).load("https://api.myjson.com/bins/1b5huh").into(myHolder.newsIv);
        Picasso.with(context).load(current.newsImage).into(myHolder.newsIv);


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }




    class MyHolder extends RecyclerView.ViewHolder{

        NewsData newsData;
        TextView newsTitle;
        ImageView newsIv;
        TextView newsDesc;
        TextView textType;
        TextView textPrice;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            newsTitle= (TextView) itemView.findViewById(R.id.newsTitleTxt);
            newsDesc= (TextView) itemView.findViewById(R.id.newsDescTxt);
            newsIv= (ImageView) itemView.findViewById(R.id.ivNews);


        }

    }

}
