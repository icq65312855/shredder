package com.shredder.graph;

import com.shredder.edge.IEdge;
import com.shredder.node.ColumnNode;
import com.shredder.node.DocumentNode;
import com.shredder.node.INode;
import com.shredder.node.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class BaseGraph implements IGraph {
    private ArrayList<ColumnNode> columns = new ArrayList<>();

    private HashSet<String> words = new HashSet();

    @Override
    public void addNode(String letters, int col) {
        Node node = new Node(letters);
        ColumnNode colNode;

        if (col < columns.size()) {
            colNode = columns.get(col);
        } else {
            colNode = new ColumnNode();
            columns.add(col, colNode);
        }
        colNode.addNode(node);
    }

    @Override
    public int size() {
        return columns.size();
    }

    private List<ColumnNode> getFirstColumns() {
        //TODO: add volume for sorting
        List<ColumnNode> foundColumns = new ArrayList<>(columns);

        ArrayList<Character> specialSymbols = new ArrayList<>();
        specialSymbols.add('!');
        specialSymbols.add(';');
        specialSymbols.add(')');
        specialSymbols.add('?');
        specialSymbols.add('}');
        specialSymbols.add(']');
        specialSymbols.add(':');
        specialSymbols.add('.');
        specialSymbols.add(',');

        for (ColumnNode col : columns) {
            int size = col.getNodes().size();
            int volume = 0;

            for (int i = 0; i < size; i++) {
                char letter1 = col.getLetter(i, 0);
                char letter2 = col.getLetter(i, 1);

                if (letter1 == 32) {
                    letter1 = letter2;
                }

                if (Character.isLetterOrDigit(letter1)) {
                    volume++;
                }

                if (Character.isUpperCase(letter1)) {
                    volume++;
                }

                if (specialSymbols.contains(letter1)) {
                    volume = 0;
                    break;
                }
            }

            col.setVolume(volume);
        }

        Collections.sort(foundColumns);
        Collections.reverse(foundColumns);

        return foundColumns;
    }

    public INode restoreDocument() {

        List<ColumnNode> firstColumns = getFirstColumns();

        INode docNode = null;

        boolean isFound = false;

        while (!firstColumns.isEmpty()) {

            ColumnNode firstColumn = firstColumns.remove(0);

            DocumentNode rootNode = new DocumentNode(firstColumn);

            rootNode.fillEdges(columns);

            ArrayList<IEdge> edges = new ArrayList<>(rootNode.getSortedEdges());

            while (!edges.isEmpty()) {

                IEdge edge = edges.remove(0);
                docNode = edge.getEnd();

                if (docNode.isValid(words)) {
                    if (docNode.fillEdges(columns)) {
                        edges.addAll(0, docNode.getSortedEdges());
                    } else {
                        isFound = true;
                    }
                } else {
                    edge.getStart().removeEdge(edge);
                }

                if (isFound) {
                    break;
                }

            }

            if (isFound) {
                break;
            }

        }

        if (isFound) System.out.println("*************** RESTORED ****************");
        return docNode;

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (ColumnNode node : columns) {
            str.append(node.toString()).append(" \n");
        }

        return str.toString();
    }
}
