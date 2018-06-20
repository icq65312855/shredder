package com.shredder.spelling;

import java.util.HashSet;

public class BaseDictionary implements Dictionary {

    HashSet<String> words = new HashSet<>();

    private static BaseDictionary ourInstance = new BaseDictionary();

    public static BaseDictionary getInstance() {
        return ourInstance;
    }

    private BaseDictionary() {
    }

    @Override
    public boolean addWord(String word) {
        return false;
    }

    @Override
    public boolean isWord(String s) {
        return false;
    }

    @Override
    public int size() {
        return words.size();
    }
}
