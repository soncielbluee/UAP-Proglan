import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PemesananTiketKonserelsa {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Pemesanan Tiket Konser");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);

            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            mainPanel.setBackground(new Color(156, 156, 174)); // Warna latar belakang utama (biru muda pucat)

            JPanel inputPanel = new JPanel(new GridBagLayout());
            inputPanel.setBorder(BorderFactory.createTitledBorder("Pesan Tiket"));
            inputPanel.setBackground(new Color(216, 216, 221)); // Warna latar belakang panel input
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel nameLabel = new JLabel("Nama Lengkap:");
            JTextField nameField = new JTextField(20);

            JLabel ticketLabel = new JLabel("Jumlah Tiket:");
            JTextField ticketField = new JTextField(20);

            JLabel emailLabel = new JLabel("Email:");
            JTextField emailField = new JTextField(20);

            JLabel concertLabel = new JLabel("Konser:");
            String[] concerts = {"Seventeen 'Right Here'", "NCT Dream 'The Dream Show 4'", "Treasure 'Reeboot'"};
            JComboBox<String> concertComboBox = new JComboBox<>(concerts);

            JLabel paymentLabel = new JLabel("Metode Pembayaran:");
            String[] payments = {"QRIS", "Transfer Bank"};
            JComboBox<String> paymentComboBox = new JComboBox<>(payments);

            JButton orderButton = new JButton("Tambah");
            JButton updateButton = new JButton("Update");
            JButton deleteButton = new JButton("Hapus");

            gbc.gridx = 0;
            gbc.gridy = 0;
            inputPanel.add(nameLabel, gbc);
            gbc.gridx = 1;
            inputPanel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            inputPanel.add(ticketLabel, gbc);
            gbc.gridx = 1;
            inputPanel.add(ticketField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            inputPanel.add(emailLabel, gbc);
            gbc.gridx = 1;
            inputPanel.add(emailField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            inputPanel.add(concertLabel, gbc);
            gbc.gridx = 1;
            inputPanel.add(concertComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            inputPanel.add(paymentLabel, gbc);
            gbc.gridx = 1;
            inputPanel.add(paymentComboBox, gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            inputPanel.add(orderButton, gbc);
            gbc.gridy = 6;
            inputPanel.add(updateButton, gbc);
            gbc.gridy = 7;
            inputPanel.add(deleteButton, gbc);

            String[] columns = {"Nama", "Jumlah Tiket", "Konser", "Harga", "Pembayaran", "Email"};
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
            JTable table = new JTable(tableModel);
            JScrollPane tableScrollPane = new JScrollPane(table);
            tableScrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Pemesanan"));
            tableScrollPane.getViewport().setBackground(new Color(216, 216, 221)); // Warna latar belakang tabel

            mainPanel.add(inputPanel, BorderLayout.WEST);
            mainPanel.add(tableScrollPane, BorderLayout.CENTER);

            orderButton.addActionListener(e -> {
                String name = nameField.getText().trim();
                String ticketText = ticketField.getText().trim();
                String email = emailField.getText().trim();
                String concert = (String) concertComboBox.getSelectedItem();
                String payment = (String) paymentComboBox.getSelectedItem();

                if (name.isEmpty() || ticketText.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int tickets = Integer.parseInt(ticketText);
                    if (tickets <= 0) throw new NumberFormatException();

                    int price = calculatePrice(concert, tickets);

                    if ("QRIS".equals(payment)) {
                        int response = JOptionPane.showConfirmDialog(frame, "Pembayaran QRIS: Silakan lakukan scan untuk membayar.", "Informasi", JOptionPane.OK_CANCEL_OPTION);
                        if (response == JOptionPane.OK_OPTION) {
                            JDialog qrisDialog = new JDialog(frame, "QRIS Pembayaran", true);
                            qrisDialog.setLayout(new BorderLayout(10, 10));

                            JLabel qrisLabel = new JLabel();
                            qrisLabel.setHorizontalAlignment(JLabel.CENTER);
                            String imagePath = "C:\\Users\\elsaaa\\OneDrive\\Pictures\\Camera Roll\\WhatsApp Image 2024-12-18 at 10.02.40_e9e9fcfd.jpg";
                            ImageIcon originalIcon = new ImageIcon(imagePath);
                            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                            qrisLabel.setIcon(new ImageIcon(scaledImage));

                            JButton selesaiButton = new JButton("Selesai");
                            selesaiButton.addActionListener(event -> {
                                tableModel.addRow(new Object[]{name, tickets, concert, price, payment, email});
                                nameField.setText("");
                                ticketField.setText("");
                                emailField.setText("");
                                concertComboBox.setSelectedIndex(0);
                                paymentComboBox.setSelectedIndex(0);
                                qrisDialog.dispose();
                            });

                            JPanel buttonPanel = new JPanel();
                            buttonPanel.add(selesaiButton);

                            qrisDialog.add(qrisLabel, BorderLayout.CENTER);
                            qrisDialog.add(buttonPanel, BorderLayout.SOUTH);
                            qrisDialog.setSize(400, 400);
                            qrisDialog.setLocationRelativeTo(frame);
                            qrisDialog.setVisible(true);
                        }
                    } else if ("Transfer Bank".equals(payment)) {
                        JOptionPane.showMessageDialog(frame, "Silakan lakukan pembayaran melalui transfer ke rekening: 123-456-789.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        tableModel.addRow(new Object[]{name, tickets, concert, price, payment, email});
                        nameField.setText("");
                        ticketField.setText("");
                        emailField.setText("");
                        concertComboBox.setSelectedIndex(0);
                        paymentComboBox.setSelectedIndex(0);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Jumlah tiket harus berupa angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            updateButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin diupdate!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = nameField.getText().trim();
                String ticketText = ticketField.getText().trim();
                String email = emailField.getText().trim();
                String concert = (String) concertComboBox.getSelectedItem();
                String payment = (String) paymentComboBox.getSelectedItem();

                if (name.isEmpty() || ticketText.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int tickets = Integer.parseInt(ticketText);
                    if (tickets <= 0) throw new NumberFormatException();

                    int price = calculatePrice(concert, tickets);

                    tableModel.setValueAt(name, selectedRow, 0);
                    tableModel.setValueAt(tickets, selectedRow, 1);
                    tableModel.setValueAt(concert, selectedRow, 2);
                    tableModel.setValueAt(price, selectedRow, 3);
                    tableModel.setValueAt(payment, selectedRow, 4);
                    tableModel.setValueAt(email, selectedRow, 5);

                    nameField.setText("");
                    ticketField.setText("");
                    emailField.setText("");
                    concertComboBox.setSelectedIndex(0);
                    paymentComboBox.setSelectedIndex(0);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Jumlah tiket harus berupa angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tableModel.removeRow(selectedRow);
            });

            table.getSelectionModel().addListSelectionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    nameField.setText((String) tableModel.getValueAt(selectedRow, 0));
                    ticketField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 1)));
                    concertComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
                    paymentComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 4));
                    emailField.setText((String) tableModel.getValueAt(selectedRow, 5));
                }
            });

            frame.add(mainPanel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static int calculatePrice(String concert, int tickets) {
        int price;
        switch (concert) {
            case "Seventeen 'Right Here'":
                price = 150000;
                break;
            case "NCT Dream 'The Dream Show 4'":
                price = 200000;
                break;
            case "Treasure 'Reeboot'":
                price = 180000;
                break;
            default:
                throw new IllegalArgumentException("Konser tidak dikenal");
        }
        return price * tickets;
    }
}
