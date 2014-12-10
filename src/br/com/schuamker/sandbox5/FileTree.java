package br.com.schuamker.sandbox5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

/**
 *
 * @author hudson.sales
 */
public class FileTree extends JPanel implements Runnable {

    private JTree tree;
    private File dir;

    public FileTree(File dir) {
        this.dir = dir;
        setLayout(new BorderLayout());
        tree = new JTree();// Make a tree list with all the nodes, and make it a JTree
        tree.addTreeSelectionListener(new TreeSelectionListener() { // Add a listener
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                System.out.println("You selected " + node);
            }
        });
        // Lastly, put the JTree into a JScrollPane.
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.getViewport().add(tree);
        add(BorderLayout.CENTER, scrollpane);
    }

    /**
     * Add nodes from under "dir" into curTop. Highly recursive.
     */
    private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        File f;
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        ArrayList<String> ol = new ArrayList<>();
        String[] tmp = dir.list();
        for (int i = 0; i < tmp.length; i++) {
            ol.add(tmp[i]);
        }
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);        
        Vector files = new Vector();
        for (String thisObject : ol) {
            String newPath;
            if (curPath.equals(".")) {
                newPath = thisObject;
            } else {
                newPath = curPath + File.separator + thisObject;
            }
            if ((f = new File(newPath)).isDirectory()) {
                addNodes(curDir, f);
            } else {
                files.addElement(thisObject);
            }
        }
        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++) {
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        }
        return curDir;
    }

    private File[] getRoots() {
        return File.listRoots();
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(400, 400);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void run() {
        tree.setModel((TreeModel) addNodes(null, dir));
    }
    
    public void start(){
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
}
