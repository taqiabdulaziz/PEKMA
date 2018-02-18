package id.pekma.pekmavii.FragmentNews; /**
 * Created by Muhammad Taqi on 2/7/2018.
 */

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import id.pekma.pekmavii.R;

public class AdapterNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    public List<NewsData> data= Collections.emptyList();
    private List<NewsDataHome> dataHome= Collections.emptyList();

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

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderNews myHolderNews = (MyHolderNews) holder;
        NewsData current=data.get(position);
        myHolderNews.newsDesc.setText(current.desc);
        Picasso.with(context)
                .load(current.getNewsImage())
                .fit()
                .centerCrop()
                .transform(new GradientTransformation())
                .into(myHolderNews.newsIv);


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

    class MyHolderNews extends RecyclerView.ViewHolder{

        ImageView homeNewsIv;
        TextView homeNewsDesc;
        ImageView newsIv;
        TextView newsDesc;

        // create constructor to get widget reference
        MyHolderNews(View itemView) {
            super(itemView);
            newsDesc= itemView.findViewById(R.id.newsDescTxt);
            newsIv= itemView.findViewById(R.id.ivNews);
            homeNewsDesc = itemView.findViewById(R.id.homenewsdesctxt);
            homeNewsIv = itemView.findViewById(R.id.ivHomeNews);
        }
    }
}
