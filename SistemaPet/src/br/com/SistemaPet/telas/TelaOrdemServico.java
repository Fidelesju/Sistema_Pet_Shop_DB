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
public class TelaOrdemServico extends javax.swing.JInternalFrame {

    Connection conexao = null;
    // criando variaveis especiais ´para conexao com o banco
    //Prepared Statement e ResultSet são frameworks do pacote java.sql.*;
    //e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;
    private String tipo;
    private String OSOrcamento;

    /**
     * @return the tipo
     */
    public String getTipo() {
        return OSOrcamento;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.OSOrcamento = tipo;
    }

    public TelaOrdemServico() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void pesquisar_animal() {
        String sql = "select idAnimal as ID, nomeAnimal as Nome, porteAnimal as Porte, alergiasAnimal as Alergias, obsAnimal as Observação from TbAnimal where nomeAnimal like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtOSAnimalPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblOSAnimal.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }

    }

    private void setar_campo_animal() {
        int setar = tblOSAnimal.getSelectedRow();
        txtOSAnimalID.setText(tblOSAnimal.getModel().getValueAt(setar, 0).toString());
    }

    private void pesquisar_campo_cliente() {
        String sql = "select idCliente as ID, nomeCliente as Nome, telefoneCliente as Telefone, CPFCliente as CPF from TbClientes where nomeCliente like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtOSClientePesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblOSCliente.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_campo_cliente() {
        int setar = tblOSCliente.getSelectedRow();
        txtOSClienteID.setText(tblOSCliente.getModel().getValueAt(setar, 0).toString());
    }

