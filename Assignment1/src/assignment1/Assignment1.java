/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import javax.swing.JPanel;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Sub4sa
 */
class DisplayJLabel extends Component {

    public Information inf;
    public String text;
    private int x, y;

    public DisplayJLabel(String text, Information inf, int x, int y) {
        this.text = text;
        this.inf = inf;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        //g.setColor(Color.GREEN); //colors the window
        //g.fillOval(x-(getWidth()/2),y-(getHeight()/2), getWidth() - 1, getHeight() - 1);
        g.setColor(Color.RED);
        g.drawString(text, x - (getWidth() / 3), y + (getHeight() / 6));
    }

    public void paintFind(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x - (getWidth() / 2), y - (getHeight() / 2), getWidth() - 1, getHeight() - 1);
        g.setColor(Color.GREEN); //colors the window
        g.fillOval(x - (getWidth() / 2), y - (getHeight() / 2), getWidth() - 1, getHeight() - 1);
        g.setColor(Color.white);
        g.drawString(text, x - (getWidth() / 3), y + (getHeight() / 6));
    }

    public void paintDefault(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x - (getWidth() / 2), y - (getHeight() / 2), getWidth() - 1, getHeight() - 1);
        g.setColor(Color.GREEN); //colors the window
        g.drawOval(x - (getWidth() / 2), y - (getHeight() / 2), getWidth() - 1, getHeight() - 1);
        g.setColor(Color.BLACK);
        g.drawString(text, x - (getWidth() / 3), y + (getHeight() / 6));
    }

}

class DisplayJPanel extends JPanel {

    private TreeAdvanced tree;
    private JTextField text1, text2, text3, text4, text5;
    private ArrayList<DisplayJLabel> array;
    public boolean bol;
    private int width = 45, height = 45;
    Font font = new Font("Verdana", Font.BOLD, 14);

    public DisplayJPanel(TreeAdvanced tree, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5) {
        this.tree = tree;
        setLayout(null);
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.text5 = text5;
        bol = false;
        array = new ArrayList<DisplayJLabel>();
    }

    public void setTree(TreeAdvanced tree) {
        this.tree = tree;
        paintDefault();
    }

    public void paintFind(ArrayList res) {
        try {
            Graphics g = getGraphics();
            g.setFont(font);
            for (DisplayJLabel x : array) {
                if (res.contains(x.inf)) {
                    x.paintFind(g);
                }
            }
            Thread.sleep(2000);
            if (tree != null) {
                drawBranch(tree.root, g, this.getWidth() / 2, 100, 300, 100);
                drawNode(tree.root, g, this.getWidth() / 2, 100, 300, 100);
            }
        } catch (Exception e) {

        }
    }
    public void paintFind(Information res) {
        try {
            Graphics g = getGraphics();
            g.setFont(font);
            for (DisplayJLabel x : array) {
                if (x.inf.equals(res)) {
                    x.paintFind(g);
                }
            }
            Thread.sleep(2000);
            if (tree != null) {
                drawBranch(tree.root, g, this.getWidth() / 2, 100, 300, 100);
                drawNode(tree.root, g, this.getWidth() / 2, 100, 300, 100);
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        paintDefault();
    }
    
    public void paintDefault() {
        Graphics g = getGraphics();
        array.clear();
        removeAll();
        g.setColor(getBackground()); //colors the window
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(font);
        g.setColor(getForeground());
        if (tree != null) {
            drawBranch(tree.root, g, this.getWidth() / 2, 100, 300, 100);
            drawNode(tree.root, g, this.getWidth() / 2, 100, 300, 100);
        }
    }

    public void drawTraversal(ArrayList<Information> array) {
        try {
            Graphics g = getGraphics();
            g.setFont(font);
            for (Information x : array) {
                for (DisplayJLabel y : this.array) {
                    if (x.equals(y.inf)) {
                        y.paintFind(g);
                        Thread.sleep(500);
                    }
                }
            }
            Thread.sleep(2000);
            if (tree != null) {
                drawBranch(tree.root, g, this.getWidth() / 2, 100, 300, 100);
                drawNode(tree.root, g, this.getWidth() / 2, 100, 300, 100);
            }
        } catch (Exception e) {

        }
    }
    private void drawNode(Node curr, Graphics g, int x, int y, int heso1, int heso2) {

        if (curr != null) {
            g.setColor(Color.BLACK);
            String temp = "" + curr.inf.ID;
            DisplayJLabel j = new DisplayJLabel(temp, curr.inf, x, y);
            j.setBounds(x - width / 2, y - height / 2, width, height);
            j.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
                    if (bol == false) {
                        text1.setText("" + j.inf.ID);
                        text2.setText(j.inf.fullName);
                        text3.setText(j.inf.birthDay);
                        text4.setText("" + j.inf.avgScore);
                        text5.setText("" + j.inf.noOfCredits);
                    }
                    j.paint(g);
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                public void mouseExited(MouseEvent e) {
                    super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                    if (bol == false) {
                        text1.setText("");
                        text2.setText("");
                        text3.setText("");
                        text4.setText("");
                        text5.setText("");
                    }
                    j.paintDefault(g);
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                    bol = true;
                    text1.setText("" + j.inf.ID);
                    text2.setText(j.inf.fullName);
                    text3.setText(j.inf.birthDay);
                    text4.setText("" + j.inf.avgScore);
                    text5.setText("" + j.inf.noOfCredits);
                }
            });
            j.paintDefault(g);
            array.add(j);
            this.add(j);
            if (curr.left != null) {
                drawNode(curr.left, g, x - heso1, y + heso2, (heso1 / 2) >= 30 ? (heso1 / 2) : 30, (heso2 - 10) >= height ? (heso2 - 10) : height);
            }
            if (curr.right != null) {
                drawNode(curr.right, g, x + heso1, y + heso2, (heso1 / 2) >= 25 ? (heso1 / 2) : 25, (heso2 - 10) >= height ? (heso2 - 10) : height);
            }
        }
    }

