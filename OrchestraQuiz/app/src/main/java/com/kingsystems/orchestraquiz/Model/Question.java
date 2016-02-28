package com.kingsystems.orchestraquiz.Model;

/**
 * 問題クラス
 */
public class Question {
    //音楽記号
    private String symbol;

    //音楽記号の意味　選択肢として使う
    private String[] meaning = new String[4];


    public Question() {
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setMeaning(String meaning, int index) {
        if (index < this.meaning.length) {
            this.meaning[index] = meaning;
        } else {
            //エラー処理
            System.out.println("存在しないindexへのset");
        }
    }

    public String getMeaning(int index) {
        if (index < this.meaning.length) {
            return this.meaning[index];
        } else {
            //エラー時処理
            System.out.println("存在しないindexからのget");
            return null;
        }
    }

}
