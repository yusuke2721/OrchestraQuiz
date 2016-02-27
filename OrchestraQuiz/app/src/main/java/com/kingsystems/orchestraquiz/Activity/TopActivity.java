package com.kingsystems.orchestraquiz.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kingsystems.orchestraquiz.R;

/**
 * トップ画面アクティビティ
 */
public class TopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
    }

    /***
     * ボタンクリック時イベント
     * @param view 画面情報
     */
    public void onClickButton(View view) {
        final Intent intent = new Intent(this, MainActivity.class);

        switch (view.getId()) {
            case R.id.level1Button:
                intent.putExtra("level","初級");
                break;
            case R.id.level2Button:
                intent.putExtra("level","中級");
                break;
            case R.id.level3Button:
                intent.putExtra("level","上級");
                break;
        }

        //問題画面への遷移
        startActivity(intent);
        }

    public void exitApplication(View view) {
        moveTaskToBack(true);
    }

    }
