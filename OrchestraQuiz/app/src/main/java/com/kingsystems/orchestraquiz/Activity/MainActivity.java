package com.kingsystems.orchestraquiz.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kingsystems.orchestraquiz.Service.CsvParser;
import com.kingsystems.orchestraquiz.Model.Question;
import com.kingsystems.orchestraquiz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 問題出題を行うメインのアクティビティ
 */
public class MainActivity extends AppCompatActivity {

    //１回のゲームでの問題数
    static final int MAX_QUESTION_NUM = 10;

    //画面項目
    private TextView quizCount;
    private TextView textView;
    private Button[] button = new Button[4];
    private TextView nextText;
    private Button exitButton;
    private Button retryButton;

    //問題関連
    private String answer;
    private int questionNum;
    private int rightAnsNum;
    private Question question = new Question();
    private List<Question> questionList = new ArrayList<Question>();

    //難易度
    private String level;

    //出題状態か解答表示状態かのステータス　0...出題　1...解答表示
    private int questionState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面項目の取得
        //問題数表示
        quizCount = (TextView) findViewById(R.id.quizCount);
        //問題文
        textView = (TextView) findViewById(R.id.textView1);
        //選択肢
        button[0] = (Button) findViewById(R.id.button0);
        button[1] = (Button) findViewById(R.id.button1);
        button[2] = (Button) findViewById(R.id.button2);
        button[3] = (Button) findViewById(R.id.button3);
        //次の問題へボタン
        nextText = (TextView) findViewById(R.id.nextText);
        nextText.setVisibility(View.INVISIBLE);
        //終了ボタン
        exitButton = (Button) findViewById(R.id.exitButton);
        //リトライボタン
        retryButton = (Button) findViewById(R.id.retryButton);
        retryButton.setVisibility(View.INVISIBLE);

        //難易度をフィールドにセット
        level = getIntent().getStringExtra("level");

        //出題状態に変更
        questionState = 0;

        // CSVからクイズを読み込む
        questionList.clear();
        CsvParser parser = new CsvParser();
        Context context = getApplicationContext();
        parser.createQuizList(context, questionList, level);

        //フィールドの初期化
        this.initializeState();

        //最初の問題作成
        this.makeQuestion();
    }

    /**
     * クリック時イベント
     *
     * @param view 画面情報
     */
    public void onClickButton(View view) {
        //どの回答が選ばれたかを取得
        String selectedAnswer = "";

        switch (view.getId()) {
            case R.id.button0:
                selectedAnswer = (String)button[0].getText();
                break;
            case R.id.button1:
                selectedAnswer = (String)button[1].getText();
                break;
            case R.id.button2:
                selectedAnswer = (String)button[2].getText();
                break;
            case R.id.button3:
                selectedAnswer = (String)button[3].getText();
                break;
        }

        //正解・不正解判定
        if (answer.equals(selectedAnswer)) {
            //正解時処理
            this.rightAnsNum++;
            textView.setText("正解");
        } else {
            //不正解時処理
            textView.setText("不正解");
        }

        //回答後、ボタンを非活性化・解説の出力
        for (int i = 0; i <= 3; i++) {
            button[i].setEnabled(false);
        }

        //解答表示状態に変更
        questionState = 1;

        //次問題への遷移ボタン表示
        nextText.setVisibility(View.VISIBLE);
    }

    /**
     * 次の問題への移行
     *
     */
    public boolean onTouchEvent(MotionEvent event) {
        //解答表示状態の時のみ処理を実施
        if (questionState == 1) {
            //出題状態に変更 (タッチイベント無効状態にする)
            questionState = 0;

            //現在問題数をカウントアップ
            questionNum++;

            if (questionNum <= MAX_QUESTION_NUM) {
                //次の問題がある場合

                //ボタン状態の変更
                for (int i = 0; i <= 3; i++) {
                    button[i].setEnabled(true);
                }
                nextText.setVisibility(View.INVISIBLE);

                //次の問題へ移行
                this.makeQuestion();
                return true;

            } else {
                //全問題を解き終えた場合の処理

                //ボタン状態の変更
                for (int i = 0; i <= 3; i++) {
                    button[i].setVisibility(View.INVISIBLE);
                }
                nextText.setVisibility(View.INVISIBLE);
                retryButton.setVisibility(View.VISIBLE);
                exitButton.setVisibility(View.VISIBLE);

                quizCount.setText(level + "  全問終了");

                //全問正解時は処理分岐
                if (MAX_QUESTION_NUM == rightAnsNum) {
                    textView.setText(MAX_QUESTION_NUM + " 問中 " + this.rightAnsNum + " 問正解です。\n全問正解！！！！！！！");
                    //TODO 全問正解特典として、左近秘蔵写真を表示する

                } else {
                    //全問正解でない場合
                    textView.setText(MAX_QUESTION_NUM + " 問中 " + this.rightAnsNum + " 問正解です。");
                }
                return true;
            }
        } else {
            //出題状態の時は何も処理を行わない
            return true;
        }
    }

    /**
     * 問題の作成処理
     */
    public void makeQuestion() {
        //出題問題
        this.question = questionList.get(questionNum - 1);

        //問題数のセット
        quizCount.setText(level + "  " + String.valueOf(questionNum) + "問目 / " + String.valueOf(MAX_QUESTION_NUM) + "問中");

        //問題文のセット
        textView.setText(question.getSymbol());

        //答えの保持
        answer = question.getMeaning(0);

        //選択肢のランダム化用変数の準備
        int random = (int)(Math.random() * 4);

        //選択肢に文言をセット
        for (int i = 0; i <= 3; i++) {
            button[random].setText(question.getMeaning(i));
            button[random].setAllCaps(false);

            random++;
            if(random == 4) {
                random = 0;
            }
        }
    }

    /**
     * トップ画面への遷移
     *
     * @param view
     */
    public void toTopScreen(View view) {
        //問題数カウントの初期化
        this.initializeState();

        //画面遷移
        final Intent intent = new Intent(this, TopActivity.class);
        startActivity(intent);
    }

    /**
     * リトライボタン
     *
     * @param view
     */
    public void retry(View view) {
        //問題数カウントの初期化
        this.initializeState();

        // CSVからクイズを読み込む
        questionList.clear();
        CsvParser parser = new CsvParser();
        Context context = getApplicationContext();
        parser.createQuizList(context, questionList, level);

        //一問目の生成
        this.makeQuestion();
        for (int i = 0; i <= 3; i++) {
            button[i].setVisibility(View.VISIBLE);
            button[i].setEnabled(true);
        }
        retryButton.setVisibility(View.INVISIBLE);
    }

    /**
     * 出題開始前の初期化処理
     */
    public void initializeState() {
        //現在問題数＝１問目
        questionNum = 1;

        //正答数の初期化
        rightAnsNum = 0;
    }

}
