package br.com.scriptmanagercaproni.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.scriptmanagercaproni.components.FileChooserFile;
import br.com.scriptmanagercaproni.control.DatabaseCatalogControl;
import br.com.scriptmanagercaproni.model.DatabaseModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

public class DatabaseCatalogView extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtNameCatalog;
    private JTextField txCaproniPath;
    private JTable table;
    private DatabaseCatalogControl databaseCatalogControl;

    public static void main(@SuppressWarnings("deprecation") String[] args) {
        try {
            DatabaseCatalogView dialog = new DatabaseCatalogView();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DatabaseCatalogView() {
        setBounds(100, 100, 734, 486);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        databaseCatalogControl = new DatabaseCatalogControl();
        JLabel lblNewLabel = new JLabel("Nome Catalogo");
        lblNewLabel.setBounds(10, 11, 101, 14);
        contentPanel.add(lblNewLabel);

        txtNameCatalog = new JTextField();
        txtNameCatalog.setBounds(121, 8, 535, 20);
        contentPanel.add(txtNameCatalog);
        txtNameCatalog.setColumns(10);

        JLabel lblArquivoCatalogo = new JLabel("Arquivo Catalogo");
        lblArquivoCatalogo.setBounds(10, 39, 101, 14);
        contentPanel.add(lblArquivoCatalogo);

        txCaproniPath = new JTextField();
        txCaproniPath.setEditable(false);
        txCaproniPath.setColumns(10);
        txCaproniPath.setBounds(121, 36, 535, 20);
        contentPanel.add(txCaproniPath);

        JButton btnNewButton = new JButton("...");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                preencheCaminhoArquivoETabela(txCaproniPath, table);
            }
        });
        btnNewButton.setBounds(666, 35, 41, 23);
        contentPanel.add(btnNewButton);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 95, 646, 240);
        contentPanel.add(scrollPane);

        @SuppressWarnings("deprecation") String[] columnNames = {"ALIAS", "JDBC"};
        Object[][] data = {};
        table = new JTable(data, columnNames);
        table.setEnabled(false);
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Catalogar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!txtNameCatalog.getText().equals("") && table.getRowCount() != 0) {
                    if (databaseCatalogControl.catalogDatabase(new File(txCaproniPath.getText()),
                            txtNameCatalog.getText())) {
                        limpaDados();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Informar dados obrigatórios");
                }
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Fechar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }


    private void preencheCaminhoArquivoETabela(JTextField txCaproniPath, JTable table) {
        JFileChooser chooser = new FileChooserFile();
        txCaproniPath.setText(chooser.getSelectedFile().getPath());
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            List<DatabaseModel> databaseModels = databaseCatalogControl.listaDatabaseModel(new File(txCaproniPath.getText()));
            for (DatabaseModel databaseModel : databaseModels) {
                model.addRow(
                        new Object[]{databaseModel.getDatabaseAlias(), databaseModel.getDatabaseJDBC()});
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void limpaDados() {
        txCaproniPath.setText("");
        txtNameCatalog.setText("");
        table.removeAll();
        repaint();
    }
}
