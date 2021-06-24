/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SistemaPet.telas;

import br.com.SistemaPetShop.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Julia Fideles
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    // usando a variavel conexao do DAL;

    Connection conexao = null;
    // criando variaveis especiais ´para conexao com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql.*;
    //e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionar(){
         String sql = "insert into TbClientes( nomeCliente,CEPCliente,CPFCliente, RGCliente, bairroCliente, ruaCliente, numeroCliente, telefoneCliente, emailCliente)"
                 + "values(?,?,?,?,?,?,?,?,?); ";
         try {
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtClienteNome.getText());
             pst.setString(2, txtClienteCEP.getText());
             pst.setString(3, txtClienteCPF.getText());
             pst.setString(4, txtClienteRG.getText());
             pst.setString(5, txtClienteBairro.getText());
             pst.setString(6, txtClienteRua.getText());
             pst.setString(7, txtClienteNumero.getText());
             pst.setString(8, txtClienteCelular.getText());
             pst.setString(9, txtClienteEmail.getText());
            if(txtClienteNome.getText().isEmpty()||txtClienteCEP.getText().isEmpty()||txtClienteCPF.getText().isEmpty()||txtClienteRG.getText().isEmpty()||
                    txtClienteBairro.getText().isEmpty()||txtClienteRua.getText().isEmpty()||txtClienteNumero.getText().isEmpty()|txtClienteCelular.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
                 txtClienteNome.setText(null);
                 txtClienteCEP.setText(null);
                 txtClienteCPF.setText(null);
                 txtClienteRG.setText(null);
                 txtClienteBairro.setText(null);
                 txtClienteRua.setText(null);
                 txtClienteNumero.setText(null);
                 txtClienteCelular.setText(null);
                 txtClienteEmail.setText(null);
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar(){
            String sql = "update TbClientes  set nomeCliente = ?,CEPCliente = ?,CPFCliente =?, RGCliente =?, bairroCliente=?, ruaCliente = ?, numeroCliente = ?, telefoneCliente = ?, emailCliente = ?where idCliente= ? ";
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(2, txtClienteNome.getText());
             pst.setString(3, txtClienteCEP.getText());
             pst.setString(4, txtClienteCPF.getText());
             pst.setString(5, txtClienteRG.getText());
             pst.setString(6, txtClienteBairro.getText());
             pst.setString(7, txtClienteRua.getText());
             pst.setString(8, txtClienteNumero.getText());
             pst.setString(9, txtClienteCelular.getText());
             pst.setString(10, txtClienteEmail.getText());
             pst.setString(1, txtClienteID.getText());
             
            if(txtClienteNome.getText().isEmpty()||txtClienteCEP.getText().isEmpty()||txtClienteCPF.getText().isEmpty()||txtClienteRG.getText().isEmpty()||
                    txtClienteBairro.getText().isEmpty()||txtClienteRua.getText().isEmpty()||txtClienteNumero.getText().isEmpty()|txtClienteCelular.getText().isEmpty()){
             
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
                 txtClienteNome.setText(null);
                 txtClienteCEP.setText(null);
                 txtClienteCPF.setText(null);
                 txtClienteRG.setText(null);
                 txtClienteBairro.setText(null);
                 txtClienteRua.setText(null);
                 txtClienteNumero.setText(null);
                 txtClienteCelular.setText(null);
                 txtClienteEmail.setText(null);
                 
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
              String sql = "delete from TbClientes  where idCliente = ? ";
              try {
                  pst = conexao.prepareStatement(sql);
                 pst.setString(1, txtClienteID.getText());
                 pst.executeUpdate();
                  JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
        }
       
    }
    private void pesquisar_campo(){
        String sql = "select * from TbClientes where nomeCliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClientePesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblCliente.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
        
    }
    private void setar_campo(){
        int setar = tblCliente.getSelectedRow();
         txtClienteID.setText(tblCliente.getModel().getValueAt(setar, 0).toString());
        txtClienteNome.setText(tblCliente.getModel().getValueAt(setar, 1).toString());
        txtClienteCEP.setText(tblCliente.getModel().getValueAt(setar, 2).toString());
        txtClienteCPF.setText(tblCliente.getModel().getValueAt(setar, 3 ).toString());
        txtClienteRG.setText(tblCliente.getModel().getValueAt(setar, 4).toString());
        txtClienteBairro.setText(tblCliente.getModel().getValueAt(setar, 5 ).toString());
        txtClienteRua.setText(tblCliente.getModel().getValueAt(setar, 6 ).toString());
        txtClienteNumero.setText(tblCliente.getModel().getValueAt(setar, 7 ).toString());
        txtClienteCelular.setText(tblCliente.getModel().getValueAt(setar, 8 ).toString());
        txtClienteEmail.setText(tblCliente.getModel().getValueAt(setar, 9).toString());
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        txtClienteBairro = new javax.swing.JTextField();
        txtClienteEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtClientePesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtClienteID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtClienteRG = new javax.swing.JFormattedTextField();
        txtClienteCPF = new javax.swing.JFormattedTextField();
        txtClienteCelular = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtClienteRua = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtClienteNumero = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtClienteCEP = new javax.swing.JFormattedTextField();
        txtClienteNome = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnClienteAdicionar = new javax.swing.JButton();
        btnClienteExcluir = new javax.swing.JButton();
        btnClienteEditar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cliente");
        setPreferredSize(new java.awt.Dimension(872, 857));

        jLabel8.setText("*Celular:");

        jLabel9.setText("Email:");

        txtClientePesquisar.setToolTipText("");
        txtClientePesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientePesquisarKeyReleased(evt);
            }
        });

        jLabel1.setText("*ID:");

        txtClienteID.setEnabled(false);

        jLabel2.setText("*Campo Obrigatorio");

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5", "Título 6", "Título 7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        jLabel3.setText("*RG:");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/searchBlue48px.png"))); // NOI18N

        jLabel5.setText("*Bairro:");

        jLabel6.setText("*CPF:");

        try {
            txtClienteRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtClienteRG.setText("000000000-0");

        try {
            txtClienteCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtClienteCPF.setText("000.000.000-00");

        try {
            txtClienteCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtClienteCelular.setText("(00) 00000-0000");

        jLabel7.setText("*Rua:");

        jLabel12.setText("*Numero:");

        jLabel13.setText("*CEP:");

        try {
            txtClienteCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtClienteCEP.setText("00000-000");

        jLabel14.setText("*Nome:");

        btnClienteAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/addBlack128px.png"))); // NOI18N
        btnClienteAdicionar.setToolTipText("Adicionar");
        btnClienteAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClienteAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteAdicionarActionPerformed(evt);
            }
        });

        btnClienteExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/delete X Black 128px.png"))); // NOI18N
        btnClienteExcluir.setToolTipText("Deletar");
        btnClienteExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClienteExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteExcluirActionPerformed(evt);
            }
        });

        btnClienteEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/editar lapis black 128px.png"))); // NOI18N
        btnClienteEditar.setToolTipText("Editar");
        btnClienteEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClienteEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteEditarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 100)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LOGO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel9)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtClienteBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClienteRua))
                            .addComponent(txtClienteEmail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 1318, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(31, 31, 31)
                                .addComponent(txtClienteNome))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtClientePesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClienteAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnClienteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(btnClienteExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(370, 370, 370))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(txtClientePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtClienteRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)
                        .addComponent(txtClienteNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClienteCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtClienteBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtClienteRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClienteAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClienteEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClienteExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        setBounds(0, 0, 1405, 970);
    }// </editor-fold>//GEN-END:initComponents

    private void btnClienteAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnClienteAdicionarActionPerformed

    private void btnClienteEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteEditarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnClienteEditarActionPerformed

    private void btnClienteExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteExcluirActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnClienteExcluirActionPerformed

    private void txtClientePesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientePesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_campo();
    }//GEN-LAST:event_txtClientePesquisarKeyReleased

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
       setar_campo();
    }//GEN-LAST:event_tblClienteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClienteAdicionar;
    private javax.swing.JButton btnClienteEditar;
    private javax.swing.JButton btnClienteExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtClienteBairro;
    private javax.swing.JFormattedTextField txtClienteCEP;
    private javax.swing.JFormattedTextField txtClienteCPF;
    private javax.swing.JFormattedTextField txtClienteCelular;
    private javax.swing.JTextField txtClienteEmail;
    private javax.swing.JTextField txtClienteID;
    private javax.swing.JTextField txtClienteNome;
    private javax.swing.JTextField txtClienteNumero;
    private javax.swing.JTextField txtClientePesquisar;
    private javax.swing.JFormattedTextField txtClienteRG;
    private javax.swing.JTextField txtClienteRua;
    // End of variables declaration//GEN-END:variables
}
