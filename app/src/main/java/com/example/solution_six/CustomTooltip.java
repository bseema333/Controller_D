package com.example.solution_six;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

public class CustomTooltip {

    private Context context;

    private View anchorView;
    private String tooltipText;
    private PopupWindow tooltipPopup;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String TOOLTIP_KEY_PREFIX  = "tooltip_shown";
    private IHandler iHandler;

    public CustomTooltip(Context context, String tooltipText, View anchorView,
                         IHandler iHandler) {
        this.context = context;
        this.tooltipText = tooltipText;
        this.anchorView = anchorView;
        this.iHandler = iHandler;
    }

    public void showTooltip() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //boolean tooltipShown = sharedPreferences.getBoolean(TOOLTIP_KEY_PREFIX + buttonid, false);

        boolean tooltipShown = sharedPreferences.getBoolean(TOOLTIP_KEY_PREFIX , false);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tooltipView = inflater.inflate(R.layout.custom_tooltip, null);
        TextView textView = tooltipView.findViewById(R.id.tooltip_text);
        textView.setText(tooltipText);

        tooltipPopup = new PopupWindow(
                tooltipView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tooltipPopup.setElevation(8f);
        tooltipPopup.setOutsideTouchable(true);

        tooltipView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int tooltipWidth = tooltipView.getMeasuredWidth();
        int tooltipHeight = tooltipView.getMeasuredHeight();

        int[] anchorLocation = new int[2];
        anchorView.getLocationInWindow(anchorLocation);
        int anchorViewX = anchorLocation[0];
        int anchorViewY = anchorLocation[1];

        int x = anchorViewX + anchorView.getWidth() / 2 - tooltipWidth / 2;
        int y = anchorViewY - tooltipHeight - 16; // Add an offset to position it above the anchor view

        tooltipPopup.showAtLocation(anchorView, Gravity.NO_GRAVITY, x, y);

        tooltipPopup.setOnDismissListener(() -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //editor.putBoolean(TOOLTIP_KEY, true);
            editor.putBoolean(TOOLTIP_KEY_PREFIX , true);
            editor.apply();

            iHandler.dismiss();
        });

        tooltipView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                return true;
            } else {
                return false;
            }
        });

        new Handler().postDelayed(() -> {
            dismissTooltip();
        }, 5000);
    }





    void dismissTooltip() {
        tooltipPopup.dismiss();
        tooltipPopup = null;


    }
}
