package com.shredder.node;

import com.shredder.edge.IEdge;

import java.util.ArrayList;
import java.util.HashSet;

public class ContainerNode implements INode {
    private ArrayList<ColumnNode> nodes = new ArrayList<ColumnNode>();
    private HashSet<IEdge> edges = new HashSet();

    public boolean checkColumn() {
        return true;
    }
}
