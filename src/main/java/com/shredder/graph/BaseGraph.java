package com.shredder.graph;

import com.shredder.edge.IEdge;
import com.shredder.node.ColumnNode;
import com.shredder.node.DocumentNode;
import com.shredder.node.INode;
import com.shredder.spelling.Document;

import java.util.ArrayList;

public class BaseGraph implements IGraph {
    ArrayList<ColumnNode> columns;

    @Override
    public void addNode(String letters, int col) {

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

    private ArrayList<ColumnNode> getNextColumns(ArrayList<ColumnNode> excludeColumns) {
        ArrayList<ColumnNode> otherColumns = new ArrayList<>(columns);
        otherColumns.remove(excludeColumns);

        return otherColumns;
    }

    private ArrayList<ColumnNode> getNextColumns(ColumnNode excludeColumn) {
        ArrayList<ColumnNode> excludeColumns = new ArrayList<>();
        excludeColumns.add(excludeColumn);

        return getNextColumns(excludeColumns);
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
                    edges.addAll(docNode.getSortedEdges());
                }

            }

        }

        return docNode;

    }
}
