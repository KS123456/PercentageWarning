package ks123456.github.com.percentagewarning;

/**
 * Created by koens on 19-3-2016.
 */
class Device {
    String name;
    String ip;
    int icon;
    int percentage;
    Device(String name, String ip, int icon, int percentage) {
        this.name = name;
        this.ip = ip;
        this.icon = icon;
        this.percentage = percentage;
    }
}