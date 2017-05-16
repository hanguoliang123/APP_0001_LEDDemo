package com.ecarx.app_0001_leddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean ledon = false;
    private Button button = null;
    private CheckBox checkBoxLed1 = null;
    private CheckBox checkBoxLed2 = null;
    private CheckBox checkBoxLed3 = null;
    private CheckBox checkBoxLed4 = null;

    class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ledon = !ledon;
            if(ledon){
                button.setText("ALL OFF");
                checkBoxLed1.setChecked(true);
                checkBoxLed2.setChecked(true);
                checkBoxLed3.setChecked(true);
                checkBoxLed4.setChecked(true);
            }
            else
            {
                button.setText("ALL ON");
                checkBoxLed1.setChecked(false);
                checkBoxLed2.setChecked(false);
                checkBoxLed3.setChecked(false);
                checkBoxLed4.setChecked(false);
            }

        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.LED1:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED1 ON", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"LED1 OFF", Toast.LENGTH_SHORT).show();
                }
                // Remove the meat
                break;
            case R.id.LED2:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED2 ON", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"LED2 OFF", Toast.LENGTH_SHORT).show();
                }
                // Remove the meat
                break;
            case R.id.LED3:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED3 ON", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"LED3 OFF", Toast.LENGTH_SHORT).show();
                }
                // Remove the meat
                break;
            case R.id.LED4:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED4 ON", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"LED4 OFF", Toast.LENGTH_SHORT).show();
                }
                // Remove the meat
                break;
            // TODO: Veggie sandwich
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.BUTTON);

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
