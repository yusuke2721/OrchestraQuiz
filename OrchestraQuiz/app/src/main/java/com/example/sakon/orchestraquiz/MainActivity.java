package com.example.sakon.orchestraquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //画面項目
    private TextView quizCount;
    private TextView textView;
    private Button[] button = new Button[4];
    private Button nextButton;

    //問題
    private int answer;
    private int questionNum;
    private Question question = new Question();
    private ArrayList<Question> questionList = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面項目の取得
        quizCount = (TextView) findViewById(R.id.quizCount);
        textView = (TextView) findViewById(R.id.textView1);
        button[0] = (Button) findViewById(R.id.button0);
        button[1] = (Button) findViewById(R.id.button1);
        button[2] = (Button) findViewById(R.id.button2);
        button[3] = (Button) findViewById(R.id.button3);
        nextButton = (Button) findViewById(R.id.nextButton);

        // TODO
        CSVParser parser = new CSVParser();
        Context context = getApplicationContext();
        parser.parse(context, questionList);

        //問題数の初期化
        questionNum = 1;

        //最初の問題作成
        this.makeQuestion();
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

        questionNum++;
        this.makeQuestion();
    }

    /**
     * 問題の作成処理
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
        this.question.setAnsNumber((int)(Math.random() * 4));

        //問題数のセット
        quizCount.setText(String.valueOf(questionNum) + "問目");

        //問題文のセット
        textView.setText(question.getChoice(question.getAnsNumber()).getSymbol());

        //選択肢に文言をセット
        for (int i = 0; i <= 3; i++) {
            button[i].setText(question.getChoice(i).getMeaning());
            button[i].setAllCaps(false);
        }

    }
}
