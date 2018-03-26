package id.pekma.pekmavii.FragmentNews.InstaFrag;
/**
 * Created by Muhammad Taqi on 2/7/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import id.pekma.pekmavii.FragmentNews.DetailActivityNews;
import id.pekma.pekmavii.FragmentNews.GradientTransformation;
import id.pekma.pekmavii.FragmentNews.ItemClickListener;
import id.pekma.pekmavii.FragmentNews.NewsData;
import id.pekma.pekmavii.R;

public class AdapterInsta extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    public List<InstaData> data= Collections.emptyList();

    // create constructor to innitilize context and data sent from MainActivity
    AdapterInsta(Context context, List<InstaData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }



    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_insta, parent,false);
        return new MyHolderNews(view);
    }


    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView);
        final String image = data.get(position).getImage();
        final String title = data.get(position).getDescription();


        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderNews myHolderNews = (MyHolderNews) holder;
        InstaData current = data.get(position);
        myHolderNews.title.setText(current.getDescription());

        Picasso.with(context)
                .load(current.getImage())
                .fit()
                .centerCrop()
//                .transform(new GradientTransformation())
                .into(myHolderNews.instaIv);

        Picasso.with(context)
                .load(R.drawable.instagram_icon)
                .fit()
                .into(myHolderNews.instaIcon);
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

        ImageView instaIv,instaIcon;
        TextView title;
        ItemClickListener itemClickListener;
        // create constructor to get widget reference
        MyHolderNews(View itemView) {
            super(itemView);
            instaIcon = itemView.findViewById(R.id.iconInsta);
            title= itemView.findViewById(R.id.descInsta);
            instaIv = itemView.findViewById(R.id.ivInsta);

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
