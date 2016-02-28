package com.kingsystems.orchestraquiz.Model;

/**
 * 音楽記号クラス
 * symbol : 音楽記号
 * meaning : 音楽記号の意味
 */
public class MusicalSymbol {
    private String symbol;
    private String meaning;

    public MusicalSymbol() {
    }

    public MusicalSymbol(String symbol, String meaning) {
        this.symbol = symbol;
        this.meaning = meaning;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return this.meaning;
    }
}
