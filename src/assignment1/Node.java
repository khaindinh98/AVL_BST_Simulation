/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.ArrayList;

/**
 *
 * @author Sub4sa
 */
public class Node{

    public Information inf;
    public Integer height, size;
    public Node left, right, parent;
    //public Node(){}

    public Node(Information inf, Integer height, Integer size, Node left, Node right, Node parent) {
        this.inf = inf;
        this.height = height;
        this.size = size;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public String toString() {
        return "" + inf.ID;
    }

    public static void toArray(Node curr, ArrayList<Information> array) {
        if (curr != null) {
            toArray(curr.left, array);
            array.add(curr.inf);
            toArray(curr.right, array);
        }
    }
}
