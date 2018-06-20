package com.shredder.node;

import com.shredder.edge.IEdge;

import java.util.ArrayList;
import java.util.HashSet;

public class ColumnNode implements INode {

    private ArrayList<Node> nodes = new ArrayList<Node>();

    private HashSet<IEdge> edges = new HashSet();

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
}
