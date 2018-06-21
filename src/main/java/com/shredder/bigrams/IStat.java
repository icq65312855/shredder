package com.shredder.bigrams;

public interface IStat {

    void addStat(String bigram, long stat);

    void resetStat();

    void loadingComplete();
}
