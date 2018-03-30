package id.pekma.pekmavii.FragmentHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import id.pekma.pekmavii.FragmentNews.ItemClickListener;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<HomeData> data= Collections.emptyList();
    HomeData current;
    AdapterView.OnItemClickListener itemClickListener;

    AdapterHome(Context context, List<HomeData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_home, parent,false);
        return new MyHolderHome(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderhome, int position) {

        //DATA UNTUK KE DETAIL ACTIVITY HOME
        final String playerA = data.get(position).getPlayerA();
        final String playerB = data.get(position).getPlayerB();
        final String jurA = data.get(position).getJurA();
        final String jurB = data.get(position).getJurB();
        final String msdate = data.get(position).getMsDate();
        final String mstime = data.get(position).getMstime();
        final int done = Integer.parseInt(data.get(position).getDone());
        final int idevent = data.get(position).getIdevent();

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderHome myHolderHome = (MyHolderHome) holderhome;
        HomeData currenthome = data.get(position);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date strDate = sdf.parse(msdate);
            if (done == 0) {

                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
                Date date1 = null;
                try {
                    date1 = sdfTime.parse(currenthome.mstime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                assert date1 != null;
                long time = date1.getTime();
                sdf.applyPattern("HH:mm");

                sdf.applyPattern("HH:mm");
                myHolderHome.tvplayerA.setText(currenthome.playerA);
                myHolderHome.msTime.setText(sdf.format(time));
                myHolderHome.tvplayerB.setText(currenthome.playerB);

                switch (jurA) {
                    case "D4 Akuntansi":
                    case "D3 Akuntansi":
                    case "D4 Akuntansi Tugas belajar":
                    case "D3 Manajemen Aset":
                    case "D3 Akuntansi Tugas Belajar":

                        Picasso.with(context).load(R.drawable.maskot_akun).fit().into(myHolderHome.jurAciv);
                        break;
                    case "D1 Pajak":
                    case "D3 Pajak":
                    case "D3 Pajak Tugas Belajar":
                    case "D3 Penilai":

                        Picasso.with(context).load(R.drawable.maskot_pajak).fit().into(myHolderHome.jurAciv);
                        break;
                    case "D1 maskot_bc":
                    case "D3 maskot_bc":

                        Picasso.with(context).load(R.drawable.maskot_bc).fit().into(myHolderHome.jurAciv);
                        break;
                    default:

                        Picasso.with(context).load(R.drawable.maskot_mankeu).fit().into(myHolderHome.jurAciv);
                        break;
                }

                switch (jurB) {
                    case "D4 Akuntansi":
                    case "D3 Akuntansi":
                    case "D4 Akuntansi Tugas belajar":
                    case "D3 Manajemen Aset":
                    case "D3 Akuntansi Tugas Belajar":

                        Picasso.with(context).load(R.drawable.maskot_akun).fit().into(myHolderHome.jurBciv);
                        break;
                    case "D1 Pajak":
                    case "D3 Pajak":
                    case "D3 Pajak Tugas Belajar":
                    case "D3 Penilai":

                        Picasso.with(context).load(R.drawable.maskot_pajak).fit().into(myHolderHome.jurBciv);
                        break;
                    case "D1 maskot_bc":
                    case "D3 maskot_bc":

                        Picasso.with(context).load(R.drawable.maskot_bc).fit().into(myHolderHome.jurBciv);
                        break;
                    default:

                        Picasso.with(context).load(R.drawable.maskot_mankeu).fit().into(myHolderHome.jurBciv);
                        break;
                }

                ((MyHolderHome) holderhome).setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(int pos) {
                        openDetailActivityHome(playerA,playerB,jurA,jurB,msdate,mstime, String.valueOf(idevent));
                    }
                });

            } else {
                System.out.println("kurang dari");
                holderhome.itemView.setVisibility(View.GONE);
                holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void openDetailActivityHome(String playerA, String playerB, String jurA, String jurB, String msDate, String msTime, String idevent) {
        Intent i=new Intent(context, DetailActivityHomeMatch.class);

        //PACK DATA TO SEND
        i.putExtra("MSTIME", msTime);
        i.putExtra("MSDATE", msDate);
        i.putExtra("NAME_KEY_A",playerA);
        i.putExtra("NAME_KEY_B",playerB);
        i.putExtra("NAME_KEY_A_JUR",jurA);
        i.putExtra("NAME_KEY_B_JUR",jurB);
        i.putExtra("IDEVENT",idevent);

        //open activity
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvplayerA,tvplayerB,msTime;
        ItemClickListener itemClickListener;
        ImageView jurAciv,jurBciv;


        // create constructor to get widget reference
        MyHolderHome(View itemView) {
            super(itemView);

            msTime = itemView.findViewById(R.id.UF_Time_1);
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
        void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener=itemClickListener;
        }
    }
}
