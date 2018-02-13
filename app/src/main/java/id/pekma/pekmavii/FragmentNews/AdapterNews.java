package id.pekma.pekmavii.FragmentNews;

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

import com.squareup.picasso.Picasso;

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
        View view=inflater.inflate(R.layout.item_news, parent,false);
        MyHolderNews holder=new MyHolderNews(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderNews myHolderNews = (MyHolderNews) holder;
        NewsData current=data.get(position);
//        myHolderNews.newsTitle.setText(current.title);
        myHolderNews.newsDesc.setText(current.desc);
//        Glide.with (context).load("https://api.myjson.com/bins/1b5huh").into(myHolderNews.newsIv);
        Picasso.with(context)
                .load(current.getNewsImage())
                .fit()
                .centerCrop()
                .transform(new GradientTransformation())
                .into(myHolderNews.newsIv);


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }




    class MyHolderNews extends RecyclerView.ViewHolder{

        NewsData newsData;
        TextView newsTitle;
        ImageView newsIv;
        TextView newsDesc;
        TextView textType;
        TextView textPrice;

        // create constructor to get widget reference
        public MyHolderNews(View itemView) {
            super(itemView);
            newsDesc= (TextView) itemView.findViewById(R.id.newsDescTxt);
            newsIv= (ImageView) itemView.findViewById(R.id.ivNews);
        }
    }
}
