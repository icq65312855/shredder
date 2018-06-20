package com.shredder.spelling;

import com.shredder.node.DocumentNode;

import java.util.ArrayList;

public class Document {
    ArrayList<String> rows;

    public Document(DocumentNode nodes) {
        this.rows = getRows(nodes);
    }

    private ArrayList<String> getRows(DocumentNode nodes) {
        //TODO: transform column nodes to rows
        return new ArrayList<String>();
    }

    /**
     * check document for original words from the dictionary
     * @return true if document contains only original words or part of words
     */
    public boolean isValid() {
        //TODO:
        return true;
    }

    public void printDocument() {
        //TODO:
    }
}
