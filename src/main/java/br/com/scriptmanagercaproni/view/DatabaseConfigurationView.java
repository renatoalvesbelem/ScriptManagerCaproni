package br.com.scriptmanagercaproni.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.scriptmanagercaproni.components.FileChooserFile;

@SuppressWarnings("Since15")
public class DatabaseConfigurationView extends JFrame {

    private static final long serialVersionUID = -7439009097407169326L;

    private JTextField txDatabaseFile;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseConfigurationView frame = new DatabaseConfigurationView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DatabaseConfigurationView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 587, 381);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setBounds(121, 42, 414, 24);
        contentPane.add(comboBox);

        JLabel lblTipodeDeBanco = new JLabel("Tipo de Banco");
        lblTipodeDeBanco.setBounds(24, 47, 94, 15);
        contentPane.add(lblTipodeDeBanco);

        txDatabaseFile = new JTextField();
        txDatabaseFile.setBounds(121, 89, 377, 24);
        contentPane.add(txDatabaseFile);
        txDatabaseFile.setColumns(10);

        JButton button = new JButton("...");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new FileChooserFile();

                @SuppressWarnings("deprecation") String pathScriptFolder;
                pathScriptFolder = chooser.getSelectedFile().getPath();
                txDatabaseFile.setText(pathScriptFolder);
            }
        });
        button.setBounds(510, 88, 25, 25);
        contentPane.add(button);
    }
}