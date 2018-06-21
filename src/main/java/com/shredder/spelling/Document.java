package com.shredder.spelling;

import com.shredder.node.ColumnNode;
import com.shredder.node.DocumentNode;
import com.shredder.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    ArrayList<String> rows;
    ArrayList<String> originalRows;

    public Document(DocumentNode nodes) {
        this.rows = getRows(nodes);
        this.originalRows = new ArrayList<>(this.rows);
    }

    /**
     * transform column nodes to rows
     * @param nodes
     * @return list of rows
     */
    private ArrayList<String> getRows(DocumentNode nodes) {

        ArrayList<String> listRows = new ArrayList<>();

        for (ColumnNode colNode : nodes.getNodes()) {

            int row = 0;

            for (Node node : colNode.getNodes()) {

                if (listRows.size() <= row) {
                    listRows.add(node.getLetters());
                } else {
                    String str = listRows.get(row) + node.getLetters();
                    listRows.set(row, str);
                }

                row++;

            }
        }

        return listRows;
    }

    /** Returns the tokens that match the regex pattern from the document
     * text string.
     * @param pattern A regular expression string specifying the
     *   token pattern desired
     * @return A List of tokens from the document text that match the regex
     *   pattern
     */
    protected List<String> getTokens(String text, String pattern)
    {
        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }

    /**
     * check document for original words from the dictionary
     * @return true if document contains only original words or part of words
     */
    public boolean isValid() {

        int index = 0;

        for (String str : rows) {

            List<String> wordsList = getTokens(str, "[a-zA-Z]+");
            String lastWord = "";
            boolean isWord = true;

            for (String word : wordsList) {
                if (!isWord || DictionaryTrie.getInstance().findWord(word) == null) {
                    return false;
                }
                isWord = DictionaryTrie.getInstance().isWord(word);
                lastWord = word;
            }

            rows.set(index, lastWord);

            index++;
        }

        return true;
    }

    public void printDocument() {
        for (String str : originalRows) {
            System.out.println(str);
        }
    }
}
