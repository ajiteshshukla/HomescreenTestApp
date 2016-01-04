package com.homescreentestapp.homescreentestapp.overlay;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.homescreentestapp.homescreentestapp.R;

/**
 * Created by ajitesh.shukla on 1/3/16.
 */
public class RunningAppsOverlay {
    private Context context;
    public RunningAppsOverlay(Context context) {
        this.context = context;
    }

    public void drawOverlay() {
        Log.d("home service", "implement logic to draw an overlay showing running apps");
    }
}
