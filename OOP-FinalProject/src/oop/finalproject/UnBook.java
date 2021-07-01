/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.finalproject;

import java.io.IOException;
import javax.swing.JOptionPane;
import oop.classes.BookingManagement;

/**
 *
 * @author Zeeshan
 */
public class UnBook extends javax.swing.JDialog {

    /**
     * Creates new form UnBook
     */
    public UnBook(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //populate combo box with current booked cars
        populate_currentBookings();
    }
    //this function is referenced to call in constructor when car needs to un-book
    public void populate_currentBookings()
    {
        //declarations
        BookingManagement bookingManage = null;
        
        //initializations with handeled exceptions
        try {
            bookingManage = new BookingManagement();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Bookings does not Found!", 2);
        }
        //if no exception occured then proceed next operation
        if(bookingManage != null)
        {
            //get all current bookings 
            String[] bookingsArray = bookingManage.getAllBookedCarNames();
            
            //show all bookings into combo boxes
            for (String bookings_found : bookingsArray) {
                bookedCars_comboBox.addItem(bookings_found);
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookedCars_comboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        unBook_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bookedCars_comboBox.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Select Car to unbook from following booked cars");

        unBook_btn.setText("UnBook");
        unBook_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unBook_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(bookedCars_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(unBook_btn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(bookedCars_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(unBook_btn)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unBook_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unBook_btnActionPerformed
        String car_name = bookedCars_comboBox.getSelectedItem().toString();

        //Validator
        if(car_name.isEmpty())
            JOptionPane.showMessageDialog(null, "No Car Selected!", "Invalid Input", 1);
        else    //operation
        {
            BookingManagement bookingManage;    //Declaration
            try {       //initialization
                bookingManage = new BookingManagement();
                if(bookingManage.UnBook(car_name)){        //un-booking
                    //if un-booking succeded then show positive message
                    JOptionPane.showMessageDialog(null, "Car Unbooked Successfully!");
                    this.setVisible(false);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "bookings.txt/unbookings.txt file Error", 1);
            }
        }
    }//GEN-LAST:event_unBook_btnActionPerformed

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
            java.util.logging.Logger.getLogger(UnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UnBook dialog = new UnBook(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bookedCars_comboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton unBook_btn;
    // End of variables declaration//GEN-END:variables
}