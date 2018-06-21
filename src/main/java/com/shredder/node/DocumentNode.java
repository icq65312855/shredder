package com.shredder.node;

import com.shredder.edge.Edge;
import com.shredder.edge.IEdge;
import com.shredder.spelling.Document;

import java.util.ArrayList;
import java.util.HashSet;

public class DocumentNode implements INode {

    private ArrayList<ColumnNode> nodes;

    private HashSet<IEdge> edges = new HashSet();

    private Document document = null;

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

    public ArrayList<ColumnNode> getNodes() {
        return nodes;
    }

    @Override
    public ArrayList<IEdge> getSortedEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public boolean isValid() {
        if (document == null) {
            document = new Document(this);
        }

        return document.isValid();
    }

    @Override
    public void print() {
        document.printDocument();
    }

    public boolean fillEdges(ArrayList<ColumnNode> columnNodes) {
        ArrayList<ColumnNode> columns = new ArrayList<>(columnNodes);

        boolean find = false;

        for (ColumnNode col : columns) {
            if (nodes.contains(col)) { //TODO: use hashset
                continue;
            }
            find = true;
            DocumentNode docNode = new DocumentNode(nodes);
            docNode.addColumn(col);

            edges.add(new Edge(this, docNode));
        }

        return find;
    }

    @Override
    public int size() {
        return nodes.size();
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
