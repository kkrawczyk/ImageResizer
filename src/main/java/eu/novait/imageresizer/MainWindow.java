/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.imageresizer;

import eu.novait.imageresizer.helpers.ImageConverter;
import eu.novait.imageresizer.helpers.ImageFile;
import eu.novait.imageresizer.models.ImageListModel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Krzysztof
 */
public class MainWindow extends javax.swing.JFrame {

    private File outputDirectory;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        ImageListModel ilm = new ImageListModel();
        jList1.setModel(ilm);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        this.setLocation(x, y);

        //this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 480));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setText("Dodaj pliki");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jLabel1.setText("Szer.");
        jToolBar1.add(jLabel1);

        jTextField1.setText("1200");
        jToolBar1.add(jTextField1);

        jLabel2.setText("Wys.");
        jToolBar1.add(jLabel2);

        jTextField2.setText("800");
        jToolBar1.add(jTextField2);

        jButton3.setText("Ustaw katalog wyjściowy");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton2.setText("Przetwarzaj");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Katalog wyjściowy: [BRAK]");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Wybierz katalog wyjściowy");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.outputDirectory = chooser.getSelectedFile();
            jLabel3.setText("Katalog wyjściowy: " + this.outputDirectory.getAbsolutePath());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ImageListModel ilm = (ImageListModel) jList1.getModel();
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setAcceptAllFileFilterUsed(false);
        String[] suffices = ImageIO.getReaderFileSuffixes();
        for (int i = 0; i < suffices.length; i++) {
            FileFilter filter = new FileNameExtensionFilter("Pliki: " + suffices[i], suffices[i]);
            chooser.addChoosableFileFilter(filter);
        }
        int od = chooser.showOpenDialog(this);
        if (od == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (int i = 0; i < files.length; i++) {
                ilm.addElement(files[i].getAbsolutePath());
                getContentPane().invalidate();
                getContentPane().validate();
                jList1.ensureIndexIsVisible(ilm.getSize() - 1);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        final int newwidth, newheight;
        if(outputDirectory==null || !outputDirectory.exists()){
            JOptionPane.showMessageDialog(this, "Katalog wyjściowy nie istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        try {
            newwidth = Integer.parseInt(jTextField1.getText());
            newheight = Integer.parseInt(jTextField2.getText());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Sprawdź poprawność rozmiaru pliku wyjściowego!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                ImageListModel ilm = (ImageListModel)jList1.getModel();
                for(ImageFile ifile : ilm.getAll()){
                    jLabel3.setText("Przetwarzam plik: "+ifile.getFilename());
                    ImageConverter ic = new ImageConverter(ifile.getFilename(), ifile.getOutputFilename(outputDirectory.getAbsolutePath()), newwidth, newheight);
                    try {
                        ic.process();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Błąd zmiany rozmiaru pliku: "+ifile.getFilename(), "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
                jLabel3.setText("Przetwarzanie zakończone. Katalog wyjściowy: "+outputDirectory.getAbsolutePath());
            }
        });
        t.start();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
