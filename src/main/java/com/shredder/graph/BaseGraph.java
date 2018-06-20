package com.shredder.graph;

import com.shredder.node.ColumnNode;
import com.shredder.node.DocumentNode;
import com.shredder.spelling.Document;

import java.util.ArrayList;

public class BaseGraph implements IGraph {
    ArrayList<ColumnNode> columns;

    @Override
    public void addNode(String letters, int col) {

    }

    public void addNode(ColumnNode node) {
        columns.add(node);
    }

    public void restore() {

        ArrayList<ColumnNode> firstColumns = new ArrayList<ColumnNode>(columns);

        Document doc = null;

        while (!firstColumns.isEmpty()) {

            ColumnNode firstColumn = firstColumns.remove(0);

            DocumentNode rootNode = new DocumentNode(firstColumn);

            ArrayList<ColumnNode> otherColumns = new ArrayList<>(columns);
            otherColumns.remove(firstColumn);

            doc = findDocument(rootNode, otherColumns);

            if (doc != null) {
                break;
            }

        }

        if (doc != null) {
            doc.printDocument();
        }

    }

    private Document findDocument(DocumentNode node, ArrayList<ColumnNode> columnNodes) {

        Document doc = new Document(node);

        if (!doc.checkWords()) {
            return null;
        }

        while (!columnNodes.isEmpty()) {

        }

        return null;
    }
}
