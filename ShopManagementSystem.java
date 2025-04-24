import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShopManagementSystem extends JFrame implements ActionListener {
    // GUI components
    JLabel lblName, lblPrice, lblQuantity;
    JTextField txtName, txtPrice, txtQuantity;
    JButton btnAdd, btnView, btnUpdate, btnDelete, btnSell;
    JTextArea textArea;
    JScrollPane scrollPane;

    // Store products in memory
    ArrayList<Product> products = new ArrayList<>();

    // Constructor to set up GUI
    public ShopManagementSystem() {
        setTitle("Shop Management System");
        setSize(500, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels and TextFields
        lblName = new JLabel("Product Name:");
        lblName.setBounds(50, 50, 150, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 50, 200, 30);
        add(txtName);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(50, 100, 150, 30);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(200, 100, 200, 30);
        add(txtPrice);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(50, 150, 150, 30);
        add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(200, 150, 200, 30);
        add(txtQuantity);

        // Buttons
        btnAdd = new JButton("Add Product");
        btnAdd.setBounds(50, 200, 150, 30);
        btnAdd.addActionListener(this);
        add(btnAdd);

        btnView = new JButton("View Products");
        btnView.setBounds(210, 200, 150, 30);
        btnView.addActionListener(this);
        add(btnView);

        btnUpdate = new JButton("Update Product");
        btnUpdate.setBounds(50, 250, 150, 30);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnDelete = new JButton("Delete Product");
        btnDelete.setBounds(210, 250, 150, 30);
        btnDelete.addActionListener(this);
        add(btnDelete);

        btnSell = new JButton("Sell Product");
        btnSell.setBounds(130, 300, 150, 30);
        btnSell.addActionListener(this);
        add(btnSell);

        // Text Area for displaying data
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 350, 400, 200);
        add(scrollPane);

        setVisible(true);
    }

    // Event handling
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            addProduct();
        } else if (e.getSource() == btnView) {
            viewProducts();
        } else if (e.getSource() == btnUpdate) {
            updateProduct();
        } else if (e.getSource() == btnDelete) {
            deleteProduct();
        } else if (e.getSource() == btnSell) {
            sellProduct();
        }
    }

    // Add product to the ArrayList
    private void addProduct() {
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());

        Product newProduct = new Product(name, price, quantity);
        products.add(newProduct);
        JOptionPane.showMessageDialog(this, "Product added successfully!");
    }

    // View products
    private void viewProducts() {
        textArea.setText("");
        for (Product product : products) {
            textArea.append(product.toString() + "\n");
        }
    }

    // Update product details
    private void updateProduct() {
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setPrice(price);
                product.setQuantity(quantity);
                JOptionPane.showMessageDialog(this, "Product updated successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found!");
    }

    // Delete product
    private void deleteProduct() {
        String name = txtName.getText();

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                products.remove(product);
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found!");
    }

    // Sell product
    private void sellProduct() {
        String name = txtName.getText();
        int quantitySold = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity to sell:"));

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (product.getQuantity() >= quantitySold) {
                    product.setQuantity(product.getQuantity() - quantitySold);
                    double totalPrice = quantitySold * product.getPrice();
                    JOptionPane.showMessageDialog(this, "Product sold successfully! Total Price: " + totalPrice);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient stock!");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found!");
    }

    // Main method to run the application
    public static void main(String[] args) {
        new ShopManagementSystem();
    }
}
