import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

public class PemesananTiketKonserelsaTest {
    private PemesananTiketKonserelsa crudApp;
    private JTextField nameField;
    private JTextField ticketField;
    private JTextField emailField;
    private JComboBox<String> concertComboBox;
    private JComboBox<String> paymentComboBox;
    private DefaultTableModel tableModel;

    @BeforeEach
    public void setUp() {
        crudApp = new PemesananTiketKonserelsa();

        // Set up components used for testing
        nameField = new JTextField();
        ticketField = new JTextField();
        emailField = new JTextField();
        String[] concerts = {"Seventeen 'Right Here'", "NCT Dream 'The Dream Show 4'", "Treasure 'Reeboot'"};
        concertComboBox = new JComboBox<>(concerts);
        String[] payments = {"QRIS", "Transfer Bank"};
        paymentComboBox = new JComboBox<>(payments);

        String[] columns = {"Nama", "Jumlah Tiket", "Konser", "Harga", "Pembayaran", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
    }

    @Test
    public void testCalculatePrice_ValidConcertAndTickets() {
        int price = PemesananTiketKonserelsa.calculatePrice("Seventeen 'Right Here'", 2);
        assertEquals(300000, price);

        price = PemesananTiketKonserelsa
                .calculatePrice("NCT Dream 'The Dream Show 4'", 3);
        assertEquals(600000, price);
    }

    @Test
    public void testCalculatePrice_InvalidConcert() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                PemesananTiketKonserelsa.calculatePrice("Invalid Concert", 1));
        assertEquals("Konser tidak dikenal", exception.getMessage());
    }

    @Test
    public void testAddOrder_ValidInput() {
        nameField.setText("John Doe");
        ticketField.setText("3");
        emailField.setText("john.doe@example.com");
        concertComboBox.setSelectedItem("Seventeen 'Right Here'");
        paymentComboBox.setSelectedItem("QRIS");

        int price = PemesananTiketKonserelsa.calculatePrice("Seventeen 'Right Here'", 3);
        tableModel.addRow(new Object[]{
                nameField.getText().trim(),
                Integer.parseInt(ticketField.getText().trim()),
                concertComboBox.getSelectedItem(),
                price,
                paymentComboBox.getSelectedItem(),
                emailField.getText().trim()
        });

        assertEquals(1, tableModel.getRowCount());
        assertEquals("John Doe", tableModel.getValueAt(0, 0));
        assertEquals(3, tableModel.getValueAt(0, 1));
        assertEquals("Seventeen 'Right Here'", tableModel.getValueAt(0, 2));
        assertEquals(price, tableModel.getValueAt(0, 3));
        assertEquals("QRIS", tableModel.getValueAt(0, 4));
        assertEquals("john.doe@example.com", tableModel.getValueAt(0, 5));
    }

    @Test
    public void testAddOrder_InvalidTicketInput() {
        nameField.setText("John Doe");
        ticketField.setText("invalid");
        emailField.setText("john.doe@example.com");
        concertComboBox.setSelectedItem("Seventeen 'Right Here'");
        paymentComboBox.setSelectedItem("QRIS");

        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt(ticketField.getText().trim());
        });
    }

    @Test
    public void testDeleteOrder_ValidRow() {
        tableModel.addRow(new Object[]{"John Doe", 2, "Seventeen 'Right Here'", 300000, "QRIS", "john.doe@example.com"});
        assertEquals(1, tableModel.getRowCount());

        tableModel.removeRow(0);
        assertEquals(0, tableModel.getRowCount());
    }

    @Test
    public void testDeleteOrder_InvalidRow() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            tableModel.removeRow(0);
        });
    }
}
