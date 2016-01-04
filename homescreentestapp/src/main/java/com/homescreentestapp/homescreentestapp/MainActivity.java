package com.homescreentestapp.homescreentestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == button1.getId()) {
            if (isAccessibilityEnabled() == false) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            } else {
                Toast.makeText(this, "Accessibility already enabled", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == button2.getId()) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
                } else {
                    Toast.makeText(this, "Draw overlays already enabled", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Draw overlays already enabled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isAccessibilityEnabled() {
        if(Build.VERSION.SDK_INT >= 23)
            return false;
        else {
            int accessibilityEnabled = 0;
            String checkSettings = Settings.Secure.getString(this.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            try {
                accessibilityEnabled = Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
            } catch (Settings.SettingNotFoundException e) {
                //Log.d(AppConstants.TAG, " Settings not found Exception");
                e.printStackTrace();
            }
            if (accessibilityEnabled == 1 && checkSettings.contains(AppConstants.PACKAGE_NAME)) {
                //Log.d(AppConstants.TAG, " Settings fine. No need to redirect");
                return true;
            }
            return false;
        }
    }
}
