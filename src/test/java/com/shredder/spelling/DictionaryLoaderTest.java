package com.shredder.spelling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DictionaryLoaderTest {

	private DictionaryTrie mDictionaryTrie;
	private DictionaryLoader mDictionaryLoader;

	@Before
	public void setUp() throws Exception {
		mDictionaryTrie = DictionaryTrie.getInstance();
		mDictionaryLoader = new DictionaryLoader();
	}

	@Test
	public void testLoadDictionary() {
		// 100 'New' words
		int size = mDictionaryTrie.size();
		mDictionaryLoader.loadDictionary(mDictionaryTrie, "src/main/resources/Dictionary.txt", 100);
		assertEquals(mDictionaryTrie.size(), size + 100);

		// No 'New' words
		mDictionaryLoader.loadDictionary(mDictionaryTrie, "src/main/resources/Dictionary.txt");
		size = mDictionaryTrie.size();
		mDictionaryLoader.loadDictionary(mDictionaryTrie, "src/main/resources/Dictionary.txt");
		assertEquals(mDictionaryTrie.size(), size);
	}

}