    private void emitir_os() {
        String sql = "insert into OrdemServico(tipoOS, situacaoOS, funcionarioResponsavelOS, servicoOS,valorOS, idClienteOS,idFuncionarioOS, idAnimalOS )"
                + "values(?,?,?,?,?,?,?,?);";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cbnOSSituacao.getSelectedItem().toString());
            pst.setString(3, txtOSFuncionario.getText());
            pst.setString(4, cbnOSServico.getSelectedItem().toString());
            pst.setString(5, txtOSValor.getText());
            pst.setString(6, txtOSClienteID.getText());
            pst.setString(7, txtOSFuncionarioID.getText());
            pst.setString(8, txtOSAnimalID.getText());
            if (txtOSFuncionario.getText().isEmpty() || txtOSValor.getText().isEmpty() || txtOSClienteID.getText().isEmpty() || txtOSFuncionarioID.getText().isEmpty()
                    || txtOSAnimalID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço emitida com sucesso");
                    txtOSFuncionario.setText(null);
                    txtOSValor.setText(null);
                    txtOSClienteID.setText(null);
                    txtOSFuncionarioID.setText(null);
                    txtOSAnimalID.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);

        }
    }

    private void pesquisar_os() {
        String num_os = JOptionPane.showInputDialog("Número da ordem de serviço");
        String sql = "select * from OrdemServico where idOS = " + num_os;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOSServicoNumero.setText(rs.getString(1));
                txtOSServicoData.setText(rs.getString(2));
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("Ordem de Serviço")) {
                    rbtOS.setSelected(true);
                    tipo = "Ordem de Serviço";

                } else {
                    rbtOSOrcamento.setSelected(true);
                    tipo = "Orçamento";
                }
                cbnOSSituacao.setSelectedItem(rs.getString(4));
                txtOSFuncionario.setText(rs.getString(5));
                cbnOSServico.setSelectedItem(rs.getString(6));
                txtOSValor.setText(rs.getString(7));
                txtOSClienteID.setText(rs.getString(8));
                txtOSFuncionarioID.setText(rs.getString(9));
                txtOSAnimalID.setText(rs.getString(10));
                btnOSAdicionar.setEnabled(false);
                txtOSClientePesquisar.setEnabled(false);
                txtOSAnimalPesquisar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Ordem de Serviço não cadastrada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar(){
        String sql = "update OrdemServico set tipoOS = ?, situacaoOS = ?, funcionarioResponsavelOS = ?, servicoOS = ?, valorOS = ?, idClienteOS = ?,"
                + "idFuncionarioOS = ?, idAnimalOS = ? where idOS = ?; ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cbnOSSituacao.getSelectedItem().toString());
            pst.setString(3, txtOSFuncionario.getText());
            pst.setString(4, cbnOSServico.getSelectedItem().toString());
            pst.setString(5, txtOSValor.getText());
            pst.setString(6, txtOSClienteID.getText());
            pst.setString(7, txtOSFuncionarioID.getText());
            pst.setString(8, txtOSAnimalID.getText());
            pst.setString(9, txtOSServicoNumero.getText());
            if (txtOSFuncionario.getText().isEmpty() || txtOSValor.getText().isEmpty() || txtOSClienteID.getText().isEmpty() || txtOSFuncionarioID.getText().isEmpty()
                    || txtOSAnimalID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço alterada com sucesso");
                    txtOSFuncionario.setText(null);
                    txtOSValor.setText(null);
                    txtOSClienteID.setText(null);
                    txtOSFuncionarioID.setText(null);
                    txtOSAnimalID.setText(null);
                    btnOSAdicionar.setEnabled(true);
                txtOSClientePesquisar.setEnabled(true);
                txtOSAnimalPesquisar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);

        }
    }
    private void excluir (){
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir essa ordem de serviço?","Atenção",JOptionPane.YES_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
             String sql = "delete from OrdemServico where idOs = ?";
             try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOSServicoNumero.getText());
                int apagado = pst.executeUpdate();
                 if (apagado > 0) {
                     JOptionPane.showMessageDialog(null, "Ordem de serviço excluida com sucesso");
                     txtOSFuncionario.setText(null);
                    txtOSValor.setText(null);
                    txtOSClienteID.setText(null);
                    txtOSFuncionarioID.setText(null);
                    txtOSAnimalID.setText(null);
                 }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
       
    }
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOSServicoNumero = new javax.swing.JTextField();
        txtOSServicoData = new javax.swing.JTextField();
        rbtOSOrcamento = new javax.swing.JRadioButton();
        rbtOS = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cbnOSSituacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtOSClientePesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtOSClienteID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOSCliente = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtOSAnimalPesquisar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtOSAnimalID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOSAnimal = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtOSFuncionario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbnOSServico = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtOSValor = new javax.swing.JTextField();
        btnOSlExcluir = new javax.swing.JButton();
        btnOSEditar = new javax.swing.JButton();
        btnOSAdicionar = new javax.swing.JButton();
        btnOSBuscar = new javax.swing.JButton();
        btnOSImprimir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtOSFuncionarioID = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(872, 857));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Serviço", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        jLabel1.setText("Nº os;");

        jLabel2.setText("Data:");

        txtOSServicoNumero.setEnabled(false);

        txtOSServicoData.setEnabled(false);

        buttonGroup1.add(rbtOSOrcamento);
        rbtOSOrcamento.setText("Orçamento");
        rbtOSOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOSOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOS);
        rbtOS.setText("Ordem de Serviço");
        rbtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOSActionPerformed(evt);
            }
        });

        jLabel3.setText("Situação:");

        cbnOSSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Internado", "Cirurgia", "Medicamentos", "Hospedado", " ", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(rbtOSOrcamento)
                            .addComponent(txtOSServicoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(rbtOS)
                                .addGap(39, 39, 39))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOSServicoData, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbnOSSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOSServicoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOSServicoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOSOrcamento)
                    .addComponent(rbtOS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbnOSSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        txtOSClientePesquisar.setToolTipText("");
        txtOSClientePesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOSClientePesquisarKeyReleased(evt);
            }
        });

        jLabel4.setText("ID:");

        txtOSClienteID.setEnabled(false);

        tblOSCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblOSCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOSClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOSCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtOSClientePesquisar)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtOSClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOSClientePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtOSClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(23, 23, 23)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Animal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        txtOSAnimalPesquisar.setToolTipText("");
        txtOSAnimalPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOSAnimalPesquisarKeyReleased(evt);
            }
        });

        jLabel5.setText("ID:");

        txtOSAnimalID.setEnabled(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        tblOSAnimal.setModel(new javax.swing.table.DefaultTableModel(
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
        tblOSAnimal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOSAnimalMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblOSAnimal);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1329, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtOSAnimalPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOSAnimalID, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOSAnimalPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtOSAnimalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setText("*Funcionario Responsavel:");

        jLabel7.setText("*Serviço:");

        cbnOSServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banho e Tosa", "Farmacia", "Hotel", "Hospital", "Jazigo", "Taxi Pet", "Vacinação", " " }));

        jLabel8.setText("Valor Total:");

        btnOSlExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/delete X Black 128px.png"))); // NOI18N
        btnOSlExcluir.setToolTipText("Deletar");
        btnOSlExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSlExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSlExcluirActionPerformed(evt);
            }
        });

        btnOSEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/editar lapis black 128px.png"))); // NOI18N
        btnOSEditar.setToolTipText("Editar");
        btnOSEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSEditarActionPerformed(evt);
            }
        });

        btnOSAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/addBlack128px.png"))); // NOI18N
        btnOSAdicionar.setToolTipText("Adicionar");
        btnOSAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSAdicionarActionPerformed(evt);
            }
        });

        btnOSBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/search black 128px.png"))); // NOI18N
        btnOSBuscar.setToolTipText("Buscar");
        btnOSBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSBuscarActionPerformed(evt);
            }
        });

        btnOSImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/SistemaPet/icons/print impressora black 128px.png"))); // NOI18N
        btnOSImprimir.setToolTipText("Imprimir");
        btnOSImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSImprimirActionPerformed(evt);
            }
        });

        jLabel9.setText("ID:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 100)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("LOGO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOSFuncionario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOSFuncionarioID, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnOSAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOSEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnOSlExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOSBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnOSImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(288, 288, 288))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbnOSServico, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtOSValor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(422, 422, 422))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(432, 432, 432))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOSFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtOSFuncionarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbnOSServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtOSValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOSEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSlExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        setBounds(0, 0, 1405, 970);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOSlExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSlExcluirActionPerformed
        // TODO add your handling code here:
       excluir();
    }//GEN-LAST:event_btnOSlExcluirActionPerformed

    private void btnOSEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSEditarActionPerformed
        alterar();
    }//GEN-LAST:event_btnOSEditarActionPerformed

    private void btnOSAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSAdicionarActionPerformed
        emitir_os();
    }//GEN-LAST:event_btnOSAdicionarActionPerformed

    private void btnOSBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSBuscarActionPerformed
        pesquisar_os();
    }//GEN-LAST:event_btnOSBuscarActionPerformed

    private void btnOSImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOSImprimirActionPerformed

    private void txtOSClientePesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOSClientePesquisarKeyReleased
        pesquisar_campo_cliente();
    }//GEN-LAST:event_txtOSClientePesquisarKeyReleased

    private void txtOSAnimalPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOSAnimalPesquisarKeyReleased
        // pesquisar_campo_animal();
        pesquisar_animal();
    }//GEN-LAST:event_txtOSAnimalPesquisarKeyReleased

    private void tblOSClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOSClienteMouseClicked
        setar_campo_cliente();
    }//GEN-LAST:event_tblOSClienteMouseClicked

    private void tblOSAnimalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOSAnimalMouseClicked
        setar_campo_animal();
    }//GEN-LAST:event_tblOSAnimalMouseClicked

    private void rbtOSOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOSOrcamentoActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_rbtOSOrcamentoActionPerformed

    private void rbtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOSActionPerformed
        tipo = "OS";
    }//GEN-LAST:event_rbtOSActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        rbtOSOrcamento.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOSAdicionar;
    private javax.swing.JButton btnOSBuscar;
    private javax.swing.JButton btnOSEditar;
    private javax.swing.JButton btnOSImprimir;
    private javax.swing.JButton btnOSlExcluir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbnOSServico;
    private javax.swing.JComboBox<String> cbnOSSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    public static javax.swing.JRadioButton rbtOS;
    public static javax.swing.JRadioButton rbtOSOrcamento;
    private javax.swing.JTable tblOSAnimal;
    private javax.swing.JTable tblOSCliente;
    private javax.swing.JTextField txtOSAnimalID;
    private javax.swing.JTextField txtOSAnimalPesquisar;
    private javax.swing.JTextField txtOSClienteID;
    private javax.swing.JTextField txtOSClientePesquisar;
    private javax.swing.JTextField txtOSFuncionario;
    private javax.swing.JTextField txtOSFuncionarioID;
    private javax.swing.JTextField txtOSServicoData;
    private javax.swing.JTextField txtOSServicoNumero;
    private javax.swing.JTextField txtOSValor;
    // End of variables declaration//GEN-END:variables
}
