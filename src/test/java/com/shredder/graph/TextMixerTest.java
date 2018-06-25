package com.shredder.graph;

import com.shredder.tools.TextMixer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TextMixerTest {

	private TextMixer mTextMixer;

	@Before
	public void setUp() throws Exception {
		mTextMixer = new TextMixer();
	}

	@SuppressWarnings("static-access")
	@Test
	public void testMakeGraph() {
		try {
		    mTextMixer.mixText("src/main/resources/Big3", "src/main/resources/Big3_Shredded.txt");
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertTrue(true);
	}

}
