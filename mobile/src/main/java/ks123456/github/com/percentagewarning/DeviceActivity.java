package ks123456.github.com.percentagewarning;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class DeviceActivity extends AppCompatActivity {
    EditText nm;
    EditText ipp;
    EditText perc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String name;
        String ip;
        int percentage;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                ip = null;
                percentage = 000;
                Toast.makeText(getApplicationContext(), "Unable to load device properties", Toast.LENGTH_LONG).show();
            }
            else {
                name = extras.getString("devName");
                ip = extras.getString("devIP");
                percentage = extras.getInt("devPer");
            }
        }
        else {
            name = (String) savedInstanceState.getSerializable("devName");
            ip = (String) savedInstanceState.getSerializable("devIP");
            percentage = (int) savedInstanceState.getSerializable("devPer");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        nm = (EditText) findViewById(R.id.devNm);
        ipp = (EditText) findViewById(R.id.devIp);
        perc = (EditText) findViewById(R.id.devPerc);

        nm.setText(name);
        ipp.setText(ip);
        perc.setText(percentage);
    }
}
