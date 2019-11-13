/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst_avl;

/**
 *
 * @author Sub4sa
 */
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreeAdvanced extends Tree {

    String type;

    public TreeAdvanced(String type) {
        super();
        this.type = type;
    }

    //------------------------
    public void createFileTree(String input) throws IOException {
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
        String line = "";
        while ((line = fr.readLine()) != null) {
            int ID = 0;
            String fullName = null;
            String birthDay = null;
            double avgScore = 0.0;
            int noOfCredits = 0;
            if (!line.equals("")) {

                ID = Integer.parseInt(line);
                fullName = (line = fr.readLine());
                try {
                    birthDay = (line = fr.readLine());
                    SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = s.parse(birthDay);
                    birthDay = s.format(date);
                } catch (Exception e) {
                    System.out.println("Khong co BirthDay");
                }
                try {
                    avgScore = Double.parseDouble((line = fr.readLine()));
                } catch (Exception e) {
                    System.out.println("Khong co Average");
                }
                try {
                    noOfCredits = Integer.parseInt((line = fr.readLine()));
                } catch (Exception e) {
                    System.out.println("Khong co noOfCredits ");
                }
                try {
                    Information inf = new Information(ID, fullName, birthDay, avgScore, noOfCredits);
                    System.out.println(inf);
                    addNode(inf);
                } catch (Exception e) {
                    System.out.println("Loi tao node");
                }

            }
        }
        fr.close();
    }

    public void createSkewedLeft() {
        createTreeRandom();
        ArrayList<Information> array = new ArrayList<Information>();
        Node.toArray(root, array);
        Collections.sort(array);
        Collections.reverse(array);
        root = null;
        addAllNode(array);
    }

    public void createSkewedRight() {
        createTreeRandom();
        ArrayList<Information> array = new ArrayList<Information>();
        Node.toArray(root, array);
        Collections.sort(array);
        root = null;
        addAllNode(array);
    }

    public void createTreeRandom() {
        root = null;
        Random random = new Random();
        //int [] num={0,1,2,3,4,5,6,7,8,9};
        String[] name = {"Khai", "Bo", "Huy", "Phi", "Phat", "Phong"};
        String[] lastName = {"Nguyen Van", "Nguyen Dinh", "Thach Thanh", "Nguyen Truong", "Nguyen Ky Viet", "Nguyen Hoang"};
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String birthDay = s.format(date);
        for (int i = 1; i <= random.nextInt(15) + 1; i++) {
            int ID = random.nextInt(899) + 100;
            String fullName = lastName[random.nextInt(lastName.length)] + " " + name[random.nextInt(name.length)];
            double temp = random.nextDouble();
            while (temp < 1.0) {
                temp *= 10;
            }
            double avgScore = temp;
            int noOfCredits = random.nextInt(1000);
            try {
                Information inf = new Information(ID, fullName, birthDay, avgScore, noOfCredits);
                addNode(inf);

            } catch (Exception ex) {
                Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Information predecessor(Integer key) {
        Node curr=get(key);
        return predecessor(curr);
    }
    public Information successor(Integer key) {
        Node curr=get(key);
        return successor(curr);
    }
    private Information predecessor(Node curr) {
        if (curr.left != null)
            return findMax(curr.left).inf;
        Node p = curr.parent;
        while (p != null && curr == p.left) {
            curr = p;
            p = p.parent;
        }
        return p.inf;
    }
    private Information successor(Node curr) {
        if (curr.right != null)
            return findMin(curr.right).inf;
        Node p = curr.parent;
        while (p != null && curr == p.right) {
            curr = p;
            p = p.parent;
        }
        return p.inf;
    }

    public void update(int ID, String fullName, String birthDay, double avgScore, int noOfCredits, int k) {
        Node x = get(root, ID);
        if (k == 1) {
            x.inf.fullName = fullName;
        }
        if (k == 2) {
            x.inf.birthDay = birthDay;
        }
        if (k == 3) {
            x.inf.avgScore = avgScore;
        }
        if (k == 4) {
            x.inf.noOfCredits = noOfCredits;
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addNode(Information inf) {
        if (type.equals("AVL")) {
            root = addNodeAVL(root, inf);
        }
        if (type.equals("BST")) {
            root = addNodeBST(root, inf);
        }
    }

    public void addAllNode(ArrayList<Information> arrayInf) {
        for (Information x : arrayInf) {
            if (type.equals("AVL")) {
                root = addNodeAVL(root, x);
            }
            if (type.equals("BST")) {
                root = addNodeBST(root, x);
            }
        }
    }

    public void delete(Integer key) {
        root = delete(root, key);

    }

    private Node addNodeBST(Node curr, Information inf) {
        //boolean temp=inf.fullName.equalsIgnoreCase(curr.inf.fullName);
        if (curr == null) {
            return new Node(inf, 1, 1, null, null, null);
        }
        if (inf.ID > curr.inf.ID) {
            curr.right = addNodeBST(curr.right, inf);
            curr.right.parent = curr;
        } else if (inf.ID < curr.inf.ID) {
            curr.left = addNodeBST(curr.left, inf);
            curr.left.parent = curr;
        }
        curr.height = height(curr);
        curr.size = size(curr);
        //curr = updateParent(curr);
        return curr;
    }

    private Node addNodeAVL(Node curr, Information inf) {
        //boolean temp=inf.fullName.equalsIgnoreCase(curr.inf.fullName);
        if (curr == null) {
            return new Node(inf, 1, 1, null, null, null);
        }
        if (inf.ID > curr.inf.ID) {
            curr.right = addNodeAVL(curr.right, inf);
            curr.right.parent = curr;
        } else if (inf.ID < curr.inf.ID) {
            curr.left = addNodeAVL(curr.left, inf);
            curr.left.parent = curr;
        }
        curr.height = height(curr);
        curr.size = size(curr);
        curr = balance(curr);
        //curr = updateParent(curr);
        return curr;
    }

    private int checkBalance(Node curr) {
        return height(curr.left) - height(curr.right);
    }

    private Node balance(Node curr) {
        if (checkBalance(curr) >= 2) {
            if (checkBalance(curr.left) <= -1) {
                curr.left = rotateLeft(curr.left);
            }
            curr = rotateRight(curr);
        } else if (checkBalance(curr) <= -2) {
            if (checkBalance(curr.right) >= 1) {
                curr.right = rotateRight(curr.right);
            }
            curr = rotateLeft(curr);
        }
        return curr;
    }

    private Node rotateLeft(Node curr) {
        Node child = curr.right;
        curr.right = child.left;
        curr.height = height(curr);
        curr.size = size(curr);

        child.left = curr;
        child.height = height(child);
        child.size = size(child);
        child.parent=curr.parent;
        curr.parent=child;
        return child;
    }

    private Node rotateRight(Node curr) {
        Node child = curr.left;
        curr.left = child.right;
        curr.height = height(curr);
        curr.size = size(curr);

        child.right = curr;
        child.height = height(child);
        child.size = size(child);
        //child = updateParent(child);
        child.parent=curr.parent;
        curr.parent=child;
        return child;
    }

    private Node deleteMin(Node curr) {
        if (curr.left == null) {
            return curr.right;
        }
        curr.left = deleteMin(curr.left);
        if(curr.left!=null)
            curr.left.parent=curr;
        if (type.equals("AVL")) {
            curr = balance(curr);
        }
        //curr = updateParent(curr);
        curr.height = height(curr);
        curr.size = size(curr);
        return curr;
    }

    private Node deleteMax(Node curr) {
        if (curr.right == null) {
            return curr.left;
        }
        curr.right = deleteMax(curr.right);
        if(curr.right!=null)
            curr.right.parent=curr;
        if (type.equals("AVL")) {
            curr = balance(curr);
        }
        //curr = updateParent(curr);
        curr.height = height(curr);
        curr.size = size(curr);
        return curr;
    }

    private Node delete(Node curr, Integer key) {
        if (curr == null) {
            return null;
        }
        if (key > curr.inf.ID) {
            curr.right = delete(curr.right, key);
            if(curr.right!=null)
                curr.right.parent=curr;
        } else if (key < curr.inf.ID) {
            curr.left = delete(curr.left, key);
            if(curr.left!=null)
                curr.left.parent=curr;
        } else {
            if (curr.left == null) {
                return curr.right;
            }
            if (curr.right == null) {
                return curr.left;
            }
            Node min = findMin(curr.right);
            if (min != null) {
                curr.inf = min.inf;
                curr.right = deleteMin(curr.right);
                if(curr.right!=null)
                    curr.right.parent=curr;
            } else {
                Node max = findMax(curr.left);
                curr.inf = min.inf;
                curr.left = deleteMax(curr.left);
                if(curr.left!=null)
                    curr.left.parent=curr;
            }
        }
        if (type.equals("AVL")) {
            curr = balance(curr);
        }
        //curr = updateParent(curr);
        curr.height = height(curr);
        curr.size = size(curr);
        return curr;
    }

    private Node get(Integer key) {
        return get(root,key);
    }
}
