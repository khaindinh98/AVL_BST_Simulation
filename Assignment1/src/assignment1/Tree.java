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
public class Tree {

    public Node root;

    public Integer height(Node curr) {
        if (curr != null) {
            return 1 + Math.max(height(curr.left), height(curr.right));
        }
        return 0;
    }

    public Integer size(Node curr) {
        if (curr != null) {
            return 1 + size(curr.left) + size(curr.right);
        }
        return 0;
    }
    
    public Information findID(Node curr, int input) {//fix lai nhieu lua chon tim kiem regular expression
        if (curr == null) {
            return null;
        }
        if (input > curr.inf.ID) {
            return findID(curr.right, input);
        } else if (input < curr.inf.ID) {
            return findID(curr.left, input);
        }
        return curr.inf;
    }

    public ArrayList<Information> findName(Node curr, String fullName, ArrayList<Information> array) {
        if (curr != null) {
            if (curr.inf.fullName.equalsIgnoreCase(fullName)) {
                array.add(curr.inf);
            }
            array = findName(curr.left, fullName, array);
            array = findName(curr.right, fullName, array);
        }
        return array;
    }

    public ArrayList<Information> findBirthDay(Node curr, String birthDay, ArrayList<Information> array) {
        if (curr != null) {
            if (curr.inf.birthDay.equalsIgnoreCase(birthDay)) {
                array.add(curr.inf);
            }
            array = findBirthDay(curr.left, birthDay, array);
            array = findBirthDay(curr.right, birthDay, array);
        }
        return array;
    }

    public ArrayList<Information> findAvgScore(Node curr, double avgScore, ArrayList<Information> array) {
        int x = Information.afterPoint(avgScore);
        if (curr != null) {
            if ((int) (curr.inf.avgScore * Math.pow(10, x)) == (int) (avgScore * Math.pow(10, x))) {
                array.add(curr.inf);
            }
            array = findAvgScore(curr.left, avgScore, array);
            array = findAvgScore(curr.right, avgScore, array);
        }
        return array;
    }

    public ArrayList<Information> findNoOfCredits(Node curr, int noOfCredits, ArrayList<Information> array) {
        if (curr != null) {
            if (curr.inf.noOfCredits == noOfCredits) {
                array.add(curr.inf);
            }
            array = findNoOfCredits(curr.left, noOfCredits, array);
            array = findNoOfCredits(curr.right, noOfCredits, array);
        }
        return array;
    }

    public void setInformation(int input, Information inf2) {
        Information inf1 = findID(root, input);
        if (inf1 != null) {
            inf1 = inf2;
        }
    }

    public Node findMax(Node curr) {
        if (curr.right != null) {
            return findMax(curr.right);
        }
        return curr;
    }

    public Node findMin(Node curr) {
        if (curr.left != null) {
            return findMin(curr.left);
        }
        return curr;
    }

    public Node get(Node curr, int input) {//fix lai nhieu lua chon tim kiem regular expression
        if (curr == null) {
            return null;
        }
        if (input > curr.inf.ID) {
            return get(curr.right, input);
        } else if (input < curr.inf.ID) {
            return get(curr.left, input);
        }
        return curr;
    }
    public void LNR(Node curr, ArrayList<Information>array){
        if(curr!=null){
            LNR(curr.left,array);
            array.add(curr.inf);
            LNR(curr.right,array);
        }
    }
    public void LRN(Node curr, ArrayList<Information>array){
        if(curr!=null){
            LRN(curr.left,array);
            LRN(curr.right,array);
            array.add(curr.inf);
        }
    }
    public void NLR(Node curr, ArrayList<Information>array){
        if(curr!=null){
            array.add(curr.inf);
            NLR(curr.left,array);
            NLR(curr.right,array);
        }
    }
    public void RNL(Node curr, ArrayList<Information>array){
        if(curr!=null){   
            RNL(curr.right,array);
            array.add(curr.inf);
            RNL(curr.left,array);
        }
    }
    public void NRL(Node curr, ArrayList<Information>array){
        if(curr!=null){
            array.add(curr.inf);
            NRL(curr.right,array);
            NRL(curr.left,array);
        }
    }
    public void RLN(Node curr, ArrayList<Information>array){
        if(curr!=null){
            RLN(curr.right,array);
            RLN(curr.left,array);
            array.add(curr.inf);
        }
    }
}
