package com.shredder.node;

import com.shredder.edge.IEdge;

import java.util.ArrayList;

public interface INode {

    ArrayList<IEdge> getSortedEdges();

    boolean isValid();

    void print();

    boolean fillEdges(ArrayList<ColumnNode> columnNodes);

    int size();
}
