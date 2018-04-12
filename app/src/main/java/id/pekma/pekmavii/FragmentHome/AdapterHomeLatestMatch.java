package id.pekma.pekmavii.FragmentHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import id.pekma.pekmavii.FragmentNews.ItemClickListener;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class AdapterHomeLatestMatch extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<HomeData> data= Collections.emptyList();
    HomeData current;

    AdapterHomeLatestMatch(Context context, List<HomeData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_home_latest, parent,false);
        MyHolderHome holderhome = new MyHolderHome(view);
        return holderhome;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderhome, int position) {

        //DATA UNTUK KE DETAIL ACTIVITY HOME
//        final String playerA = data.get(position).getPlayerA();
//        final String playerB = data.get(position).getPlayerB();
//        final String jurA = data.get(position).getJurA();
//        final String jurB = data.get(position).getJurB();
//        final String msdate = data.get(position).getMsDate();
//        final String mstime = data.get(position).getMstime();
//        final String skorA = data.get(position).getResultpa();
//        final String skorB = data.get(position).getResultpb();
//        final int done = Integer.parseInt(data.get(position).getDone());
//        final int idevent = data.get(position).getIdevent();

        final String playerA = data.get(position).getPlayerA();
        final String playerB = data.get(position).getPlayerB();
        final String loc = data.get(position).getLoc();
        final String jurA = data.get(position).getJurA();
        final String jurB = data.get(position).getJurB();
        final int idevent = data.get(position).getIdevent();
        final int done = Integer.parseInt(data.get(position).getDone());
        final String msdate = data.get(position).getMsDate();
        final int idcat = data.get(position).getIdcat();
        final String mstime = data.get(position).getMstime();
        final String cat = data.get(position).getCategory();
        final String skorA = data.get(position).getResultpa();
        final String skorB = data.get(position).getResultpb();

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolderHome myHolderHome = (MyHolderHome) holderhome;
        HomeData currenthome = data.get(position);


        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.add(Calendar.DAY_OF_WEEK, -2);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date strDate = sdf.parse(msdate);
            Date strDateSystem = cal.getTime();

            System.out.println(strDate.getDate() + "msdate");
            System.out.println(strDateSystem.getDate() + "msdateSys");

            if (done > 0 && strDate.compareTo(strDateSystem) >=0) {
                myHolderHome.skorA.setText(skorA);
                myHolderHome.skorB.setText(skorB);
                myHolderHome.tvplayerA.setText(currenthome.playerA);
                myHolderHome.tvplayerB.setText(currenthome.playerB);

                switch (jurA) {
                    case "Akuntansi":
                        Picasso.with(context).load(R.drawable.maskot_akun).fit().into(myHolderHome.jurAciv);


                        break;
                    case "Pajak":
                        Picasso.with(context).load(R.drawable.maskot_pajak).fit().into(myHolderHome.jurAciv);


                        break;
                    case "Bea Cukai":
                        Picasso.with(context).load(R.drawable.maskot_bc).fit().into(myHolderHome.jurAciv);


                        break;
                    case "Manajemen Keuangan":
                        Picasso.with(context).load(R.drawable.maskot_mankeu).fit().into(myHolderHome.jurAciv);


                        break;
                    default:
                        Picasso.with(context).load(R.drawable.maskot_sekre).fit().into(myHolderHome.jurAciv);

                        break;
                }

                switch (jurB) {
                    case "Akuntansi":
                        Picasso.with(context).load(R.drawable.maskot_akun).fit().into(myHolderHome.jurBciv);


                        break;
                    case "Pajak":
                        Picasso.with(context).load(R.drawable.maskot_pajak).fit().into(myHolderHome.jurBciv);


                        break;
                    case "Bea Cukai":
                        Picasso.with(context).load(R.drawable.maskot_bc).fit().into(myHolderHome.jurBciv);


                        break;
                    case "Manajemen Keuangan":
                        Picasso.with(context).load(R.drawable.maskot_mankeu).fit().into(myHolderHome.jurBciv);


                        break;
                    default:
                        Picasso.with(context).load(R.drawable.maskot_sekre).fit().into(myHolderHome.jurBciv);

                        break;
                }

                ((MyHolderHome) holderhome).setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(int pos) {
                        openDetailActivityHome(playerA,playerB,jurA,jurB,msdate,mstime,skorA,skorB, String.valueOf(idcat),loc,cat);
                    }
                });
            } else {

                holderhome.itemView.setVisibility(View.GONE);
                holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void openDetailActivityHome(String playerA, String playerB, String jurA, String jurB, String msDate, String msTime, String skorA, String skorB,String idcat,String loc, String cat) {
        Intent i=new Intent(context, DetailActivityHomeMatch.class);

//        //PACK DATA TO SEND
//        i.putExtra("RESULTPA", skorA);
//        i.putExtra("RESULTPB",skorB);
//        i.putExtra("MSTIME", msTime);
//        i.putExtra("MSDATE", msDate);
//        i.putExtra("NAME_KEY_A",playerA);
//        i.putExtra("NAME_KEY_B",playerB);
//        i.putExtra("NAME_KEY_A_JUR",jurA);
//        i.putExtra("NAME_KEY_B_JUR",jurB);
//        i.putExtra("IDEVENT",idevent);

        i.putExtra("RESULTPA", skorA);
        i.putExtra("RESULTPB",skorB);
        i.putExtra("MSTIME", msTime);
        i.putExtra("MSDATE", msDate);
        i.putExtra("IDEVENT",idcat);
        i.putExtra("NAME_KEY_A", playerA);
        i.putExtra("NAME_KEY_B", playerB);
        i.putExtra("NAME_KEY_A_JUR", jurA);
        i.putExtra("NAME_KEY_B_JUR", jurB);
        i.putExtra("LOC", loc);
        i.putExtra("CAT", cat);



        //open activity
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvplayerA,tvplayerB,skorA,skorB;
        ItemClickListener itemClickListener;
        ImageView jurAciv,jurBciv;

        // create constructor to get widget reference
        MyHolderHome(View itemView) {
            super(itemView);

            skorA = itemView.findViewById(R.id.skorTimA);
            skorB = itemView.findViewById(R.id.skorTimB);
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