    private void drawBranch(Node curr, Graphics g, int x, int y, int heso1, int heso2) {
        g.setColor(Color.red);
        if (curr != null) {
            if (curr.left != null) {
                g.drawLine(x, y, x - heso1, y + heso2);
                drawBranch(curr.left, g, x - heso1, y + heso2, (heso1 / 2) >= 25 ? (heso1 / 2) : 25, (heso2 - 10) >= height ? (heso2 - 10) : height);
            }
            if (curr.right != null) {
                g.drawLine(x, y, x + heso1, y + heso2);
                drawBranch(curr.right, g, x + heso1, y + heso2, (heso1 / 2) >= 25 ? (heso1 / 2) : 25, (heso2 - 10) >= height ? (heso2 - 10) : height);
            }
        }
    }
}

//                if (!existsTree) {
//                    try {
//                        Thread.sleep(500);
//                        g.drawLine(x, y, x + heso1, y + heso2);
//                        drawTree(curr.right, g, x + heso1, y + heso2, heso1 - 30, heso2 - 20);
//
//                    } catch (InterruptedException e) {
//                        Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, e);
//                    }
//                } else {
public class Assignment1 extends javax.swing.JFrame {

    /**
     * Creates new form Assignment1
     */
    public TreeAdvanced tree;

    //boolean variable to indicate that the tree has been painted
    //public DisplayJPanel jPanel3;
    public Assignment1() {
        tree = null;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        changeMode = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        createTree = new javax.swing.JButton();
        createTree3 = new javax.swing.JButton();
        createTree1 = new javax.swing.JButton();
        createTree2 = new javax.swing.JButton();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        find = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        createTree5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new DisplayJPanel(tree,jTextField1,jTextField2,jTextField3,jTextField4,jTextField5);
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(1, 0));
        setMinimumSize(new java.awt.Dimension(1024, 786));

        jPanel2.setLayout(new java.awt.GridLayout(8, 2));

        jLabel9.setText("FIle Directory");
        jPanel2.add(jLabel9);
        jPanel2.add(jTextField6);

