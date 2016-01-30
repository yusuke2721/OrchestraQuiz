package com.example.sakon.orchestraquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView1);

    }
    public void OnClickButton(View view) {
        switch(view.getId()) {
            case R.id.button1:
                textView.setText("ア");
                break;
            case R.id.button2:
                textView.setText("イ");
                break;
            case R.id.button3:
                textView.setText("ウ");
                break;
            case R.id.button4:
                textView.setText("エ");
                break;
        }

    }
}
