package com.shredder.graph;

import com.shredder.edge.IEdge;
import com.shredder.node.ColumnNode;
import com.shredder.node.DocumentNode;
import com.shredder.node.INode;
import com.shredder.node.Node;

import java.util.ArrayList;

public class BaseGraph implements IGraph {
    ArrayList<ColumnNode> columns = new ArrayList<>();

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

    public void addNode(ColumnNode node) {
        columns.add(node);
    }

    private ArrayList<ColumnNode> getFirstColumns() {
        ArrayList<ColumnNode> foundColumns = new ArrayList<>();

        for (ColumnNode col : columns) {
            int size = col.getNodes().size();
            boolean isValid = true;

            for (int i = 0; i < size; i++) {
                char letter1 = col.getLetter(i, 0);
                char letter2 = col.getLetter(i, 1);
                if (!Character.isLetterOrDigit(letter1) && (letter1 != letter2 && letter2 != 32)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                foundColumns.add(col);
            }
        }

        return foundColumns;
    }

    public INode restoreDocument() {

        ArrayList<ColumnNode> firstColumns = getFirstColumns();

        INode docNode = null;

        boolean isFound = false;

        int testCount = 0;
        int testCount2 = 0;

        while (!firstColumns.isEmpty()) {

            ColumnNode firstColumn = firstColumns.remove(0);

            DocumentNode rootNode = new DocumentNode(firstColumn);

            rootNode.fillEdges(columns);

            ArrayList<IEdge> edges = new ArrayList<>();

            edges.addAll(rootNode.getSortedEdges());

            while (!edges.isEmpty()) {

                docNode = edges.remove(0).getEnd();
                testCount2++;

                if (docNode.isValid()) {
                    if (testCount < docNode.size()) {
                        testCount = docNode.size();
                        System.out.println("====Reach size "+testCount+" from "+columns.size());
                        System.out.println("was iterated: "+testCount2);
                        System.out.println("edges.size: "+edges.size());
                    }
                    if (docNode.fillEdges(columns)) {
                        edges.addAll(0, docNode.getSortedEdges());
                    } else {
                        isFound = true;
                    }
                }

                if (isFound) {
                    break;
                }

            }


            if (isFound) {
                break;
            }

        }

        return docNode;

    }

    @Override
    public String toString() {
        String str = "";

        for (ColumnNode node : columns) {
            str = str + node.toString()+ " \n";
        }

        return str;
    }
}
