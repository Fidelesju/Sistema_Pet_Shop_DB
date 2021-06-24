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

/**
 *
 * @author Julia Fideles
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    // usando a variavel conexao do DAL;

    Connection conexao = null;
    // criando variaveis especiais ´para conexao com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql.*;
    //e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    private void consultar(){
        String sql = "select * from TbUsuario where idUsuario = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuarioID.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                txtUsuarioNome.setText(rs.getString(5));
                txtUsuarioLogin.setText(rs.getString(2));
                txtUsuarioSenha.setText(rs.getString(3));
                cbnUsuarioPerfil.setSelectedItem(4);
            }else{
                JOptionPane.showMessageDialog(null,"Usuário não cadastrado");
                 txtUsuarioNome.setText(null);
                txtUsuarioLogin.setText(null);
                txtUsuarioSenha.setText(null);
                cbnUsuarioPerfil.setSelectedItem(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void adicionar(){
         String sql = "insert into TbUsuario(idUsuario, loginUsuario,senhaUsuario, perfilUsuario, nomeUsuario)"
                 + "values(?,?,?,?,?); ";
         try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuarioID.getText());
            pst.setString(2, txtUsuarioLogin.getText());
            pst.setString(3, txtUsuarioSenha.getText());
            pst.setString(4, cbnUsuarioPerfil.getSelectedItem().toString());
            pst.setString(5, txtUsuarioNome.getText());
            if(txtUsuarioID.getText().isEmpty()||txtUsuarioLogin.getText().isEmpty()||txtUsuarioNome.getText().isEmpty()||txtUsuarioSenha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                 txtUsuarioID.setText(null);
                 txtUsuarioNome.setText(null);
                 txtUsuarioLogin.setText(null);
                 txtUsuarioSenha.setText(null);
                 cbnUsuarioPerfil.setSelectedItem(null);
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar(){
            String sql = "update TbUsuario  set loginUsuario = ?,senhaUsuario = ?, perfilUsuario =?, nomeUsuario= ? where idUsuario = ? ";
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuarioLogin.getText());
            pst.setString(2, txtUsuarioSenha.getText());
            pst.setString(3, cbnUsuarioPerfil.getSelectedItem().toString());
            pst.setString(4, txtUsuarioNome.getText());
            pst.setString(5, txtUsuarioID.getText());
            if(txtUsuarioID.getText().isEmpty()||txtUsuarioLogin.getText().isEmpty()||txtUsuarioNome.getText().isEmpty()||txtUsuarioSenha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                 txtUsuarioID.setText(null);
                 txtUsuarioNome.setText(null);
                 txtUsuarioLogin.setText(null);
                 txtUsuarioSenha.setText(null);
                 cbnUsuarioPerfil.setSelectedItem(null);
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
              String sql = "delete from TbUsuario  where idUsuario = ? ";
              try {
                  pst = conexao.prepareStatement(sql);
                 pst.setString(1, txtUsuarioID.getText());
                 pst.executeUpdate();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
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

        lblUsuarioNome = new javax.swing.JLabel();
        lblUsuarioLogin = new javax.swing.JLabel();
        lblUsuarioSenha = new javax.swing.JLabel();
        lblUsuarioPerfil = new javax.swing.JLabel();
        txtUsuarioNome = new javax.swing.JTextField();
        txtUsuarioLogin = new javax.swing.JTextField();
        txtUsuarioSenha = new javax.swing.JTextField();
        cbnUsuarioPerfil = new javax.swing.JComboBox<>();
        btnUsuarioProcurar = new javax.swing.JButton();
        btnUsuarioAdicionar = new javax.swing.JButton();
        btnUsuarioExcluir = new javax.swing.JButton();
        lblUsuarioID = new javax.swing.JLabel();
        txtUsuarioID = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(872, 857));

        lblUsuarioNome.setText("*Nome:");

        lblUsuarioLogin.setText("*Login:");

        lblUsuarioSenha.setText("*Senha:");

        lblUsuarioPerfil.setText("*Perfil:");

        cbnUsuarioPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        btnUsuarioProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/search black 128px.png"))); // NOI18N
        btnUsuarioProcurar.setToolTipText("Procurar");
        btnUsuarioProcurar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuarioProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioProcurarActionPerformed(evt);
            }
        });

        btnUsuarioAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/addBlack128px.png"))); // NOI18N
        btnUsuarioAdicionar.setToolTipText("Adicionar");
        btnUsuarioAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuarioAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioAdicionarActionPerformed(evt);
            }
        });

        btnUsuarioExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/delete X Black 128px.png"))); // NOI18N
        btnUsuarioExcluir.setToolTipText("Deletar");
        btnUsuarioExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuarioExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioExcluirActionPerformed(evt);
            }
        });

        lblUsuarioID.setText("ID:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/editar lapis black 128px.png"))); // NOI18N
        jButton2.setToolTipText("Editar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("*Campo Obrigatorio");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 100)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("LOGO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 314, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnUsuarioAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnUsuarioExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnUsuarioProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuarioNome)
                            .addComponent(lblUsuarioLogin)
                            .addComponent(lblUsuarioSenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuarioSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblUsuarioPerfil)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbnUsuarioPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(lblUsuarioID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(204, 204, 204))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))))
                .addGap(225, 225, 225)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioPerfil)
                    .addComponent(cbnUsuarioPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioNome)
                    .addComponent(txtUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioLogin)
                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioSenha)
                    .addComponent(txtUsuarioSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUsuarioProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUsuarioExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUsuarioAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 1405, 970);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuarioProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioProcurarActionPerformed
       //chamando o metodo consultar
        consultar();
    }//GEN-LAST:event_btnUsuarioProcurarActionPerformed

    private void btnUsuarioAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnUsuarioAdicionarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        alterar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnUsuarioExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioExcluirActionPerformed
       deletar();
    }//GEN-LAST:event_btnUsuarioExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuarioAdicionar;
    private javax.swing.JButton btnUsuarioExcluir;
    private javax.swing.JButton btnUsuarioProcurar;
    private javax.swing.JComboBox<String> cbnUsuarioPerfil;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblUsuarioID;
    private javax.swing.JLabel lblUsuarioLogin;
    private javax.swing.JLabel lblUsuarioNome;
    private javax.swing.JLabel lblUsuarioPerfil;
    private javax.swing.JLabel lblUsuarioSenha;
    private javax.swing.JTextField txtUsuarioID;
    private javax.swing.JTextField txtUsuarioLogin;
    private javax.swing.JTextField txtUsuarioNome;
    private javax.swing.JTextField txtUsuarioSenha;
    // End of variables declaration//GEN-END:variables
}
