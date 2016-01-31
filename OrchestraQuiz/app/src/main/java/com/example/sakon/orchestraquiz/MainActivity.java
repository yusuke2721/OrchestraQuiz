package com.example.sakon.orchestraquiz;

import android.content.Context;
import android.graphics.Color;
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

    //問題関連
    private int answer;
    private int questionNum;
    private int rightAnsNum;
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

        // CSVデータの取り出し
        CSVParser parser = new CSVParser();
        Context context = getApplicationContext();
        parser.parse(context, questionList);

        //フィールドの初期化
        questionNum = 1;
        rightAnsNum = 0;

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
            this.rightAnsNum++;
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

        questionNum++;
        if(questionNum <= questionList.size()) {
            for (int i = 0; i <= 3; i++) {
                button[i].setEnabled(true);
            }
            nextButton.setVisibility(View.INVISIBLE);

            this.makeQuestion();
        }
        else
        {
            //全問題を解き終えた場合の処理
            for (int i = 0; i <= 3; i++) {
                button[i].setVisibility(View.INVISIBLE);
            }
            nextButton.setVisibility(View.INVISIBLE);
            quizCount.setText("全問終了");
            textView.setText(questionList.size() + " 問中 " + this.rightAnsNum + " 問正解です。");
        }
    }

    /**
     * 問題の作成処理
     */
    public void makeQuestion () {
        //出題問題
        this.question = questionList.get(questionNum - 1);

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
