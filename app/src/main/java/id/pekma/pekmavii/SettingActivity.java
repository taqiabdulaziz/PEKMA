package id.pekma.pekmavii;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.onesignal.OneSignal;

public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    Switch newsSwitch = null;
    Button handbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newsSwitch = findViewById(R.id.notifNewsSwitch);
        newsSwitch.setOnCheckedChangeListener(this);

        handbook = findViewById(R.id.downloadHbk);
        handbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getString(R.string.url_handbook)); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            OneSignal.setSubscription(true);
        } else {
            //do something when unchecked
            OneSignal.setSubscription(false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
