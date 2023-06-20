package com.example.solution_six;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Controller controller;
    Button mybutton1,mybutton2,mybutton3;
    private View anchorView;
    private HashMap<Integer, Boolean> tooltipStatusMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        controller = Controller.getInstance();
        controller.registerActivity(this);
        controller.init(this);

        //private HashMap<Integer, Boolean> tooltipStatusMap;
        mybutton1 = findViewById(R.id.button1);
        mybutton2 = findViewById(R.id.button2);
        mybutton3 = findViewById(R.id.button3);

        tooltipStatusMap = new HashMap<>();
        tooltipStatusMap.put(R.id.button1, false);
        tooltipStatusMap.put(R.id.button2, false);
        tooltipStatusMap.put(R.id.button3, false);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Show the tooltip when the window gains focus and the activity is visible
        if (hasFocus && controller != null) {
            controller.NextTooltip();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();



    }}
