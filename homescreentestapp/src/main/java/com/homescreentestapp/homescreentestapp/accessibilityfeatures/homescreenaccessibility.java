package com.homescreentestapp.homescreentestapp.accessibilityfeatures;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import com.homescreentestapp.homescreentestapp.overlay.RunningAppsOverlay;

/**
 * Created by ajitesh.shukla on 1/3/16.
 */
public class homescreenaccessibility extends AccessibilityService {
    private RunningAppsOverlay runningAppsOverlay;
    private static long home_key_event_starttime = 0;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        Log.d("home service", " home screen service connected");
        runningAppsOverlay = new RunningAppsOverlay(this);
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.notificationTimeout = 100;
        info.flags = AccessibilityServiceInfo.FLAG_REQUEST_FILTER_KEY_EVENTS;
        setServiceInfo(info);
        super.onServiceConnected();
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                return super.onKeyEvent(event);
            }
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_HOME && event.getAction() == KeyEvent.ACTION_DOWN) {
            home_key_event_starttime = event.getEventTime();
            return true;
        }

        if (event.getKeyCode() == KeyEvent.KEYCODE_HOME && event.getAction() == KeyEvent.ACTION_UP) {
            if ((event.getEventTime() - home_key_event_starttime) > 1000) {
                Log.d("home service", " drawing overlay now");
                runningAppsOverlay.drawOverlay();
                return true;
            }
        }
        return super.onKeyEvent(event);
    }
}