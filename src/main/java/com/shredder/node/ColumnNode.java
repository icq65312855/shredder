package com.shredder.node;

import com.shredder.edge.IEdge;

import java.util.ArrayList;
import java.util.HashSet;

public class ColumnNode implements INode {

    private ArrayList<Node> nodes = new ArrayList<Node>();

    private HashSet<IEdge> edges = new HashSet();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
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
    public String toString() {
        return nodes.toString();
    }
}
