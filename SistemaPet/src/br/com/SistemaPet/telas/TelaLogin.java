/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SistemaPet.telas;

import br.com.SistemaPetShop.dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Julia Fideles
 */
public class TelaLogin extends javax.swing.JFrame {

   private static final long serialVersionUID = 1L;
    // usando a variavel conexao do DAL;
    Connection conexao = null;
    // criando variaveis especiais ´para conexao com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql.*;
    //e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    // metodo para logar
    public void logar() {
        //logica principal para pesquisar no banco de dados
        String sql = "select * from TbUsuario where loginUsuario = ? and senhaUsuario = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuarioLogin.getText());
            pst.setString(2, txtUsuarioSenha.getText());
            // a linha abaixo executa a query;
            rs = pst.executeQuery();
            //se existir usuario e senha correspondente
            if (rs.next()) {
                //a linha abaixo obtem o conteudo do campo perfil da tabela tbusuario;
                String perfil = rs.getString(4);
                //a linha abaixo obtem o conteudo do campo usuarios da tabela tbusuario;
                String usuarios = rs.getString(5);
                //System.out.println(perfil);
                //a estrutura abaixo faz o tratamento do perfil do usuario
               if (perfil.equals("Admin")) {
                    //as linhas abaixo instanciam um novo JFrame
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menCadastroFuncionario.setEnabled(true);
                    TelaPrincipal.menCadastroUsuario.setEnabled(true);
                   // TelaPrincipal.btnMenuRelatorio.setEnabled(true);
                    TelaPrincipal.lblMenuUsuario.setText(rs.getString(5));
                    TelaPrincipal.lblMenuUsuario.setForeground(Color.RED);
                    this.dispose();
                    conexao.close();
                    
                }else{
                   TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.lblMenuUsuario.setText(rs.getString(5));
                    this.dispose();
                    conexao.close();
               }
            }else{
                JOptionPane.showMessageDialog(null,"Usuário e/ou senha inválido(s)");
            }
            }
         catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
        } 
    public TelaLogin() {
        initComponents();
        // fazendo conexao
        conexao = ModuloConexao.conector();
        // auxilio de comunicação
        //System.out.println(conexao);
        if (conexao != null) {
            lblUsuarioStatus.setText("Conectado");
        } else {
            lblUsuarioStatus.setText("Não Conectado");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuarioLogin = new javax.swing.JLabel();
        lblUsuarioSenha = new javax.swing.JLabel();
        txtUsuarioLogin = new javax.swing.JTextField();
        lblUsuarioStatus = new javax.swing.JLabel();
        btnUsuarioLogin = new javax.swing.JButton();
        txtUsuarioSenha = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUsuarioLogin.setText("Login:");

        lblUsuarioSenha.setText("Senha:");

        lblUsuarioStatus.setText("Status");

        btnUsuarioLogin.setText("Login");
        btnUsuarioLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuarioLoginMouseClicked(evt);
            }
        });
        btnUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioLoginActionPerformed(evt);
            }
        });
        btnUsuarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnUsuarioLoginKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 100)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/WhatsApp_Image_2020-11-17_at_20.10.03-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblUsuarioStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuarioLogin)
                    .addComponent(lblUsuarioSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsuarioLogin)
                    .addComponent(txtUsuarioSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioLogin)
                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuarioSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioStatus)
                    .addComponent(btnUsuarioLogin))
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuarioLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuarioLoginMouseClicked
       logar();
    }//GEN-LAST:event_btnUsuarioLoginMouseClicked

    private void btnUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUsuarioLoginActionPerformed

    private void btnUsuarioLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUsuarioLoginKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUsuarioLoginKeyPressed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuarioLogin;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel lblUsuarioLogin;
    private javax.swing.JLabel lblUsuarioSenha;
    private javax.swing.JLabel lblUsuarioStatus;
    private javax.swing.JTextField txtUsuarioLogin;
    private javax.swing.JPasswordField txtUsuarioSenha;
    // End of variables declaration//GEN-END:variables
}
