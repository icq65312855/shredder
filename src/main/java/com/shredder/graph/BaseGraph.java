package com.shredder.graph;

import com.shredder.edge.IEdge;
import com.shredder.node.ColumnNode;
import com.shredder.node.DocumentNode;
import com.shredder.node.INode;
import com.shredder.node.Node;
import com.shredder.spelling.Document;

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
        return new ArrayList<>(columns);
    }

    public INode restoreDocument() {

        ArrayList<ColumnNode> firstColumns = getFirstColumns();

        INode docNode = null;

        while (!firstColumns.isEmpty()) {

            ColumnNode firstColumn = firstColumns.remove(0);

            DocumentNode rootNode = new DocumentNode(firstColumn);

            rootNode.fillEdges(columns);

            ArrayList<IEdge> edges = new ArrayList<>();

            edges.addAll(rootNode.getSortedEdges());

            while (!edges.isEmpty()) {

                docNode = edges.remove(0).getEnd();

                if (docNode.isValid()) {
                    if (docNode.fillEdges(columns)) {
                        edges.addAll(0, docNode.getSortedEdges());
                    } else {
                        break;
                    }
                }

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
