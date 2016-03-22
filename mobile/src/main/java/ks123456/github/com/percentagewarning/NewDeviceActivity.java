package ks123456.github.com.percentagewarning;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NewDeviceActivity extends AppCompatActivity {

    Button addBtn;
    EditText name;
    EditText ip;
    EditText percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);

        ActionBar tb = getSupportActionBar();
        tb.setTitle("Add Device");

        addBtn = (Button) findViewById(R.id.button2);
        name = (EditText) findViewById(R.id.editText);
        ip = (EditText) findViewById(R.id.editText2);
        percentage = (EditText) findViewById(R.id.editText6);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n;
                String i;
                int p;
                String errmsg = "";
                n = name.getText().toString();
                i = ip.getText().toString();
                p = Integer.parseInt(percentage.getText().toString());
                if (n != null && !n.isEmpty()) {
                    if (i != null && !i.isEmpty()) {
                        registerDevice(n, i, p, view);
                    }
                    else {
                        if (n.isEmpty()) {
                            errmsg = "You have not provided a device name. Plee do so.";
                        }
                        if (i.isEmpty()) {
                            errmsg = "You have not provided a device ip. Please do so.";
                        }
                        if (i.isEmpty() && n.isEmpty()) {
                            errmsg = "You have not provided a device name and ip. Please do so.";
                        }
                        Snackbar.make(view, errmsg, Snackbar.LENGTH_SHORT).show();
                        errmsg = "";
                    }
                }
                else {
                    if (n.isEmpty()) {
                        errmsg = "You have not provided a device name. Please do so.";
                    }
                    if (i.isEmpty()) {
                        errmsg = "You have not provided a device ip. Please do so.";
                    }
                    if (i.isEmpty() && n.isEmpty()) {
                        errmsg = "You have not provided a device name and ip. Please do so.";
                    }
                    Snackbar.make(view, errmsg, Snackbar.LENGTH_SHORT).show();
                    errmsg = "";
                }
            }
        });
}
    public void registerDevice(String name, String ip, int percentage, View v) {
        try {
            JSONObject jobj = new JSONObject();
            jobj.put("name", name);
            jobj.put("ip", ip);
            jobj.put("percentage", percentage);
            String txt = jobj.toString();
            String FileName = "percentagewarning_devices";
            File deviceFile = new File("sdcard/" + FileName);
            if (!deviceFile.exists()) {
                try {
                    deviceFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    Snackbar.make(v, "Something went wrong while creating your new device", Snackbar.LENGTH_LONG).show();
                }
            }
            try {
                BufferedWriter bu = new BufferedWriter(new FileWriter(deviceFile, true));
                bu.append(txt);
                bu.newLine();
                bu.close();
                Intent intent = new Intent(NewDeviceActivity.this, MainActivity.class);
                Context context = getApplicationContext();
                CharSequence text = "Device succesfully created";
                int dur = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, dur);
                toast.show();
                startActivity(intent);
            }
            catch (IOException e) {
                e.printStackTrace();
                Snackbar.make(v, "Something went wrong while creating your new device" + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            Snackbar.make(v, "Something went wrong while creating your new device", Snackbar.LENGTH_LONG).show();
        }

    }
}
