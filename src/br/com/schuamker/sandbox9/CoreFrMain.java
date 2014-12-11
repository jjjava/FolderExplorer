package br.com.schuamker.sandbox9;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author hudson.sales
 */
public class CoreFrMain implements Runnable {

    private FrMain main;
    private int op;
    private String node;

    
     public CoreFrMain(FrMain main, int op) {
        this.main = main;
        this.op = op;
    }
    
    public CoreFrMain(FrMain main, int op, String node) {
        this.main = main;
        this.op = op;
        this.node = node;
    }

    public void start() {
        Thread t = new Thread(this);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }

    @Override
    public void run() {
        if (op == 1) {
            String root = "C:/Work/";
            File file = new File("C:/Work");
            String[] names = file.list();
            for (String s : names) {
                File f = new File(root + s);
                if (f.isDirectory()) {
                    main.rootNode.add(new DefaultMutableTreeNode(s, true));
                } else {
                    main.rootNode.add(new DefaultMutableTreeNode(s, false));
                }
            }
        }
        if (op == 2) {
            String root = "C:/Work/";
            File file = new File(root+node);
            String[] names = file.list();
            for (String s : names) {
                File f = new File(root + s);
                if (f.isDirectory()) {
                   
                } else {
                    main.rootNode.add(new DefaultMutableTreeNode(s, false));
                }
            }
        }
    }
}
