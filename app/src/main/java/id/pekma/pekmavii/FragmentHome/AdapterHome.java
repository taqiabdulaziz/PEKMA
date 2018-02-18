package id.pekma.pekmavii.FragmentHome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<HomeData> data= Collections.emptyList();
    HomeData current;
    int currentPos=0;

    public AdapterHome(Context context, List<HomeData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_home, parent,false);
        MyHolderHome holderhome = new MyHolderHome(view);
        return holderhome;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderhome, int position) {
        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderHome myHolderHome = (MyHolderHome) holderhome;
        HomeData currenthome = data.get(position);
        myHolderHome.tvplayerA.setText(currenthome.playerA);
        myHolderHome.tvplayerB.setText(currenthome.playerB);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyHolderHome extends RecyclerView.ViewHolder{

        HomeData homeData;
        TextView tvplayerA;
        TextView tvplayerB;

        // create constructor to get widget reference
        public MyHolderHome(View itemView) {
            super(itemView);
//            newsTitle= (TextView) itemView.findViewById(R.id.newsTitleTxt);
            tvplayerA= itemView.findViewById(R.id.txtPlayerA);
            tvplayerB= itemView.findViewById(R.id.txtPlayerB);
        }
    }
}
