import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class StoreGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JLabel unitPriceLabel;
    private JTextField unitPriceField;
    private JLabel qtyInStockLabel;
    private JTextField qtyInStockField;
    private JButton addItemButton;
    private JButton searchItemButton;
    private JButton removeItemButton;
    private JButton updateItemButton;
    private JTextArea itemListArea;
    private JScrollPane itemListScrollPane;
    private ArrayList<Item> items;
    private int itemIdCounter;
    
    public StoreGUI() {
        // Set up the window
        this.setTitle("Store Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        // Set up the title label
        titleLabel = new JLabel("Store Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(titleLabel, BorderLayout.NORTH);

        // Set up the item input fields and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        idLabel = new JLabel("Item ID:");
        idField = new JTextField();
        descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        unitPriceLabel = new JLabel("Unit Price:");
        unitPriceField = new JTextField();
        qtyInStockLabel = new JLabel("Qty in Stock:");
        qtyInStockField = new JTextField();
        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new AddItemListener());
        searchItemButton = new JButton("Search Item");
        searchItemButton.addActionListener(new SearchItemListener());
        removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener(new RemoveItemListener());
        updateItemButton = new JButton("Update Item");
        updateItemButton.addActionListener(new UpdateItemListener());
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionField);
        inputPanel.add(unitPriceLabel);
        inputPanel.add(unitPriceField);
        inputPanel.add(qtyInStockLabel);
        inputPanel.add(qtyInStockField);
        inputPanel.add(addItemButton);
        inputPanel.add(searchItemButton);
        inputPanel.add(removeItemButton);
        inputPanel.add(updateItemButton);
        this.add(inputPanel, BorderLayout.WEST);

        // Set up the item list area
        itemListArea = new JTextArea();
        itemListArea.setEditable(false);
        itemListScrollPane = new JScrollPane(itemListArea);
        itemListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(itemListScrollPane, BorderLayout.CENTER);

        // Initialize the list of items and item ID counter
        items = new ArrayList<Item>();
        itemIdCounter = 1;
    }

    public StoreGUI(JButton addItemButton, JTextField descriptionField, JLabel descriptionLabel, JTextField idField, JLabel idLabel, int itemIdCounter, JTextArea itemListArea, JScrollPane itemListScrollPane, ArrayList<Item> items, JTextField qtyInStockField, JLabel qtyInStockLabel, JButton removeItemButton, JButton searchItemButton, JLabel titleLabel, JTextField unitPriceField, JLabel unitPriceLabel, JButton updateItemButton) throws HeadlessException {
        this.addItemButton = addItemButton;
        this.descriptionField = descriptionField;
        this.descriptionLabel = descriptionLabel;
        this.idField = idField;
        this.idLabel = idLabel;
        this.itemIdCounter = itemIdCounter;
        this.itemListArea = itemListArea;
        this.itemListScrollPane = itemListScrollPane;
        this.items = items;
        this.qtyInStockField = qtyInStockField;
        this.qtyInStockLabel = qtyInStockLabel;
        this.removeItemButton = removeItemButton;
        this.searchItemButton = searchItemButton;
        this.titleLabel = titleLabel;
        this.unitPriceField = unitPriceField;
        this.unitPriceLabel = unitPriceLabel;
        this.updateItemButton = updateItemButton;
    }

    public StoreGUI(JButton addItemButton, JTextField descriptionField, JLabel descriptionLabel, JTextField idField, JLabel idLabel, int itemIdCounter, JTextArea itemListArea, JScrollPane itemListScrollPane, ArrayList<Item> items, JTextField qtyInStockField, JLabel qtyInStockLabel, JButton removeItemButton, JButton searchItemButton, JLabel titleLabel, JTextField unitPriceField, JLabel unitPriceLabel, JButton updateItemButton, GraphicsConfiguration gc) {
        super(gc);
        this.addItemButton = addItemButton;
        this.descriptionField = descriptionField;
        this.descriptionLabel = descriptionLabel;
        this.idField = idField;
        this.idLabel = idLabel;
        this.itemIdCounter = itemIdCounter;
        this.itemListArea = itemListArea;
        this.itemListScrollPane = itemListScrollPane;
        this.items = items;
        this.qtyInStockField = qtyInStockField;
        this.qtyInStockLabel = qtyInStockLabel;
        this.removeItemButton = removeItemButton;
        this.searchItemButton = searchItemButton;
        this.titleLabel = titleLabel;
        this.unitPriceField = unitPriceField;
        this.unitPriceLabel = unitPriceLabel;
        this.updateItemButton = updateItemButton;
    }

    public StoreGUI(JButton addItemButton, JTextField descriptionField, JLabel descriptionLabel, JTextField idField, JLabel idLabel, int itemIdCounter, JTextArea itemListArea, JScrollPane itemListScrollPane, ArrayList<Item> items, JTextField qtyInStockField, JLabel qtyInStockLabel, JButton removeItemButton, JButton searchItemButton, JLabel titleLabel, JTextField unitPriceField, JLabel unitPriceLabel, JButton updateItemButton, String title) throws HeadlessException {
        super(title);
        this.addItemButton = addItemButton;
        this.descriptionField = descriptionField;
        this.descriptionLabel = descriptionLabel;
        this.idField = idField;
        this.idLabel = idLabel;
        this.itemIdCounter = itemIdCounter;
        this.itemListArea = itemListArea;
        this.itemListScrollPane = itemListScrollPane;
        this.items = items;
        this.qtyInStockField = qtyInStockField;
        this.qtyInStockLabel = qtyInStockLabel;
        this.removeItemButton = removeItemButton;
        this.searchItemButton = searchItemButton;
        this.titleLabel = titleLabel;
        this.unitPriceField = unitPriceField;
        this.unitPriceLabel = unitPriceLabel;
        this.updateItemButton = updateItemButton;
    }

    public StoreGUI(JButton addItemButton, JTextField descriptionField, JLabel descriptionLabel, JTextField idField, JLabel idLabel, int itemIdCounter, JTextArea itemListArea, JScrollPane itemListScrollPane, ArrayList<Item> items, JTextField qtyInStockField, JLabel qtyInStockLabel, JButton removeItemButton, JButton searchItemButton, JLabel titleLabel, JTextField unitPriceField, JLabel unitPriceLabel, JButton updateItemButton, String title, GraphicsConfiguration gc) {
        super(title, gc);
        this.addItemButton = addItemButton;
        this.descriptionField = descriptionField;
        this.descriptionLabel = descriptionLabel;
        this.idField = idField;
        this.idLabel = idLabel;
        this.itemIdCounter = itemIdCounter;
        this.itemListArea = itemListArea;
        this.itemListScrollPane = itemListScrollPane;
        this.items = items;
        this.qtyInStockField = qtyInStockField;
        this.qtyInStockLabel = qtyInStockLabel;
        this.removeItemButton = removeItemButton;
        this.searchItemButton = searchItemButton;
        this.titleLabel = titleLabel;
        this.unitPriceField = unitPriceField;
        this.unitPriceLabel = unitPriceLabel;
        this.updateItemButton = updateItemButton;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public JTextField getQtyInStockField() {
        return qtyInStockField;
    }

    public void setQtyInStockField(JTextField qtyInStockField) {
        this.qtyInStockField = qtyInStockField;
    }
    
    private class AddItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                String description = descriptionField.getText();
                BigDecimal unitPrice = new BigDecimal(unitPriceField.getText());
                int qtyInStock = Integer.parseInt(qtyInStockField.getText());
                Item item = new Item(id, description, unitPrice, qtyInStock);
                items.add(item);
                itemListArea.append(item.toString() + "\n");
                itemIdCounter = Math.max(itemIdCounter, id             + 1); // update item ID counter
                idField.setText(Integer.toString(itemIdCounter)); // set ID field to next available ID
                descriptionField.setText(""); // clear description field
                unitPriceField.setText(""); // clear unit price field
                qtyInStockField.setText(""); // clear quantity in stock field
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(StoreGUI.this, "Invalid input. Please enter a valid integer for item ID and quantity in stock, and a valid decimal number for unit price.");
            }
        }
    }
    
    private class SearchItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                for (Item item : items) {
                    if (item.getId() == id) {
                        descriptionField.setText(item.getDescription());
                        unitPriceField.setText(item.getUnitPrice().toString());
                        qtyInStockField.setText(Integer.toString(item.getQtyInStock()));
                        return;
                    }
                }
                JOptionPane.showMessageDialog(StoreGUI.this, "Item not found.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(StoreGUI.this, "Invalid input. Please enter a valid integer for item ID.");
            }
        }
    }
    
    private class RemoveItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                for (Item item : items) {
                    if (item.getId() == id) {
                        items.remove(item);
                        itemListArea.setText("");
                        for (Item newItem : items) {
                            itemListArea.append(newItem.toString() + "\n");
                        }
                        itemIdCounter--; // update item ID counter
                        idField.setText(Integer.toString(itemIdCounter)); // set ID field to previous ID
                        descriptionField.setText(""); // clear description field
                        unitPriceField.setText(""); // clear unit price field
                        qtyInStockField.setText(""); // clear quantity in stock field
                        return;
                    }
                }
                JOptionPane.showMessageDialog(StoreGUI.this, "Item not found.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(StoreGUI.this, "Invalid input. Please enter a valid integer for item ID.");
            }
        }
    }
    
    private class UpdateItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                for (Item item : items) {
                    if (item.getId() == id) {
                        String description = descriptionField.getText();
                        BigDecimal unitPrice = new BigDecimal(unitPriceField.getText());
                        int qtyInStock = Integer.parseInt(qtyInStockField.getText());
                        item.setDescription(description);
                        item.setUnitPrice(unitPrice);
                        item.setQtyInStock(qtyInStock);
                        itemListArea.setText("");
                        for (Item newItem : items) {
                            itemListArea.append(newItem.toString() + "\n");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(StoreGUI.this, "Item not found.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(StoreGUI.this, "Invalid input. Please enter a valid integer for item ID and quantity in stock, and a valid decimal number for unit price.");
            }
        }
    }
    
    public static void main(String[] args) {
        // Create and show the GUI
        StoreGUI gui = new StoreGUI();
        gui.setVisible(true);
    }
}    
