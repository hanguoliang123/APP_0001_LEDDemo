package com.ecarx.app_0001_leddemo;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import android.os.ILedService;
//import android.os.ServiceManager;

import android.os.IBinder;

public class MainActivity extends AppCompatActivity {

    private boolean ledon = false;
    private Button button = null;
    private CheckBox checkBoxLed1 = null;
    private CheckBox checkBoxLed2 = null;
    private CheckBox checkBoxLed3 = null;
    private CheckBox checkBoxLed4 = null;

    Object proxy=null;

    Method ledCtrl = null;

    //private ILedService iLedService = null;

    class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            //iLedService = new ILedService();

            ledon = !ledon;
            if(ledon){
                button.setText("ALL OFF");
                checkBoxLed1.setChecked(true);
                checkBoxLed2.setChecked(true);
                checkBoxLed3.setChecked(true);
                checkBoxLed4.setChecked(true);

                try
                {
                    for(int i=0;i<4;i++)
                        //iLedService.LedCtrl(i, 1);
                        ledCtrl.invoke(proxy,i,1);
                }catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }catch (InvocationTargetException e){
                    e.printStackTrace();
                }

            }
            else
            {
                button.setText("ALL ON");
                checkBoxLed1.setChecked(false);
                checkBoxLed2.setChecked(false);
                checkBoxLed3.setChecked(false);
                checkBoxLed4.setChecked(false);

                try
                {
                    for(int i=0;i<4;i++)
                        //iLedService.LedCtrl(i, 0);
                        ledCtrl.invoke(proxy,i,0);
                }catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }catch (InvocationTargetException e){
                    e.printStackTrace();
                }
            }

        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        try{
            // Check which checkbox was clicked
            switch(view.getId()) {
                case R.id.LED1:
                    if (checked){
                        Toast.makeText(getApplicationContext(),"LED1 ON", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(0, 1);
                        ledCtrl.invoke(proxy,0,1);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"LED1 OFF", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(0, 0);
                        ledCtrl.invoke(proxy,0,0);
                    }
                    // Remove the meat
                    break;
                case R.id.LED2:
                    if (checked){
                        Toast.makeText(getApplicationContext(),"LED2 ON", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(1, 1);
                        ledCtrl.invoke(proxy,1,1);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"LED2 OFF", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(1, 0);
                        ledCtrl.invoke(proxy,1,0);
                    }
                    // Remove the meat
                    break;
                case R.id.LED3:
                    if (checked){
                        Toast.makeText(getApplicationContext(),"LED3 ON", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(2, 1);
                        ledCtrl.invoke(proxy,2,1);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"LED3 OFF", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(2, 0);
                        ledCtrl.invoke(proxy,2,0);
                    }
                    // Remove the meat
                    break;
                case R.id.LED4:
                    if (checked){
                        Toast.makeText(getApplicationContext(),"LED4 ON", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(3, 1);
                        ledCtrl.invoke(proxy,3,1);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"LED4 OFF", Toast.LENGTH_SHORT).show();
                        //iLedService.LedCtrl(3, 0);
                        ledCtrl.invoke(proxy,3,0);
                    }
                    // Remove the meat
                    break;
                // TODO: Veggie sandwich
            }
        }catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.BUTTON);

        try{
            //iLedService = ILedService.Stub.asInterface(ServiceManager.getService("led"));
            Method getService = Class.forName("android.os.ServiceManager").getMethod("getService",String.class);
            Object ledService = getService.invoke(null, "led");


            Method asInterface = Class.forName("android.os.ILedService$Stub").getMethod("asInterface", IBinder.class);
            proxy = asInterface.invoke(null, ledService);
            ledCtrl = Class.forName("android.os.ILedService$Stub$Proxy").getMethod("LedCtrl", int.class, int.class);
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }


        //ledCtrl.invoke(proxy,0,1);

        checkBoxLed1 = (CheckBox) findViewById(R.id.LED1);
        checkBoxLed2 = (CheckBox) findViewById(R.id.LED2);
        checkBoxLed3 = (CheckBox) findViewById(R.id.LED3);
        checkBoxLed4 = (CheckBox) findViewById(R.id.LED4);

        button.setOnClickListener(new MyButtonListener());// 匿名类
/*
        button.setOnClickListener(new View.OnClickListener() {// 匿名类
            public void onClick(View v) {
                // Perform action on click
                ledon = !ledon;
                if(ledon)
                    button.setText("ALL OFF");
                else
                    button.setText("ALL ON");

            }
        });
*/

    }
}
