package br.com.scriptmanagercaproni.view;

import br.com.scriptmanagercaproni.components.FileChooserDirectory;
import br.com.scriptmanagercaproni.control.CaproniConfigurationControl;
import br.com.scriptmanagercaproni.control.ScriptFolderControl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SQLToolView extends JFrame {

    private static final long serialVersionUID = -7439009097407169326L;
    private JTextField txCaproniPath;

    public static void main(@SuppressWarnings("deprecation") String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SQLToolView frame = new SQLToolView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SQLToolView() {
        CaproniConfigurationControl caproniConfigurationControl = new CaproniConfigurationControl();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 467, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbCaproniFolder = new JLabel("Script Folder");
        lbCaproniFolder.setBounds(12, 14, 78, 15);
        contentPane.add(lbCaproniFolder);

        txCaproniPath = new JTextField();
        txCaproniPath.setBounds(108, 12, 275, 19);
        contentPane.add(txCaproniPath);
        txCaproniPath.setColumns(10);
        txCaproniPath.setEditable(false);
        txCaproniPath.setText(caproniConfigurationControl.getPath());
        JButton btChooseCaproniFolder = new JButton("...");
        btChooseCaproniFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                preencheCaminhoPath(txCaproniPath);
            }
        });
        btChooseCaproniFolder.setBounds(395, 9, 43, 25);
        contentPane.add(btChooseCaproniFolder);

        JButton btFechar = new JButton("Fechar");
        btFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btFechar.setBounds(134, 230, 98, 25);
        contentPane.add(btFechar);

        JButton btGerar = new JButton("Gerar");
        btGerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                geraSQL(new ScriptFolderControl(txCaproniPath.getText()).listaScripts());
            }
        });
        btGerar.setBounds(24, 230, 98, 25);
        contentPane.add(btGerar);
    }

    private void preencheCaminhoPath(JTextField txCaproniPath) {
        JFileChooser chooser = new FileChooserDirectory(txCaproniPath.getText());
        txCaproniPath.setText(chooser.getSelectedFile().getPath());
    }

    private void geraSQL(@SuppressWarnings("deprecation") List<String> listaScripts) {
        @SuppressWarnings("deprecation") StringBuilder sql = new StringBuilder("select * from epadscriptrodado where nmscript in (");
        for (@SuppressWarnings("deprecation") String script : listaScripts) {
            sql.append("'").append(script).append("',");
        }

        //noinspection deprecation
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(");");
        System.out.println(sql);
    }
}
