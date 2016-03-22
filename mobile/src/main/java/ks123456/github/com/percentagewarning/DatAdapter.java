package ks123456.github.com.percentagewarning;

import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DatAdapter extends RecyclerView.Adapter<DatAdapter.DeviceViewHolder> {
        List<Device> devices;
        DatAdapter(List<Device> devices) {
            this.devices = devices;
        }

        @Override
        public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
            DeviceViewHolder dvh = new DeviceViewHolder(v);
            return dvh;
        }

        @Override
        public void onBindViewHolder(DeviceViewHolder h, int position) {
            h.deviceName.setText(devices.get(position).name);
            h.deviceIP.setText(devices.get(position).ip);
            h.deviceIcon.setImageResource(devices.get(position).icon);
            final Device device = devices.get(position);
            h.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity dev = new MainActivity();
                    dev.openDevice(device.name, device.ip, device.percentage, v);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (devices != null) {
                return devices.size();
            }
            return 0;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class DeviceViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView deviceName;
            TextView deviceIP;
            ImageView deviceIcon;
            DeviceViewHolder(View v) {
                super(v);
                cv = (CardView)itemView.findViewById(R.id.cv);
                deviceName = (TextView)itemView.findViewById(R.id.device_name);
                deviceIP = (TextView)itemView.findViewById(R.id.device_ip);
                deviceIcon = (ImageView)itemView.findViewById(R.id.device_icon);
            }
        }
}
