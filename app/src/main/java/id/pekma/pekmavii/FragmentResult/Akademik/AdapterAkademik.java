package id.pekma.pekmavii.FragmentResult.Akademik;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.pekma.pekmavii.FragmentHome.DetailActivityHomeMatch;
import id.pekma.pekmavii.FragmentHome.HomeData;
import id.pekma.pekmavii.FragmentNews.ItemClickListener;
import id.pekma.pekmavii.FragmentResult.ResultFragment;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class AdapterAkademik extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ResultFragment.SendCabol {

    List<HomeData> data = Collections.emptyList();
    int currentPos = 0;
    int cabolPos;
    AdapterView.OnItemClickListener itemClickListener;
    private Context context;
    private LayoutInflater inflater;

    public AdapterAkademik(Context context, List<HomeData> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_akademik, parent, false);
        MyHolderHome holderhome = new MyHolderHome(view);

        final int cabolData = data.get(0).getCabolData();
        cabolPos = cabolData;

        return holderhome;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderhome, int position) {


        //DATA UNTUK KE DETAIL ACTIVITY HOME
        final String playerA = data.get(position).getPlayerA();
        final String playerB = data.get(position).getPlayerB();
        final String jurA = data.get(position).getJurA();
        final String jurB = data.get(position).getJurB();
        final int idevent = data.get(position).getIdevent();
        final String msdate = data.get(position).getMsDate();
        final String mstime = data.get(position).getMstime();


        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderHome myHolderHome = (MyHolderHome) holderhome;
        HomeData currenthome = data.get(position);
        HomeData currenthome2 = data.get(0);
        System.out.println(cabolPos + "cabolPos");
        System.out.println(idevent + "idevent");


        if (cabolPos == idevent) {

            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            myHolderHome.namaPeserta.setText(currenthome.namaPeserta);
            myHolderHome.namaTema.setText(currenthome.namaTema);
            Picasso.with(context)
                    .load(R.drawable.person_placeholder)
                    .fit()
                    .into(((MyHolderHome) holderhome).placeholderIv);

        } else {

            holderhome.itemView.setVisibility(View.GONE);
            holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    private void openDetailActivityHome(String playerA, String playerB, String jurA, String jurB) {
        Intent i = new Intent(context, DetailActivityHomeMatch.class);

        //PACK DATA TO SEND
//        i.putExtra("NAME_KEY_A",playerA);
//        i.putExtra("NAME_KEY_B",playerB);
//        i.putExtra("NAME_KEY_A_JUR",jurA);
//        i.putExtra("NAME_KEY_B_JUR",jurB);

        //open activity
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void sendCabolData(int message) {

    }

    public void parseCabolData() {

    }

    class MyHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener {

        HomeData homeData;
        TextView namaPeserta, namaTema;
        ItemClickListener itemClickListener;
        CircleImageView placeholderIv;


        // create constructor to get widget reference
        public MyHolderHome(View itemView) {
            super(itemView);

            namaPeserta = itemView.findViewById(R.id.namaPeserta);
            namaTema = itemView.findViewById(R.id.namaTema);
            placeholderIv = itemView.findViewById(R.id.placeholderIv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


    }
}
