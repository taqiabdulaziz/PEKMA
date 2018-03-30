package id.pekma.pekmavii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.onesignal.OneSignal;

public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    Switch newsSwitch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newsSwitch = findViewById(R.id.notifNewsSwitch);

        newsSwitch.setOnCheckedChangeListener(this);
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
}
