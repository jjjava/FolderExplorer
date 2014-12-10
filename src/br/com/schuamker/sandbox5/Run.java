package br.com.schuamker.sandbox5;

import java.awt.Color;
import java.awt.Container;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *
 * @author hudson.sales
 */
public class Run {

    public static void main(String[] args) {
        JFrame frame = new JFrame("FileTree");
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        Container cp = frame.getContentPane();

        if (args.length == 0) {
            // File rFiles[] = File.listRoots();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileTree fileTree = new FileTree(new File("C:/Work"));
                    fileTree.start();
                    
                    
                    cp.add(new FileTree(new File("C:/Work")));
                }
            }).start();
        } else {
            cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
            for (String av1 : args) {
                cp.add(new FileTree(new File(av1)));
            }
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
