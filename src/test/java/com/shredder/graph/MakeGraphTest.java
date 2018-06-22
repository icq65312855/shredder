package com.shredder.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MakeGraphTest {

	private MakeGraph mMakeGraph;

	@Before
	public void setUp() throws Exception {
		mMakeGraph = new MakeGraph();
	}

	@SuppressWarnings("static-access")
	@Test
	public void testMakeGraph() {
		try {
		    mMakeGraph.makeGraph("src/main/resources/Big.txt", "src/main/resources/Big_New.txt");
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertTrue(true);
	}

}
