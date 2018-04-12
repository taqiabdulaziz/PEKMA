package id.pekma.pekmavii.FragmentResult.Olahraga.Schedule;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import id.pekma.pekmavii.FragmentHome.DetailActivityHomeMatch;
import id.pekma.pekmavii.FragmentHome.HomeData;
import id.pekma.pekmavii.FragmentNews.ItemClickListener;
import id.pekma.pekmavii.FragmentResult.CabolDataGetSet;
import id.pekma.pekmavii.FragmentResult.Olahraga.OlahragaData;
import id.pekma.pekmavii.FragmentResult.ResultFragment;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class AdapterOlahraga extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ResultFragment.SendCabol {

    List<OlahragaData> data = Collections.emptyList();
    int currentPos = 0;
    AdapterView.OnItemClickListener itemClickListener;
    private Context context;
    private LayoutInflater inflater;
    private int cabolPos,resOrSchedpos;
    int resOrSched,position,done;
    int sudah1 = 1;
    int sudah2 = 1;




    public AdapterOlahraga(Context context, List<OlahragaData> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        final int cabolData = data.get(0).getCabolData();
        final int resOrSched = data.get(0).getResOrSched();

        cabolPos = cabolData;
        resOrSchedpos = resOrSched;

        if (this.done ==1 && this.resOrSchedpos == 1){
            view = inflater.inflate(R.layout.item_home_latest, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_home, parent, false);
        }

        MyHolderHome holderhome = new MyHolderHome(view);


        return holderhome;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderhome, int position) {

        //DATA UNTUK KE DETAIL ACTIVITY HOME
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



        MyHolderHome myHolderHome = (MyHolderHome) holderhome;
        OlahragaData currenthome = data.get(position);
        OlahragaData currenthome2 = data.get(0);
        System.out.println(idcat + " ID CAT");
        System.out.println(cabolPos + " CABOL POS");
        System.out.println(resOrSchedpos + " res or sched");
        System.out.println(done + " DONE!");

        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal2.add(Calendar.DAY_OF_WEEK, -1);

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date strDate = sdf.parse(msdate);
            Date strDateSystem = cal.getTime();
            Date strDateSystem2 = cal2.getTime();

            System.out.println(strDate +"WKT");
            System.out.println(strDateSystem +"WKTS");

            if (strDateSystem.before(strDate) || strDateSystem.equals(strDate) || strDateSystem2.before(strDate)) {
//                RESULT = 1 SCHEDULE = 0
                if (idcat == cabolPos) {
                    if (done == 0) {
                        if (resOrSchedpos == 0) {
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
                            myHolderHome.msTime.setText(sdf.format(time));
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
                                    openDetailActivityHome(playerA, playerB, jurA, jurB, msdate, mstime, String.valueOf(idcat), loc, cat);
                                }
                            });

                        } else {
                            holderhome.itemView.setVisibility(View.GONE);
                            holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

                        }
                    } else if (done == 1) {
                        if (resOrSchedpos == 1) {


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
                            System.out.println(time + "TIME WOI");

                            //SKOR
                            if (this.done == 1 && resOrSchedpos == 1) {
                                myHolderHome.msTime.setText(sdf.format(time));
                            }
                            myHolderHome.msTime.setText(sdf.format(time));
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
                                    openDetailActivityHome(playerA, playerB, jurA, jurB, msdate, mstime, String.valueOf(idcat), loc, cat);
                                }
                            });
                        } else {
                            holderhome.itemView.setVisibility(View.GONE);
                            holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

                        }
                    } else if (data.get(position).getDone() == null) {
                        holderhome.itemView.setVisibility(View.GONE);
                        holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                    }
                } else {
                    holderhome.itemView.setVisibility(View.GONE);
                    holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                    sudah2 = 2;
                }
            } else {
                holderhome.itemView.setVisibility(View.GONE);
                holderhome.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public void show(RecyclerView.ViewHolder holderhome, int position) {
    }



    private void openDetailActivityHome(String playerA, String playerB, String jurA, String jurB, String msDate, String msTime, String idcat, String loc, String cat) {
        Intent i = new Intent(context, DetailActivityHomeMatch.class);

        //PACK DATA TO SEND
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

    @Override
    public void sendCabolData(int message) {
    }

    class MyHolderHome extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvplayerA, tvplayerB,msTime;
        ItemClickListener itemClickListener;
        ImageView jurAciv, jurBciv;


        // create constructor to get widget reference
        public MyHolderHome(View itemView) {
            super(itemView);

            tvplayerA = itemView.findViewById(R.id.txtPlayerA);
            tvplayerB = itemView.findViewById(R.id.txtPlayerB);
            jurAciv = itemView.findViewById(R.id.jurAciv);
            jurBciv = itemView.findViewById(R.id.jurBciv);
            msTime = itemView.findViewById(R.id.UF_Time_1);

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



