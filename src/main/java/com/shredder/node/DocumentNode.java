package com.shredder.node;

import com.shredder.bigrams.BaseStat;
import com.shredder.edge.Edge;
import com.shredder.edge.IEdge;
import com.shredder.spelling.DictionaryTrie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentNode implements INode {

    private ArrayList<ColumnNode> nodes;

    private ArrayList<String> rows = new ArrayList<>();

    private HashSet<IEdge> edges = new HashSet();

    public DocumentNode(DocumentNode docNode) {
        this.nodes = new ArrayList<>(docNode.nodes);
        this.rows = new ArrayList<>(docNode.rows);
    }

    public DocumentNode(ColumnNode node) {
        this.nodes = new ArrayList<>();
        addColumn(node);
    }

    public void addColumn(ColumnNode node) {
        if (!this.nodes.contains(node)) {
            this.nodes.add(node);
            addRows(node);
        }
    }

    private void addRows(ColumnNode node) {
        ArrayList<String> newRows = new ArrayList<>();
        int index = 0;

        for (Node n : node.getNodes()) {
            String row = "";
            if (index < rows.size()) {
                row = rows.get(index);
            }
            row += n.getLetters();
            newRows.add(row);
            index++;
        }

        this.rows = newRows;
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
    @Override
    public boolean isValid(HashSet<String> words) {
        for (String str : rows) {

            List<String> wordsList = getTokens(str, "[a-zA-Z]+");
            int size = wordsList.size();

            if (size == 0) {
                continue;
            }

            String lastWord = "";
            boolean isWord = true;

            for (String word : wordsList) {
                if (!isWord) {
                    return false;
                }
                if (words.contains(word)) {
                    isWord = true;
                    lastWord = word;
                    continue;
                }
                if (DictionaryTrie.getInstance().findWord(word) == null) {
                    return false;
                }
                isWord = DictionaryTrie.getInstance().isWord(word);
                if (isWord) {
                    words.add(word);
                }
                lastWord = word;
            }

            boolean isOnlyOneWord = str.length() == lastWord.length();

            if (!isOnlyOneWord
                    && !str.substring(str.length() - lastWord.length(), str.length()).equals(lastWord)
                    && !DictionaryTrie.getInstance().isWord(lastWord)) {
                return false;
            }


        }

        return true;
    }

    /**
     * transform column nodes to rows
     * @return list of rows
     */
    private ArrayList<String> getRows() {

        ArrayList<String> listRows = new ArrayList<>();

        for (ColumnNode colNode : nodes) {

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

    public void print() {
        ArrayList<String> originalRows = getRows();

        for (String str : originalRows) {
            System.out.println(str);
        }
    }

    public void addEdge(DocumentNode node) {
        IEdge edge = new Edge(this, node);
        edges.add(edge);
    }

    public void removeEdge(IEdge edge) {
        edges.remove(edge);
    }

    @Override
    public List getSortedEdges() {
        List sortedEdges = new ArrayList<>(edges);

        for (IEdge edge : edges) {

            long stat = 0;
            int size = edge.getStart().size();

            for (int i = 0; i < size; i++) {

                String bigram = edge.getBigram(i);
                stat += BaseStat.getInstance().getStat(bigram);
            }

            edge.setVolume(stat);
        }

        Collections.sort(sortedEdges);
        Collections.reverse(sortedEdges);

        return sortedEdges;
    }

    public boolean fillEdges(ArrayList<ColumnNode> columnNodes) {
        if (columnNodes.size() == nodes.size()) {
            return false;
        }

        for (ColumnNode col : columnNodes) {
            if (nodes.contains(col)) { //TODO: use hashset
                continue;
            }
            DocumentNode docNode = new DocumentNode(this);
            docNode.addColumn(col);

            addEdge(docNode);
        }

        return true;
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public INode getFirstNode() {
        if (nodes.size() > 0) {
            return nodes.get(0);
        }

        return null;
    }

    @Override
    public INode getLastNode() {
        if (nodes.size() > 0) {
            return nodes.get(nodes.size()-1);
        }

        return null;
    }

    @Override
    public char getLetter(int vertPos, int horizPos) {
        return 0;
    }

    @Override
    public String toString() {

        String str = "";

        for (ColumnNode node : nodes) {
            str = str + node.toString()+ " \n";
        }

        return str;
    }
}
