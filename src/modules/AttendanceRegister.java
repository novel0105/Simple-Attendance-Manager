/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Novi
 */
public class AttendanceRegister extends javax.swing.JInternalFrame {

    /**
     * Creates new form AttendanceRegister
     */
    JLabel[] rollno=new JLabel[50];
    JLabel[] name=new JLabel[50];
    JComboBox[] status=new JComboBox[50];
    
    JTextFieldDateEditor e;
    int count=0;
    
    public AttendanceRegister() {
        initComponents();
        
        this.setBorder(null);
        BasicInternalFrameUI bui=(BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        e=(JTextFieldDateEditor) txtDate.getDateEditor();
        e.setEditable(false);
    }
    
    public void showStudents()
    {
        mainPanel.removeAll();
        mainPanel.updateUI();
        String std=comboClass.getSelectedItem().toString();
        if(std.equals("--SELECT--"))
        {
            JOptionPane.showMessageDialog(null, "Please select the class first");
        }
        else
        {
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendance","root","");  

                String sql="select RollNumber,Name from "+std;
                PreparedStatement pst=con.prepareStatement(sql);
                ResultSet rs=pst.executeQuery();
                int i=0,y=25;
                String[] items={"PRESENT","ABSENT"};
                while(rs.next())
                {
                    rollno[i]=new JLabel(rs.getString("RollNumber"));
                    rollno[i].setFont(new java.awt.Font("Arial", 1, 19));
                    rollno[i].setBounds(110, y, 99, 32);
                    mainPanel.add(rollno[i]);

                    name[i]=new JLabel(rs.getString("Name"));
                    name[i].setFont(new java.awt.Font("Arial", 1, 19));
                    name[i].setBounds(475, y, 363, 34);
                    mainPanel.add(name[i]);

                    status[i]=new JComboBox(items);
                    status[i].setFont(new java.awt.Font("Arial", 1, 16));
                    status[i].setBounds(930, y, 150, 30);
                    mainPanel.add(status[i]);

                    i++;
                    y=y+50;
                    count++;
                    
                }
                mainPanel.repaint();
                mainPanel.revalidate();
                rs.close();
                pst.close();
                con.close();
            }
            catch (Exception e) 
            {
                System.err.println(e);
            }
        }
            
    }
    
    public void createColumn()
    {
       
            try {
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendance","root","");
                

                String sql="ALTER TABLE `"+comboClass.getSelectedItem().toString()+"_attendance` ADD `"+e.getText()+"` VARCHAR(50)";
            
                Statement st=con.createStatement();
                st.executeUpdate(sql);
            
                st.close();
                con.close();
                
                
                update();
                
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Attendance already updated for the given date ");
            }
        
    }
    
    public void update()
    {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendance","root","");
            
            for (int i = 0; i <count; i++) {
                
                String sql="UPDATE `"+comboClass.getSelectedItem().toString()+"_attendance` SET `"+e.getText()+"`='"+status[i].getSelectedItem().toString()+"' WHERE RollNumber="+rollno[i].getText()+"";
                Statement st=con.createStatement();
                st.executeUpdate(sql);
                
                st.close();
                
            }
            con.close();
            JOptionPane.showMessageDialog(null, "Updated successfully");
                
        } 
        catch (Exception e) {
            System.out.println(e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboClass = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();

        mainPanel.setPreferredSize(new java.awt.Dimension(1159, 1000));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1159, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(mainPanel);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SELECT CLASS :");

        comboClass.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        comboClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECT--", "Eighth", "Ninth", "Tenth" }));
        comboClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClassActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NAME");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ROLL NO.");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("STATUS");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SELECT DATE :");

        txtDate.setDateFormatString("yyyy-MM-dd");
        txtDate.setFont(new java.awt.Font("Calibri", 1, 19)); // NOI18N

        jButton2.setFont(new java.awt.Font("MS PGothic", 1, 23)); // NOI18N
        jButton2.setText("SUBMIT");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(559, 559, 559)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel5)))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClassActionPerformed
        // TODO add your handling code here:
       mainPanel.removeAll();
        mainPanel.updateUI();
        showStudents();
    }//GEN-LAST:event_comboClassActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String msg="Are you sure add this Attendence for "+e.getText()+"? for class "+comboClass.getSelectedItem().toString();
        if(comboClass.getSelectedItem().equals("--SELECT--"))
        {
            JOptionPane.showMessageDialog(null, "Please select the class first");
        }
        else if(e.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please select date first");
        }
        else
        {
            int input=JOptionPane.showConfirmDialog(null, msg, "CONFIRMATION",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(input==0)
            {
                createColumn();
            }
            else
            {

            }
        }
            
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboClass;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private com.toedter.calendar.JDateChooser txtDate;
    // End of variables declaration//GEN-END:variables
}