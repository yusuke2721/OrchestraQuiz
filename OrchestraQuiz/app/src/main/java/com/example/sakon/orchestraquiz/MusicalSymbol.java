package com.example.sakon.orchestraquiz;

/**
 * 選択肢となる音楽記号のクラス
 * symbol : 音楽記号
 * meaning : 音楽記号の意味
 * Created by Kazuma on 2016/01/31.
 */
public class MusicalSymbol {
    private String symbol;
    private String meaning;

    MusicalSymbol () {
    }

    MusicalSymbol (String symbol, String meaning) {
        this.symbol = symbol;
        this.meaning = meaning;
    }

    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol () {
        return this.symbol;
    }

    public void setMeaning (String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning () {
        return this.meaning;
    }
}
