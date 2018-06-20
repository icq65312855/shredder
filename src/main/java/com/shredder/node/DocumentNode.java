package com.shredder.node;

import com.shredder.edge.Edge;
import com.shredder.edge.IEdge;

import java.util.ArrayList;
import java.util.HashSet;

public class DocumentNode implements INode {
    private ArrayList<ColumnNode> nodes;
    private HashSet<IEdge> edges = new HashSet();

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
}
