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
public class TelaFuncionario extends javax.swing.JInternalFrame {

    // usando a variavel conexao do DAL;

    Connection conexao = null;
    // criando variaveis especiais ´para conexao com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql.*;
    //e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaFuncionario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
private void adicionar(){
         String sql = "insert into TbFuncionarios( nomeFuncionario,CEPFuncionario,CPFFuncionario, RGFuncionario, bairroFuncionario, ruaFuncionario, "
                 + "numeroFuncionario, telefoneFuncionario, emailFuncionario)"
                 + "values(?,?,?,?,?,?,?,?,?); ";
         try {
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtFuncionarioNome.getText());
             pst.setString(2, txtFuncionarioCEP.getText());
             pst.setString(3, txtFuncionarioCPF.getText());
             pst.setString(4, txtFuncionarioRG.getText());
             pst.setString(5, txtFuncionarioBairro.getText());
             pst.setString(6, txtFuncionariorRua.getText());
             pst.setString(7, txtFuncionarioNumero.getText());
             pst.setString(8, txtFuncionarioCelular.getText());
             pst.setString(9, txtFuncionarioEmail.getText());
            if(txtFuncionarioNome.getText().isEmpty()||txtFuncionarioCEP.getText().isEmpty()||txtFuncionarioCPF.getText().isEmpty()||txtFuncionarioRG.getText().isEmpty()||
                    txtFuncionarioBairro.getText().isEmpty()||txtFuncionariorRua.getText().isEmpty()||txtFuncionarioNumero.getText().isEmpty()|txtFuncionarioCelular.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso");
                 txtFuncionarioID.setText(null);
                 txtFuncionarioNome.setText(null);
                 txtFuncionarioCEP.setText(null);
                 txtFuncionarioCPF.setText(null);
                 txtFuncionarioRG.setText(null);
                 txtFuncionarioBairro.setText(null);
                 txtFuncionariorRua.setText(null);
                 txtFuncionarioNumero.setText(null);
                 txtFuncionarioCelular.setText(null);
                 txtFuncionarioEmail.setText(null);
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar(){
            String sql = "update TbFuncionarios  set nomeFuncionario = ?,CEPFuncionario = ?,CPFFuncionario = ?, RGFuncionario = ?, bairroFuncionario = ?, ruaFuncionario = ?, "
                 + "numeroFuncionario = ?, telefoneFuncionario = ?, emailFuncionario = ? where idFuncionario= ? ";
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtFuncionarioNome.getText());
             pst.setString(2, txtFuncionarioCEP.getText());
             pst.setString(3, txtFuncionarioCPF.getText());
             pst.setString(4, txtFuncionarioRG.getText());
             pst.setString(5, txtFuncionarioBairro.getText());
             pst.setString(6, txtFuncionariorRua.getText());
             pst.setString(7, txtFuncionarioNumero.getText());
             pst.setString(8, txtFuncionarioCelular.getText());
             pst.setString(9, txtFuncionarioEmail.getText());
             pst.setString(10, txtFuncionarioID.getText());
             
            if(txtFuncionarioNome.getText().isEmpty()||txtFuncionarioCEP.getText().isEmpty()||txtFuncionarioCPF.getText().isEmpty()||txtFuncionarioRG.getText().isEmpty()||
                    txtFuncionarioBairro.getText().isEmpty()||txtFuncionariorRua.getText().isEmpty()||txtFuncionarioNumero.getText().isEmpty()|txtFuncionarioCelular.getText().isEmpty()){
              
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Dados do funcionario alterados com sucesso");
                 txtFuncionarioID.setText(null);
                 txtFuncionarioNome.setText(null);
                 txtFuncionarioCEP.setText(null);
                 txtFuncionarioCPF.setText(null);
                 txtFuncionarioRG.setText(null);
                 txtFuncionarioBairro.setText(null);
                 txtFuncionariorRua.setText(null);
                 txtFuncionarioNumero.setText(null);
                 txtFuncionarioCelular.setText(null);
                 txtFuncionarioEmail.setText(null);
                 
                 
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse funcionario?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
              String sql = "delete from TbFuncionarios  where idFuncionario = ? ";
              try {
                  pst = conexao.prepareStatement(sql);
                 pst.setString(1, txtFuncionarioID.getText());
                 pst.executeUpdate();
                  JOptionPane.showMessageDialog(null, "Funcionario excluido com sucesso");
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
        }
       
    }
    private void pesquisar_campo(){
        String sql = "select * from TbFuncionarios where nomeFuncionario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtFuncionarioPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblFuncionario.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
        
    }
     private void setar_campo(){
        int setar = tblFuncionario.getSelectedRow();
        txtFuncionarioID.setText(tblFuncionario.getModel().getValueAt(setar, 0).toString());
        txtFuncionarioNome.setText(tblFuncionario.getModel().getValueAt(setar, 1).toString());
        txtFuncionarioCEP.setText(tblFuncionario.getModel().getValueAt(setar, 2 ).toString());
        txtFuncionarioCPF.setText(tblFuncionario.getModel().getValueAt(setar, 3 ).toString());
        txtFuncionarioRG.setText(tblFuncionario.getModel().getValueAt(setar, 4).toString());
        txtFuncionarioBairro.setText(tblFuncionario.getModel().getValueAt(setar, 5 ).toString());
        txtFuncionariorRua.setText(tblFuncionario.getModel().getValueAt(setar, 6 ).toString());
        txtFuncionarioNumero.setText(tblFuncionario.getModel().getValueAt(setar, 7 ).toString());
        txtFuncionarioCelular.setText(tblFuncionario.getModel().getValueAt(setar, 8 ).toString());
        txtFuncionarioEmail.setText(tblFuncionario.getModel().getValueAt(setar, 9 ).toString());
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        txtFuncionarioEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFuncionarioPesquisar = new javax.swing.JTextField();
        txtFuncionarioRG = new javax.swing.JFormattedTextField();
        txtFuncionarioCPF = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        txtFuncionarioCelular = new javax.swing.JFormattedTextField();
        txtFuncionarioID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFuncionariorRua = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFuncionarioNumero = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionario = new javax.swing.JTable();
        txtFuncionarioCEP = new javax.swing.JFormattedTextField();
        txtFuncionarioNome = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFuncionarioBairro = new javax.swing.JTextField();
        btnFuncionarioAdicionar = new javax.swing.JButton();
        btnFuncionarioExcluir = new javax.swing.JButton();
        btnFuncionarioEditar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(872, 857));

        jLabel6.setText("*CPF:");

        jLabel9.setText("Email:");

        txtFuncionarioPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFuncionarioPesquisarKeyReleased(evt);
            }
        });

        try {
            txtFuncionarioRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFuncionarioRG.setText("000000000-0");

        try {
            txtFuncionarioCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFuncionarioCPF.setText("000.000.000-00");

        jLabel1.setText("*ID:");

        try {
            txtFuncionarioCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFuncionarioCelular.setText("(00) 00000-0000");

        txtFuncionarioID.setEnabled(false);

        jLabel7.setText("*Rua:");

        jLabel2.setText("*Campo Obrigatorio");

        jLabel12.setText("*Numero:");

        jLabel13.setText("*CEP:");

        tblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFuncionario);

        try {
            txtFuncionarioCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFuncionarioCEP.setText("00000-000");

        jLabel14.setText("*Nome:");

        jLabel3.setText("*RG:");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/searchBlue48px.png"))); // NOI18N

        jLabel5.setText("*Bairro:");

        jLabel8.setText("*Celular:");

        btnFuncionarioAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/addBlack128px.png"))); // NOI18N
        btnFuncionarioAdicionar.setToolTipText("Adicionar");
        btnFuncionarioAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFuncionarioAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionarioAdicionarActionPerformed(evt);
            }
        });

        btnFuncionarioExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/delete X Black 128px.png"))); // NOI18N
        btnFuncionarioExcluir.setToolTipText("Deletar");
        btnFuncionarioExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFuncionarioExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionarioExcluirActionPerformed(evt);
            }
        });

        btnFuncionarioEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/editar lapis black 128px.png"))); // NOI18N
        btnFuncionarioEditar.setToolTipText("Editar");
        btnFuncionarioEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFuncionarioEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionarioEditarActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFuncionarioPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 1342, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFuncionarioBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionariorRua))
                    .addComponent(txtFuncionarioEmail)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(31, 31, 31)
                .addComponent(txtFuncionarioNome))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionarioNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(277, 277, 277))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFuncionarioAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(btnFuncionarioExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(388, 388, 388))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFuncionarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtFuncionarioPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 252, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtFuncionarioRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuncionarioCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuncionarioCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)
                        .addComponent(txtFuncionarioNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFuncionarioCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFuncionarioBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtFuncionariorRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFuncionarioAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFuncionarioExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        setBounds(0, 0, 1405, 970);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFuncionarioAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionarioAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnFuncionarioAdicionarActionPerformed

    private void btnFuncionarioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionarioEditarActionPerformed
        alterar();
    }//GEN-LAST:event_btnFuncionarioEditarActionPerformed

    private void btnFuncionarioExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionarioExcluirActionPerformed
       deletar();
    }//GEN-LAST:event_btnFuncionarioExcluirActionPerformed

    private void txtFuncionarioPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuncionarioPesquisarKeyReleased
        pesquisar_campo();
    }//GEN-LAST:event_txtFuncionarioPesquisarKeyReleased

    private void tblFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioMouseClicked
       setar_campo();
    }//GEN-LAST:event_tblFuncionarioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFuncionarioAdicionar;
    private javax.swing.JButton btnFuncionarioEditar;
    private javax.swing.JButton btnFuncionarioExcluir;
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
    private javax.swing.JTable tblFuncionario;
    private javax.swing.JTextField txtFuncionarioBairro;
    private javax.swing.JFormattedTextField txtFuncionarioCEP;
    private javax.swing.JFormattedTextField txtFuncionarioCPF;
    private javax.swing.JFormattedTextField txtFuncionarioCelular;
    private javax.swing.JTextField txtFuncionarioEmail;
    private javax.swing.JTextField txtFuncionarioID;
    private javax.swing.JTextField txtFuncionarioNome;
    private javax.swing.JTextField txtFuncionarioNumero;
    private javax.swing.JTextField txtFuncionarioPesquisar;
    private javax.swing.JFormattedTextField txtFuncionarioRG;
    private javax.swing.JTextField txtFuncionariorRua;
    // End of variables declaration//GEN-END:variables
}
