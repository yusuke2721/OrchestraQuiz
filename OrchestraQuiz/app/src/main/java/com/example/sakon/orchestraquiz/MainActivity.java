package com.example.sakon.orchestraquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int answer;
    private Button[] button = new Button[4];
    private Button nextButton;

    private Question question = new Question();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面項目の取得
        textView = (TextView) findViewById(R.id.textView1);
        button[0] = (Button) findViewById(R.id.button0);
        button[1] = (Button) findViewById(R.id.button1);
        button[2] = (Button) findViewById(R.id.button2);
        button[3] = (Button) findViewById(R.id.button3);
        nextButton = (Button) findViewById(R.id.nextButton);

        //問題作成
        this.makeQuestion();

        //問題文のセット
        textView.setText(question.getChoice(question.getAnsNumber()).getSymbol());

        //選択肢に文言をセット
        for (int i = 0; i <= 3; i++) {
            button[i].setText(question.getChoice(i).getMeaning());
            button[i].setAllCaps(false);
        }

        //画面へのセット処理ここまで
    }

    /**
     * クリック時イベント
     * @param view 画面情報
     */
    public void OnClickButton(View view) {
        switch(view.getId()) {
            case R.id.button0:
                answer = 0;
                break;
            case R.id.button1:
                answer = 1;
                break;
            case R.id.button2:
                answer = 2;
                break;
            case R.id.button3:
                answer = 3;
                break;
        }

        //正解判定
        if (answer == question.getAnsNumber() ){
            //正解時処理
            textView.setText("正解");
        }
        else {
            //不正解時処理
            textView.setText("不正解");
        }

        //回答後、ボタンを非活性化・解説の出力
        for (int i = 0; i <= 3; i++) {
            button[i].setEnabled(false);
            button[i].setText(question.getChoice(i).getSymbol() + " : " + question.getChoice(i).getMeaning());
        }

        //次問題への遷移ボタン表示
        nextButton.setVisibility(View.VISIBLE);
    }

    /**
     * 次の問題への移行
     * @param view 画面情報
     */
    public void ToNextQuestion(View view) {
        for (int i = 0; i <= 3; i++) {
            button[i].setEnabled(true);
        }
        nextButton.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    /**
     * 問題の作成処理
     * question に 選択肢とする音楽記号と、正解選択肢の番号(0～3)を格納する。
     */
    public void makeQuestion () {
        //本来はここでCSVから取り出したものを使う。

        //選択肢番号0
        this.question.addChoices(new MusicalSymbol("dolce","甘く"));
        //選択肢番号1
        this.question.addChoices(new MusicalSymbol("piano", "弱く"));
        //選択肢番号2
        this.question.addChoices(new MusicalSymbol("forte", "強く"));
        //選択肢番号3
        this.question.addChoices(new MusicalSymbol("Fine", "終わり"));

        //正解選択肢の番号
        this.question.setAnsNumber(0);
    }
}
