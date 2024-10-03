import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private SecretKey key;

    public Main() {
        setTitle("Kriptoloji Projesi - AES Şifreleme");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputField = new JTextField(20);
        inputPanel.add(new JLabel("Şifrelemek için metin girin:"));
        inputPanel.add(inputField);

        outputArea = new JTextArea(5, 30);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton encryptButton = new JButton("Şifrele");
        JButton decryptButton = new JButton("Çöz");

        try {
            key = CryptoUtils.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                try {
                    String encryptedText = CryptoUtils.encrypt(inputText, key);
                    outputArea.setText("Şifreli Metin: " + encryptedText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encryptedText = outputArea.getText().replace("Şifreli Metin: ", "");
                try {
                    String decryptedText = CryptoUtils.decrypt(encryptedText, key);
                    outputArea.setText("Çözülen Metin: " + decryptedText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
