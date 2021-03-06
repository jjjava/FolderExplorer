package br.com.schuamker.sandbox8;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author hudson.sales
 */
public class CoreFrMain implements Runnable {

    public NewJFrame main;

    public CoreFrMain(NewJFrame main) {
        this.main = main;
    }

    public void start() {
        Thread t = new Thread(this);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }

    private void allList(DefaultMutableTreeNode curTop, File file) {
        if (file.isDirectory()) {
            main.dynamicTree1.addObject(curTop, new DefaultMutableTreeNode(file.getName()), true);
            curTop = new DefaultMutableTreeNode(file.getName());
            File[] list = file.listFiles();
            for (File list1 : list) {
                allList(curTop, list1);
            }
        } else {
            System.err.println(file.getName());
            main.dynamicTree1.addObject(curTop, new DefaultMutableTreeNode(file.getName()), true);
        }
    }

    @Override
    public void run() {
        allList(null, new File("C:/Temp"));
    }
}
