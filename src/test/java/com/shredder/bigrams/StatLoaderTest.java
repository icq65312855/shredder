package com.shredder.bigrams;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatLoaderTest {

	private StatLoader mStatLoader;
	private BaseStat mBaseStat;
	
	@Before
	public void setUp() throws Exception {
		mStatLoader = new StatLoader();
		mBaseStat = BaseStat.getInstance();
	}

	@Test
	public void testloadGraph() {
		// |  TH,100272945963  |
		// |  HE,86697336727   |
		// |  IN,68595215308   |
		// |  ER,57754162106   |
 		final String FirstCol = "[va,   , il,  y, pe]";
		mStatLoader.loadStat(mBaseStat, "src/main/resources/ngrams2.txt");
		assertEquals(100272945963L, mBaseStat.getStat("TH"));
		assertEquals(68595215308L, mBaseStat.getStat("in"));
	}


}
