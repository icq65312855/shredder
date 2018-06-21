package com.shredder.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class GraphLoaderTest {

	private GraphLoader mGraphLoader;
	private BaseGraph mBaseGraph;
	
	@Before
	public void setUp() throws Exception {
		mGraphLoader = new GraphLoader();
		mBaseGraph = new BaseGraph();
	}

	@Test
	public void testloadGraph() {
		// |vaJa software engineer              |
		// |                                    |
		// |ilSkls & Requirements               |
		// | y3+ears of extensive experience    |
		// |peExrience with relational databesas|
		final String FirstCol = "[va,   , il,  y, pe]";
		mGraphLoader.loadGraph(mBaseGraph, "src/main/resources/TestDocument.txt");
		assertEquals(FirstCol, mBaseGraph.toString().substring(0, FirstCol.length()));
	}

}
