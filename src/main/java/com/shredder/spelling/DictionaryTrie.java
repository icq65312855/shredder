package com.shredder.spelling;

public class DictionaryTrie implements  Dictionary {

    private TrieNode root;
    private int size;

    private static DictionaryTrie instance = new DictionaryTrie();

    public static DictionaryTrie getInstance() {
        return instance;
    }

    private DictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie. */
	public boolean addWord(String word)
	{
		TrieNode prevNode = this.root;
		for (Character c : word.toCharArray()) {
			c = Character.toLowerCase(c);
			TrieNode nextNode = prevNode.getChild(c);
			if (nextNode == null) {
				nextNode = prevNode.insert(c);
			}
			prevNode = nextNode;
		}
		
		if (!prevNode.endsWord()) {
			prevNode.setEndsWord(true);
			this.size += 1;
			return true;
		}
	    
		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		TrieNode prevNode = this.root;
		for (Character c : s.toCharArray()) {
			c = Character.toLowerCase(c);
			TrieNode nextNode = prevNode.getChild(c);
			if (nextNode == null) {
				return false;
			}
			prevNode = nextNode;
		}
		
		return prevNode.endsWord();
	}

	public TrieNode findWord(String s) 
	{
		TrieNode prevNode = this.root;
		for (Character c : s.toCharArray()) {
			c = Character.toLowerCase(c);
			TrieNode nextNode = prevNode.getChild(c);
			if (nextNode == null) {
				return null;
			}
			prevNode = nextNode;
		}
		
		return prevNode;
	}

}