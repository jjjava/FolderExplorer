/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.schuamker.sandbox;

/**
 *
 * @author hudson.sales
 */
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public final class SimpleTree extends JPanel {

    JTree tree;
    DefaultMutableTreeNode root;

    public SimpleTree() {
        root = new DefaultMutableTreeNode("root", true);
        getList(root, new File("c:/work"));
        setLayout(new BorderLayout());
        tree = new JTree(root);
        tree.setRootVisible(false);
        add(new JScrollPane((JTree) tree), "Center");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 120);
    }

    public void getList(DefaultMutableTreeNode node, File f) {
        if (!f.isDirectory()) {
            // We keep only JAVA source file for display in this HowTo
            if (f.getName().endsWith("java")) {
                System.out.println("FILE  -  " + f.getName());
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
                node.add(child);
            }
        } else {
            System.out.println("DIRECTORY  -  " + f.getName());
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
            node.add(child);
            File fList[] = f.listFiles();
            for (File fList1 : fList) {
                getList(child, fList1);
            }
        }
    }

    public static void main(String s[]) {
        MyJFrame frame = new MyJFrame("Directory explorer");
    }
}

class WindowCloser extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        Window win = e.getWindow();
        win.setVisible(false);
        System.exit(0);
    }
}

class MyJFrame extends JFrame {

    JButton b1, b2, b3;
    SimpleTree panel;

    MyJFrame(String s) {
        super(s);
        panel = new SimpleTree();
        getContentPane().add(panel, "Center");
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowCloser());
    }

}
