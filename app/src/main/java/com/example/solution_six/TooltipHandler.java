package com.example.solution_six;


import android.app.Activity;
import android.content.Context;
import android.view.View;



    public class TooltipHandler implements  IHandler {

        private Context context;
        private String text;
        private int resId;
        private Controller controller;

        private CustomTooltip customTooltip;

        public TooltipHandler(Context context, String text, int resId,
                              Controller controller) {
            this.context = context;
            this.text = text;
            this.resId = resId;
            this.controller = controller;

            View view =  ((Activity)context).findViewById(resId);
            customTooltip = new CustomTooltip(context, text, view , this);

        }

        @Override
        public void show() {
            //Thread button;
            customTooltip.showTooltip();

        }


        @Override
        public void dismiss() {
            customTooltip.dismissTooltip();
            controller.notifyOnDismissed();
        }


    }

