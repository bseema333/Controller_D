package com.example.solution_six;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static MainActivity registeredActivity;
    private static Controller instance;

    private boolean isShowingTooltip = false;
    private Handler handler;
    private boolean tooltipShown;


    private IHandler currentTooltipHandler;

    private List<IHandler> handlerList = new ArrayList<IHandler>();

    void init(Context context) {

        handlerList.add(new TooltipHandler(context, "Tooltip1", R.id.button1, this));
        handlerList.add(new TooltipHandler(context, "Tooltip2", R.id.button2, this));
        handlerList.add(new TooltipHandler(context, "Tooltip3", R.id.button3, this));

    }


    void NextTooltip() {

        if (isShowingTooltip || handlerList.isEmpty())
            return;

        IHandler handler = handlerList.get(0);
        currentTooltipHandler = handler;
        isShowingTooltip = true;
        currentTooltipHandler.show();


    }


    void notifyOnDismissed() {
        if (handlerList.isEmpty())

            return;

        handlerList.remove(0);
        isShowingTooltip = false;
        //triggerNextTooltip();


    }

    void dismiss() {
        currentTooltipHandler.dismiss();
    }







   public static synchronized Controller getInstance() {
        if (instance == null) {
         instance = new Controller();
      }
       return instance;
 }
   public void registerActivity(MainActivity activity) {
        registeredActivity = activity;
       handler = new Handler();
    }

}
