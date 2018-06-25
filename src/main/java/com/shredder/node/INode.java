package com.shredder.node;

import com.shredder.edge.IEdge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface INode {

    List getSortedEdges();

    boolean isValid(HashSet<String> words);

    void print();

    boolean fillEdges(ArrayList<ColumnNode> columnNodes);

    int size();

    INode getFirstNode();

    INode getLastNode();

    char getLetter(int vertPos, int horizPos);

    void removeEdge(IEdge edge);
}
