package id.pekma.pekmavii.FragmentResult.Olahraga;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.pekma.pekmavii.FragmentHome.DetailActivityHomeMatch;
import id.pekma.pekmavii.FragmentHome.HomeData;
import id.pekma.pekmavii.FragmentNews.ItemClickListener;
import id.pekma.pekmavii.FragmentResult.CabolDataGetSet;
import id.pekma.pekmavii.FragmentResult.ResultFragment;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class AdapterOlahraga extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ResultFragment.SendCabol {

    private Context context;
    private LayoutInflater inflater;
    List<HomeData> data= Collections.emptyList();
    int currentPos=0;
    private int cabolPos;
    AdapterView.OnItemClickListener itemClickListener;

    public AdapterOlahraga(Context context, List<HomeData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_home, parent,false);
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

        System.out.println(currenthome);
        HomeData currenthome2 = data.get(0);
        System.out.println(cabolPos + "BERHASIL");


        if (cabolPos == idevent) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        myHolderHome.tvplayerA.setText(currenthome.playerA);
        myHolderHome.tvplayerB.setText(currenthome.playerB);

            switch (jurA) {
                case "D4 Akuntansi":
                case "D3 Akuntansi":
                case "D4 Akuntansi Tugas belajar":
                case "D3 Manajemen Aset":
                case "D3 Akuntansi Tugas Belajar":
                    Picasso.with(context).load(R.drawable.warna_akun).fit().into(myHolderHome.jurAciv);

                    break;
                case "D1 Pajak":
                case "D3 Pajak":
                case "D3 Pajak Tugas Belajar":
                case "D3 Penilai":
                    Picasso.with(context).load(R.drawable.warna_pajak).fit().into(myHolderHome.jurAciv);

                    break;
                case "D1 maskot_bc":
                case "D3 maskot_bc":
                    Picasso.with(context).load(R.drawable.warna_bc).fit().into(myHolderHome.jurAciv);

                    break;
                default:
                    Picasso.with(context).load(R.drawable.warna_kbn).fit().into(myHolderHome.jurAciv);
                    break;
            }

            switch (jurB) {
                case "D4 Akuntansi":
                case "D3 Akuntansi":
                case "D4 Akuntansi Tugas belajar":
                case "D3 Manajemen Aset":
                case "D3 Akuntansi Tugas Belajar":
                    Picasso.with(context).load(R.drawable.warna_akun).fit().into(myHolderHome.jurBciv);

                    break;
                case "D1 Pajak":
                case "D3 Pajak":
                case "D3 Pajak Tugas Belajar":
                case "D3 Penilai":
                    Picasso.with(context).load(R.drawable.warna_pajak).fit().into(myHolderHome.jurBciv);

                    break;
                case "D1 maskot_bc":
                case "D3 maskot_bc":
                    Picasso.with(context).load(R.drawable.warna_bc).fit().into(myHolderHome.jurBciv);

                    break;
                default:
                    Picasso.with(context).load(R.drawable.warna_kbn).fit().into(myHolderHome.jurBciv);
                    break;
            }

        ((MyHolderHome) holderhome).setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivityHome(playerA,playerB,jurA,jurB,msdate,mstime);
            }
        });

        } else {
            holderhome.itemView.setVisibility(View.GONE);
            holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    private void openDetailActivityHome(String playerA, String playerB, String jurA, String jurB, String msDate, String msTime) {
        Intent i=new Intent(context, DetailActivityHomeMatch.class);

        //PACK DATA TO SEND
        i.putExtra("MSTIME", msTime);
        i.putExtra("MSDATE", msDate);
        i.putExtra("NAME_KEY_A",playerA);
        i.putExtra("NAME_KEY_B",playerB);
        i.putExtra("NAME_KEY_A_JUR",jurA);
        i.putExtra("NAME_KEY_B_JUR",jurB);

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

    class MyHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener {

        HomeData homeData;
        TextView tvplayerA,tvplayerB;
        ItemClickListener itemClickListener;
        CircleImageView jurAciv,jurBciv;


        // create constructor to get widget reference
        public MyHolderHome(View itemView) {
            super(itemView);

            tvplayerA= itemView.findViewById(R.id.txtPlayerA);
            tvplayerB= itemView.findViewById(R.id.txtPlayerB);
            jurAciv = itemView.findViewById(R.id.jurAciv);
            jurBciv = itemView.findViewById(R.id.jurBciv);

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

    public void parseCabolData() {

    }
}
