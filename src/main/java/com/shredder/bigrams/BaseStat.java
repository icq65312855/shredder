package com.shredder.bigrams;



public class BaseStat implements IStat {
	private final static int CHAR_COUNT = 26;
	private final static int STAT_SIZE = CHAR_COUNT * CHAR_COUNT;

	private final static char FirstCharacter = 'a';
	private final static int Acsii_Shift = (int) FirstCharacter;

	private long[] mStat;
	
	public BaseStat() {
		mStat = new long[STAT_SIZE];
		resetStat();
	}
	
	private int getBigramIndex(String bigram) {
		String lbigram = bigram.toLowerCase();
		int a0 = (int)lbigram.charAt(0) - Acsii_Shift;
		int a1 = (int)lbigram.charAt(1) - Acsii_Shift;
		if (0 <= a0 && a0 < CHAR_COUNT &&
			0 <= a1 && a1 < CHAR_COUNT) {
			return a0 * CHAR_COUNT + a1;
		} else {
			return -1;
		}
	}

	public void addStat(String bigram, long stat) {
		int index = getBigramIndex(bigram);
		if (index == -1) {
			System.err.println("ERROR addStat bigram = " + bigram);		
		} else {
			mStat[index] = stat;
		}
	}

	public void resetStat() {
		for(int i = 0; i < STAT_SIZE; i++) {
			mStat[i] = 0L;
		}
	}

	public void loadingComplete() {
		// TODO
	}
	
	public long getStat(String bigram) {
		int index = getBigramIndex(bigram);
		if (index == -1) {
//			System.err.println("ERROR getStat bigram = " + bigram);
			return -1L;		
		} else {
			return mStat[index];
		}
	}

}