        changeMode.setText("Change to AVL");
        changeMode.setToolTipText("");
        changeMode.setActionCommand("Chang to AVL");
        changeMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeModeActionPerformed(evt);
            }
        });
        jPanel2.add(changeMode);
        jPanel2.add(jLabel8);

        createTree.setText("Create Random");
        createTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTreeActionPerformed(evt);
            }
        });
        jPanel2.add(createTree);

        createTree3.setText("Create File");
        createTree3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTree3ActionPerformed(evt);
            }
        });
        jPanel2.add(createTree3);

        createTree1.setText("Create Skewed Left");
        createTree1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTree1ActionPerformed(evt);
            }
        });
        jPanel2.add(createTree1);

        createTree2.setText("Create Skewed Right");
        createTree2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTree2ActionPerformed(evt);
            }
        });
        jPanel2.add(createTree2);

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel2.add(add);

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel2.add(update);

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel2.add(delete);

        find.setText("Find");
        find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findActionPerformed(evt);
            }
        });
        jPanel2.add(find);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LNR", "LRN", "NLR", "RNL", "NRL", "RLN" }));
        jPanel2.add(jComboBox1);

        createTree5.setText("Traversals");
        createTree5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTree5ActionPerformed(evt);
            }
        });
        jPanel2.add(createTree5);

        jButton1.setText("Predecessor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Successor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jPanel4.setLayout(new java.awt.GridLayout(6, 0, 2, 0));

        jLabel7.setText("BST Mode");
        jPanel4.add(jLabel7);

        jLabel6.setText("Clear All");
        jLabel6.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
                jLabel6.setForeground(Color.red);
                setCursor(Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                jLabel6.setForeground(Color.BLACK);
                setCursor(Cursor.DEFAULT_CURSOR);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextArea1.setText("");
                jPanel3.bol=false;
            }
        });
        jPanel4.add(jLabel6);

        jLabel1.setText("ID");
        jPanel4.add(jLabel1);
        jPanel4.add(jTextField1);

        jLabel2.setText("FullName");
        jPanel4.add(jLabel2);
        jPanel4.add(jTextField2);

        jLabel3.setText("Birthday");
        jPanel4.add(jLabel3);
        jPanel4.add(jTextField3);

        jLabel4.setText("Average Of Score");
        jPanel4.add(jLabel4);

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField4);

        jLabel5.setText("Number Of Credits");
        jPanel4.add(jLabel5);
        jPanel4.add(jTextField5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(403, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 429, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 800));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel3);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        setBounds(0, 0, 1091, 796);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        int ID = 0;
        String fullName = null;
        String birthDay = null;
        double avgScore = 0.0;
        int noOfCredits = 0;
        if (tree == null) {
            if (changeMode.getText().equals("Change to BST")) {
                tree = new TreeAdvanced("AVL");
            }
            if (changeMode.getText().equals("Change to AVL")) {
                tree = new TreeAdvanced("BST");
            }
        }
        if (!jTextField1.getText().equals("")) {

            ID = Integer.parseInt(jTextField1.getText());
            fullName = jTextField2.getText();
            try {
                birthDay = jTextField3.getText();
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
                Date date = s.parse(birthDay);
                birthDay = s.format(date);
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Khong co BirthDay\n");
            }
            try {
                avgScore = Double.parseDouble(jTextField4.getText());
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Khong co Average\n");
            }
            try {
                noOfCredits = Integer.parseInt(jTextField5.getText());
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Khong co noOfCredits\n");
            }
            try {
                Information inf = new Information(ID, fullName, birthDay, avgScore, noOfCredits);
                System.out.println(inf);
                tree.addNode(inf);
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Loi tao node\n");
            }

        }
        jPanel3.setTree(tree);
    }//GEN-LAST:event_addActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void changeModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeModeActionPerformed
        //TODO add your handling code here:
        jTextArea1.setText("");
        if (changeMode.getText().equals("Change to AVL")) {
            Node t = null;
            if (tree != null) {
                t = tree.root;
                tree.setType("AVL");
                tree.root = null;
                if (t != null) {
                    ArrayList<Information> array = new ArrayList<Information>();
                    Node.toArray(t, array);
                    for (Information x : array) {
                        tree.addNode(x);
                    }
                }
                jPanel3.setTree(tree);
                changeMode.setText("Change to BST");
                jLabel7.setText("AVL Mode");
            }
        } else if (changeMode.getText().equals("Change to BST")) {
            if (tree != null) {
                tree.setType("BST");
                jPanel3.setTree(tree);
                changeMode.setText("Change to AVL");
                jLabel7.setText("BST Mode");
            }
        }
    }//GEN-LAST:event_changeModeActionPerformed

    private void createTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTreeActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        if (tree == null) {
            if (changeMode.getText().equals("Change to BST")) {
                tree = new TreeAdvanced("AVL");
                tree.createTreeRandom();
            }
            if (changeMode.getText().equals("Change to AVL")) {
                tree = new TreeAdvanced("BST");
                tree.createTreeRandom();
            }
        } else {
            tree.createTreeRandom();
        }
        jPanel3.setTree(tree);
    }//GEN-LAST:event_createTreeActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        int ID = -1;
        String fullName = null;
        String birthDay = null;
        double avgScore = -1.0;
        int noOfCredits = -1;
        jTextArea1.setText("");
        if (jTextField1.getText() != "") {
            try {
                ID = Integer.parseInt(jTextField1.getText());
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Sai ID\n");
                return;
            }
            if (!jTextField2.getText().equals("")) {
                fullName = jTextField2.getText();
                tree.update(ID, fullName, birthDay, avgScore, noOfCredits, 1);
            }
            if (!jTextField3.getText().equals("")) {
                try {
                    birthDay = jTextField3.getText();
                    SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy\n");
                    Date date = s.parse(birthDay);
                    tree.update(ID, fullName, s.format(date), avgScore, noOfCredits, 2);
                } catch (Exception e) {
                    jTextArea1.setText(jTextArea1.getText()+"Sai BirthDay\n");
                }
            }
            if (!jTextField4.getText().equals("")) {
                try {
                    avgScore = Double.parseDouble(jTextField4.getText());
                    tree.update(ID, fullName, birthDay, avgScore, noOfCredits, 3);
                } catch (Exception e) {
                    jTextArea1.setText(jTextArea1.getText()+"Sai Average\n");
                }
            }
            if (!jTextField5.getText().equals("")) {
                try {
                    noOfCredits = Integer.parseInt(jTextField5.getText());
                    tree.update(ID, fullName, birthDay, avgScore, noOfCredits, 4);
                } catch (Exception e) {
                    jTextArea1.setText(jTextArea1.getText()+"Sai Credits\n");
                }
            }

        }
        jPanel3.bol = false;
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        ArrayList<Information> res = find();
        for (Information x : res) {
            tree.delete(x.ID);
        }
        jPanel3.setTree(tree);
        jPanel3.paintDefault();
        jPanel3.bol = false;
    }//GEN-LAST:event_deleteActionPerformed

    private void findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        ArrayList<Information> res = find();
        jPanel3.paintFind(res);
        jPanel3.bol = false;
        for(Information x: res)
            jTextArea1.setText(jTextArea1.getText()+x);
        jPanel3.bol = false;
    }//GEN-LAST:event_findActionPerformed

    private void createTree1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTree1ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        if (tree == null) {
            if (changeMode.getText().equals("Change to BST")) {
                tree = new TreeAdvanced("AVL");
                tree.createSkewedLeft();
            }
            if (changeMode.getText().equals("Change to AVL")) {
                tree = new TreeAdvanced("BST");
                tree.createSkewedLeft();
            }
        } else {
            tree.createSkewedLeft();
        }
        jPanel3.setTree(tree);
        jPanel3.bol = false;
    }//GEN-LAST:event_createTree1ActionPerformed

    private void createTree2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTree2ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        if (tree == null) {
            if (changeMode.getText().equals("Change to BST")) {
                tree = new TreeAdvanced("AVL");
                tree.createSkewedRight();
            }
            if (changeMode.getText().equals("Change to AVL")) {
                tree = new TreeAdvanced("BST");
                tree.createSkewedRight();
            }
        } else {
            tree.createSkewedRight();
        }
        jPanel3.setTree(tree);
        jPanel3.bol = false;
    }//GEN-LAST:event_createTree2ActionPerformed

    private void createTree3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTree3ActionPerformed
        // TODO add your handling code here:
        try {
            jTextArea1.setText("");
            if (changeMode.getText().equals("Change to BST")) {
                tree = new TreeAdvanced("AVL");
                tree.createFileTree(jTextField6.getText());
            }
            if (changeMode.getText().equals("Change to AVL")) {
                tree = new TreeAdvanced("BST");
                tree.createFileTree(jTextField6.getText());
            }
            jPanel3.setTree(tree);
        } catch (Exception e) {
            jTextArea1.setText(jTextArea1.getText()+"Loi tao cay\n");
        }
        jPanel3.bol = false;
    }//GEN-LAST:event_createTree3ActionPerformed

    private void createTree5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTree5ActionPerformed
        // TODO add your handling code here:
        String temp = (String) jComboBox1.getSelectedItem();
        System.out.println(temp);
        ArrayList<Information> res = new ArrayList<Information>();
        if (temp.equals("LNR")) {
            tree.LNR(tree.root, res);
            System.out.println(res);
            jPanel3.drawTraversal(res);
        } else if (temp.equals("LRN")) {
            tree.LRN(tree.root, res);
            jPanel3.drawTraversal(res);
        } else if (temp.equals("NLR")) {
            tree.NLR(tree.root, res);
            jPanel3.drawTraversal(res);
        } else if (temp.equals("RNL")) {
            tree.RNL(tree.root, res);
            jPanel3.drawTraversal(res);
        } else if (temp.equals("NRL")) {
            tree.NRL(tree.root, res);
            jPanel3.drawTraversal(res);
        } else {
            tree.RLN(tree.root, res);
            jPanel3.drawTraversal(res);
        }
        jTextArea1.setText("");
        for(Information x:res)
            jTextArea1.setText(jTextArea1.getText()+x);
        jPanel3.bol = false;
    }//GEN-LAST:event_createTree5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        try {
                int key = Integer.parseInt(jTextField1.getText());
                Information predecessor= tree.predecessor(key);
                jPanel3.paintFind(predecessor);
                jTextArea1.setText(jTextArea1.getText()+predecessor);
        }
        catch (Exception e) {
                jTextArea1.setText("Khong co ID\n");
        }
        jPanel3.bol = false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        try {
                int key = Integer.parseInt(jTextField1.getText());
                Information successor= tree.successor(key);
                jPanel3.paintFind(successor);
                jTextArea1.setText(jTextArea1.getText()+successor);
            } catch (Exception e) {
                jTextArea1.setText("Khong co ID\n");
            }
        jPanel3.bol = false;
    }//GEN-LAST:event_jButton2ActionPerformed
    private ArrayList<Information> find() {
        jTextArea1.setText("");
        Information ID = null;
        ArrayList<Information> array = new ArrayList<Information>();
        if (jTextField1.getText() != "") {
            try {
                int key = Integer.parseInt(jTextField1.getText());
                ID = tree.findID(tree.root, key);
                array.add(ID);
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Khong co ID");
            }
            System.out.println("Done!");
        }

        if (!jTextField2.getText().equals("")) {
            String fullName = jTextField2.getText();
            array = tree.findName(tree.root, fullName, array);
            System.out.println("Done!");
        }
        if (!jTextField3.getText().equals("")) {
            try {
                String birthDay = jTextField3.getText();
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
                Date date = s.parse(birthDay);
                array = tree.findBirthDay(tree.root, s.format(date), array);
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Sai BirthDay\n");
            }
            System.out.println("Done!");
        }
        if (!jTextField4.getText().equals("")) {
            try {
                double avgScore = Double.parseDouble(jTextField4.getText());
                array = tree.findAvgScore(tree.root, avgScore, array);
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Sai Average\n");
            }
            System.out.println("Done!");
        }
        if (!jTextField5.getText().equals("")) {
            try {
                int noOfCredits = Integer.parseInt(jTextField5.getText());
                array = tree.findNoOfCredits(tree.root, noOfCredits, array);
            } catch (Exception e) {
                jTextArea1.setText(jTextArea1.getText()+"Sai Credits\n");
            }
            System.out.println("Done!");
        }
        System.out.print("Dau:\n" + array);
        ArrayList<Integer> times = new ArrayList<Integer>();
        ArrayList<Information> find = new ArrayList<Information>();
        while (!array.isEmpty()) {
            Information t = array.get(0);
            int k = 1;
            ArrayList<Integer> index = new ArrayList<Integer>();
            index.add(0);
            for (int i = 1; i < array.size(); i++) {
                if (array.get(i).equals(t)) {
                    k += 1;
                    index.add(i);
                }
            }
            Collections.reverse(index);
            for (Integer x : index) {
                array.remove((int) x);
            }
            times.add(k);
            find.add(t);
        }
        Integer max;
        ArrayList<Information> res = new ArrayList<Information>();
        if (!times.isEmpty()) {
            max = times.get(0);
            for (Integer x : times) {
                if (max.compareTo(x) <= -1) {
                    max = x;
                }
            }
            for (int i = 0; i < times.size(); i++) {
                if (times.get(i).equals(max)) {
                    res.add(find.get(i));
                }
            }
        }
        System.out.print("Luc Sau:\n" + res);
        return res;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Assignment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Assignment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Assignment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Assignment1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Assignment1 as1 = new Assignment1();
                as1.setVisible(true);
                as1.setLocationRelativeTo(null);
                as1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                as1.setExtendedState(as1.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                as1.setResizable(false);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton changeMode;
    private javax.swing.JButton createTree;
    private javax.swing.JButton createTree1;
    private javax.swing.JButton createTree2;
    private javax.swing.JButton createTree3;
    private javax.swing.JButton createTree5;
    private javax.swing.JButton delete;
    private javax.swing.JButton find;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private DisplayJPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
