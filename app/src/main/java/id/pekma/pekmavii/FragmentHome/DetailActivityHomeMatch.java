package id.pekma.pekmavii.FragmentHome;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import id.pekma.pekmavii.R;

public class DetailActivityHomeMatch extends AppCompatActivity {

    TextView playerAtext,playerBtext,jurAtext,jurBtext,titledetail,msDateTxt,msTimeTxt;
    CircleImageView jurAciv,jurBciv;
    Context context;
    int akun,bc,kbn,pajak,manset,penilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home_match);
        Toolbar toolbar = findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        msTimeTxt = findViewById(R.id.msTime);
        msDateTxt = findViewById(R.id.msDate);
        playerAtext = findViewById(R.id.playerAtxt);
        playerBtext = findViewById(R.id.playerBtxt);
        jurAtext = findViewById(R.id.JurAtxt);
        jurBtext = findViewById(R.id.JurBtxt);
        jurAciv = findViewById(R.id.jurAciv);
        jurBciv = findViewById(R.id.jurBciv);

        //RECEIVE DATA
        Intent i=this.getIntent();
        String msDate = i.getExtras().getString("MSDATE");
        String mstime = i.getExtras().getString("MSTIME");
        String playerAname=i.getExtras().getString("NAME_KEY_A");
        String playerBname=i.getExtras().getString("NAME_KEY_B");
        String jurAname = i.getExtras().getString("NAME_KEY_A_JUR");
        String jurBname = i.getExtras().getString("NAME_KEY_B_JUR");

        //BIND DATA

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date1 = null;
        try {
            date1 = sdf.parse(mstime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date1.getTime();
        sdf.applyPattern("HH:mm");

        msTimeTxt.setText(sdf.format(time));
        msDateTxt.setText(msDate);
        playerAtext.setText(playerAname);
        playerBtext.setText(playerBname);
        jurAtext.setText(jurAname);
        jurBtext.setText(jurBname);

        if ((jurAname.equals("D4 Akuntansi"))
                || (jurAname.equals("D3 Akuntansi"))
                || (jurAname.equals("D4 Akuntansi Tugas belajar"))
                || (jurAname.equals("D3 Manajemen Aset"))
                || (jurAname.equals("D3 Akuntansi Tugas Belajar"))) {

            Picasso.with(context).load(R.drawable.warna_akun).into(jurAciv);
        } else if ((jurAname.equals("D1 Pajak"))
                || ((jurAname.equals("D3 Pajak")))
                || (jurAname.equals("D3 Pajak Tugas Belajar"))
                || (jurAname.equals("D3 Penilai"))){

            Picasso.with(context).load(R.drawable.warna_pajak).into(jurAciv);
        } else if ((jurAname.equals("D1 BC"))
                || (jurAname.equals("D3 BC"))) {

            Picasso.with(context).load(R.drawable.warna_bc).into(jurAciv);
        } else {

            Picasso.with(context).load(R.drawable.warna_kbn).into(jurAciv);
        }

        if ((jurBname.equals("D4 Akuntansi"))
                || (jurBname.equals("D3 Akuntansi"))
                || (jurBname.equals("D4 Akuntansi Tugas belajar"))
                || (jurBname.equals("D3 Manajemen Aset"))
                || (jurBname.equals("D3 Akuntansi Tugas Belajar"))) {

            Picasso.with(context).load(R.drawable.warna_akun).into(jurBciv);
        } else if ((jurBname.equals("D1 Pajak"))
                || ((jurBname.equals("D3 Pajak")))
                || (jurBname.equals("D3 Pajak Tugas Belajar"))
                || (jurBname.equals("D3 Penilai"))){

            Picasso.with(context).load(R.drawable.warna_pajak).into(jurBciv);
        } else if ((jurBname.equals("D1 BC"))
                || (jurBname.equals("D3 BC"))) {

            Picasso.with(context).load(R.drawable.warna_bc).into(jurBciv);
        } else {

            Picasso.with(context).load(R.drawable.warna_kbn).into(jurBciv);
        }
    }
}
