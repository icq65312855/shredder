package com.shredder.edge;

import com.shredder.node.INode;

public class Edge implements IEdge {
    INode start;
    INode end;

    public Edge(INode start, INode end) {
        this.start = start;
        this.end = end;
    }
}
