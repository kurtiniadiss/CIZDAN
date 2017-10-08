package com.coinomi.wallet.tasks;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Halit on 7.10.2017.
 */

public final class  HttpServiceManager extends AsyncTask<Void, Void, Void> {

    private Activity activity;
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public HttpServiceManager(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        URL url = null;
        try {
            url = new URL("http://172.20.10.8:8096/wallet");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setDoOutput(true);
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Content-Type", "application/json");

        System.out.println("latitude:"+latitude);
        System.out.println("longitude:"+longitude);

        String deviceId = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        String input = "{\"id\":\""+deviceId+"\",\"latitude\":"+ latitude+", \"longitude\":"+longitude+"}";

        OutputStream os = null;
        try {
            os = conn.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.write(input.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (conn.getResponseCode() != 200) {
                System.out.println("Failed : HTTP error code : "+ conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output;
        System.out.println("Output from Server .... \n");
        try {
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.disconnect();
        return null;
    }

}

