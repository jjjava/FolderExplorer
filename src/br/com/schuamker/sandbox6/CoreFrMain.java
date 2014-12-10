package br.com.schuamker.sandbox6;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
        System.err.println(file.getAbsolutePath());
        if (file.isDirectory()) {
            DefaultTreeModel model = (DefaultTreeModel) main.jTree1.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            //model.reload(root);
            model.reload();
            if (curTop == null) {
                curTop = new DefaultMutableTreeNode(file.getName());
                main.root.add(curTop);
            } else {
                DefaultMutableTreeNode aux = new DefaultMutableTreeNode(file.getName());
                curTop.add(aux);
                curTop = aux;

            }
            File[] list = file.listFiles();
            for (File list1 : list) {
                allList(curTop, list1);
            }
        } else {
            curTop.add(new DefaultMutableTreeNode(file.getName()));
        }

    }

    @Override
    public void run() {
        allList(null, new File("P:/"));
    }
}
