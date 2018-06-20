package com.shredder.node;

import com.shredder.edge.Edge;
import com.shredder.edge.IEdge;
import com.shredder.spelling.Document;

import java.util.ArrayList;
import java.util.HashSet;

public class DocumentNode implements INode {

    private ArrayList<ColumnNode> nodes;

    private HashSet<IEdge> edges = new HashSet();

    private Document document;

    public DocumentNode(ArrayList<ColumnNode> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }

    public DocumentNode(ColumnNode node) {
        this.nodes = new ArrayList<>();
        addColumn(node);
    }

    public void addColumn(ColumnNode node) {
        if (!this.nodes.contains(node)) {
            this.nodes.add(node);
        }
    }

    public void addEdge(DocumentNode node) {
        IEdge edge = new Edge(this, node);
        edges.add(edge);
    }

    @Override
    public ArrayList<IEdge> getSortedEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public boolean isValid() {
        return document.isValid();
    }

    @Override
    public void print() {
        document.printDocument();
    }

    public void fillEdges(ArrayList<ColumnNode> columnNodes) {
        ArrayList<ColumnNode> columns = new ArrayList<>(columnNodes);
        columnNodes.remove(nodes);

        for (ColumnNode col : columns) {
            DocumentNode docNode = new DocumentNode(nodes);
            docNode.addColumn(col);

            edges.add(new Edge(this, docNode));
            docNode.fillEdges(columnNodes);
        }
    }
}
