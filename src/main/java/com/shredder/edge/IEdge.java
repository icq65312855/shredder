package com.shredder.edge;

import com.shredder.node.INode;

public interface IEdge {
   INode getStart();

   INode getEnd();

   String getBigram(int index);

   void setVolume(long volume);

   long getVolume();
}
