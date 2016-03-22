package ks123456.github.com.percentagewarning;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapater;
    private RecyclerView rv;
    private List<Device> devices;
    TextView nm;
    private static final String TAG_NAME = "name";
    private static final String TAG_IP = "ip";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (TextView)findViewById(R.id.device_name);
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        initializeData();
        initializeAdapter();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewDeviceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initializeData() {
        devices = new ArrayList<>();
        String FileName = "percentagewarning_devices";
        File file = new File("sdcard/" + FileName);
        StringBuilder txt = new StringBuilder();
        int lineCount = 0;
        if (!file.exists()) {
            Toast.makeText(getApplicationContext(), "You don't have any devices yet. Tap the plus button to add a new device.", Toast.LENGTH_LONG).show();
        }
        else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                            JSONObject jsono = new JSONObject(line);
                            lineCount++;
                            devices.add(new Device(jsono.getString("name"), jsono.getString("ip"), R.drawable.ic_computer_black_24dp, jsono.getInt("percentage")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initializeAdapter() {

        DatAdapter adapter = new DatAdapter(devices);
        rv.setAdapter(adapter);
    }
    public void openDevice(String name, String ip, int per, View ve) {
        Context ctx = getBaseContext();
        Intent it = new Intent(ctx, DeviceActivity.class);
        it.putExtra("devName", name);
        it.putExtra("devIP", ip);
        it.putExtra("devPer", per);
    }
}
