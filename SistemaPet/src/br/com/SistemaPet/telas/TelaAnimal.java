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
public class TelaAnimal extends javax.swing.JInternalFrame {
// usando a variavel conexao do DAL;

    Connection conexao = null;
    // criando variaveis especiais ´para conexao com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql.*;
    //e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaAnimal() {
       initComponents();
        conexao = ModuloConexao.conector();
    }

  private void adicionar(){
         String sql = "insert into TbAnimal( nomeAnimal,racaAnimal,porteAnimal, pesoAnimal, especieAnimal, alergiasAnimal, "
                 + "medicamentoAnimal, obsAnimal)"
                 + "values(?,?,?,?,?,?,?,?); ";
         try {
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtAnimalNome.getText());
             pst.setString(2, txtAnimalRaca.getText());
             pst.setString(3, cbnAnimalPorte.getSelectedItem().toString());
             pst.setString(4, txtAnimalPeso.getText());
             pst.setString(5, cbnAnimalEspecie.getSelectedItem().toString());
             pst.setString(6, txtAnimalAlergia.getText());
             pst.setString(7, txtAnimalMedicacao.getText());
             pst.setString(8, txtAnimalObs.getText());
          
            if(txtAnimalNome.getText().isEmpty()||txtAnimalRaca.getText().isEmpty()||txtAnimalPeso.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Animal cadastrado com sucesso");
                 txtAnimalNome.setText(null);
                 txtAnimalRaca.setText(null);
                 cbnAnimalPorte.setSelectedItem(null);
                 txtAnimalPeso.setText(null);
                 cbnAnimalEspecie.setSelectedItem(null);
                 txtAnimalAlergia.setText(null);
                 txtAnimalMedicacao.setText(null);
                 txtAnimalObs.setText(null);
                
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar(){
            String sql = "update TbAnimal  set  nomeAnimal = ?,racaAnimal = ?,porteAnimal = ?, pesoAnimal =?, especieAnimal=?, alergiasAnimal = ?, "
                 + "medicamentoAnimal = ?, obsAnimal =? where idAnimal = ? ";
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAnimalNome.getText());
             pst.setString(2, txtAnimalRaca.getText());
             pst.setString(3, cbnAnimalPorte.getSelectedItem().toString());
             pst.setString(4, txtAnimalPeso.getText());
             pst.setString(5, cbnAnimalEspecie.getSelectedItem().toString());
             pst.setString(6, txtAnimalAlergia.getText());
             pst.setString(7, txtAnimalMedicacao.getText());
             pst.setString(8, txtAnimalObs.getText());
             pst.setString(9, txtAnimalID.getText());
             
            if(txtAnimalNome.getText().isEmpty()||txtAnimalRaca.getText().isEmpty()||txtAnimalPeso.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios.");
            }else{
            int adicionado = pst.executeUpdate();
             if (adicionado > 0) {
                 JOptionPane.showMessageDialog(null, "Dados do Animal alterados com sucesso");
                 txtAnimalNome.setText(null);
                 txtAnimalRaca.setText(null);
                 cbnAnimalPorte.setSelectedItem(null);
                 txtAnimalPeso.setText(null);
                 cbnAnimalEspecie.setSelectedItem(null);
                 txtAnimalAlergia.setText(null);
                 txtAnimalMedicacao.setText(null);
                 txtAnimalObs.setText(null);
                 
                 
                 
             }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse animal?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
              String sql = "delete from TbAnimal  where idAnimal = ? ";
              try {
                  pst = conexao.prepareStatement(sql);
                 pst.setString(1, txtAnimalID.getText());
                 pst.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Animal excluido com sucesso");
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
        }
       
    }
    private void pesquisar_campo(){
        String sql = "select * from TbAnimal where nomeAnimal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAnimalPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblAniaml.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
        
    }
     private void setar_campo(){
        int setar = tblAniaml.getSelectedRow();
        txtAnimalID.setText(tblAniaml.getModel().getValueAt(setar, 0).toString());
        txtAnimalNome.setText(tblAniaml.getModel().getValueAt(setar, 1 ).toString());
        txtAnimalRaca.setText(tblAniaml.getModel().getValueAt(setar, 2).toString());
        cbnAnimalPorte.setSelectedItem(tblAniaml.getModel().getValueAt(setar, 3).toString());
        txtAnimalPeso.setText(tblAniaml.getModel().getValueAt(setar, 4).toString());
        cbnAnimalEspecie.setSelectedItem(tblAniaml.getModel().getValueAt(setar, 5 ).toString());
        txtAnimalAlergia.setText(tblAniaml.getModel().getValueAt(setar, 6).toString());
        txtAnimalMedicacao.setText(tblAniaml.getModel().getValueAt(setar, 7 ).toString());
        txtAnimalObs.setText(tblAniaml.getModel().getValueAt(setar, 8 ).toString());
       
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAnimalPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtAnimalID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAniaml = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbnAnimalPorte = new javax.swing.JComboBox<>();
        txtAnimalNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAnimalPeso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAnimalRaca = new javax.swing.JTextField();
        cbnAnimalEspecie = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtAnimalAlergia = new javax.swing.JTextField();
        txtAnimalMedicacao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnimalObs = new javax.swing.JTextArea();
        btnAnimalEditar = new javax.swing.JButton();
        btnAnimalAdicionar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnAnimalExcluir = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setForeground(new java.awt.Color(240, 240, 240));
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Animal");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(872, 857));

        txtAnimalPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnimalPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setText("*ID:");

        txtAnimalID.setEnabled(false);

        jLabel2.setText("*Campo Obrigatorio");

        tblAniaml.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Raça", "Peso", "Especie", "Alergias", "Medicamentos", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAniaml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAniamlMouseClicked(evt);
            }
        });
        tblAniaml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAniamlKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblAniaml);

        jLabel3.setText("*Porte:");

        jLabel4.setText("*Nome:");

        jLabel5.setText("Alergia:");

        cbnAnimalPorte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequeno", "Médio", "Grande" }));
        cbnAnimalPorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnAnimalPorteActionPerformed(evt);
            }
        });

        jLabel6.setText("*Peso:");

        jLabel7.setText("*Especie:");

        cbnAnimalEspecie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Canina", "Felina" }));

        jLabel8.setText("*Raça:");

        jLabel9.setText("Mediação:");

        jLabel10.setText("Observação:");

        txtAnimalObs.setColumns(20);
        txtAnimalObs.setRows(5);
        jScrollPane2.setViewportView(txtAnimalObs);

        btnAnimalEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/editar lapis black 128px.png"))); // NOI18N
        btnAnimalEditar.setToolTipText("Editar");
        btnAnimalEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnimalEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnimalEditarActionPerformed(evt);
            }
        });

        btnAnimalAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/addBlack128px.png"))); // NOI18N
        btnAnimalAdicionar.setToolTipText("Adicionar");
        btnAnimalAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnimalAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnimalAdicionarActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/searchBlue48px.png"))); // NOI18N

        btnAnimalExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/delete X Black 128px.png"))); // NOI18N
        btnAnimalExcluir.setToolTipText("Deletar");
        btnAnimalExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnimalExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnimalExcluirActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 100)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("LOGO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtAnimalPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(390, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(327, 327, 327)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnimalID, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAnimalAlergia)
                            .addComponent(txtAnimalMedicacao)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(txtAnimalNome)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel3)
                        .addGap(35, 35, 35)
                        .addComponent(cbnAnimalPorte, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAnimalPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbnAnimalEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnimalRaca, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(btnAnimalAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnAnimalEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnAnimalExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbnAnimalEspecie, cbnAnimalPorte});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnimalAdicionar, btnAnimalEditar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(190, 190, 190))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAnimalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(txtAnimalPesquisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtAnimalRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbnAnimalEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(cbnAnimalPorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtAnimalPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAnimalNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAnimalAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtAnimalMedicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnimalEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnimalAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnimalExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAnimalAdicionar, btnAnimalEditar});

        setBounds(0, 0, 1405, 970);
    }// </editor-fold>//GEN-END:initComponents

    private void cbnAnimalPorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnAnimalPorteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbnAnimalPorteActionPerformed

    private void btnAnimalAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnimalAdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btnAnimalAdicionarActionPerformed

    private void btnAnimalEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnimalEditarActionPerformed
        alterar();
    }//GEN-LAST:event_btnAnimalEditarActionPerformed

    private void btnAnimalExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnimalExcluirActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnAnimalExcluirActionPerformed

    private void tblAniamlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAniamlKeyReleased
       
    }//GEN-LAST:event_tblAniamlKeyReleased

    private void txtAnimalPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnimalPesquisarKeyReleased
        pesquisar_campo();
    }//GEN-LAST:event_txtAnimalPesquisarKeyReleased

    private void tblAniamlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAniamlMouseClicked
 setar_campo();
    }//GEN-LAST:event_tblAniamlMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnimalAdicionar;
    private javax.swing.JButton btnAnimalEditar;
    private javax.swing.JButton btnAnimalExcluir;
    private javax.swing.JComboBox<String> cbnAnimalEspecie;
    private javax.swing.JComboBox<String> cbnAnimalPorte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAniaml;
    private javax.swing.JTextField txtAnimalAlergia;
    private javax.swing.JTextField txtAnimalID;
    private javax.swing.JTextField txtAnimalMedicacao;
    private javax.swing.JTextField txtAnimalNome;
    private javax.swing.JTextArea txtAnimalObs;
    private javax.swing.JTextField txtAnimalPeso;
    private javax.swing.JTextField txtAnimalPesquisar;
    private javax.swing.JTextField txtAnimalRaca;
    // End of variables declaration//GEN-END:variables
}
