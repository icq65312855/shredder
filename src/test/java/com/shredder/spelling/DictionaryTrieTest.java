package com.shredder.spelling;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DictionaryTrieTest {

	private DictionaryTrie mDictionaryTrie;

	@Before
	public void setUp() throws Exception {
		mDictionaryTrie = DictionaryTrie.getInstance();
		mDictionaryTrie.addWord("exist");
	}

	@Test
	public void testDictionaryTrieAddWordShouldReturnTrue() {
		assertTrue(mDictionaryTrie.addWord("added"));
	}

	@Test
	public void testDictionaryTrieSizeShouldChangeByAddWord() {
		int size = mDictionaryTrie.size();
		mDictionaryTrie.addWord("new");
		assertEquals(mDictionaryTrie.size(), size + 1);
	}

	@Test
	public void testDictionaryTrieIsWordShouldReturnTrue() {
		assertTrue(mDictionaryTrie.isWord("exist"));
	}

	// TrieNode findWord(String s);
	
}
