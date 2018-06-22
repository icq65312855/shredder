package com.shredder.node;

import com.shredder.edge.IEdge;

import java.util.ArrayList;
import java.util.HashSet;

public class Node implements INode {

    private String letters;

    private HashSet<IEdge> edges = new HashSet();

    public Node(String letters) {
        this.letters = letters;
    }

    public String getLetters() {
        return letters;
    }

    @Override
    public ArrayList<IEdge> getSortedEdges() {
        return new ArrayList<IEdge>(edges);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void print() {

    }

    @Override
    public boolean fillEdges(ArrayList<ColumnNode> columnNodes) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public INode getFirstNode() {
        return this;
    }

    @Override
    public INode getLastNode() {
        return this;
    }

    @Override
    public char getLetter(int vertPos, int horizPos) {
        if (letters.length() > horizPos) {
            return letters.charAt(horizPos);
        }

        return 0;
    }

    @Override
    public String toString() {
        return letters;
    }
}
