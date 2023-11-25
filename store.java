/* UNIVERSITY OF SUFFOLK - INTRODUCTION TO PROGRAMMING 
 * Module assignment
 * 
 * Module Lead: Dr. Kakia Chatsiou
 * Last updated 2022-02-25
 * 
 * The assignment starter code consists of 3 files:
 * 
 * a) store.java: this file contains starting code for the inventory
 * management control system. See assignment brief document for 
 * more information on how to build the rest of the application.
 * 
 * b) items.txt: this file contains a list of all items in the inventory
 * with information about their quantities and total price in stock. See 
 * assignment text for more information.
 * 
 * c) transactions.txt: this file contains a list of all the transactions
 * for the day. You will be using it to print out the report of transactions
 * Each time a transaction happens i.e. an item is added or removed, 
 * a record should be stored in transactions.txt
 *  
 *
 * You are asked to work on expanding the starter code so that your Java app can do the following:
 * 
 *  - read and output to the 2 files (transactions.txt, items.txt) as appropriate
 *  - autogenerate a (5-digit) item id ie. 00001 for each new item [DONE]
 *  - add a new item to the inventory (by appending a line to items.txt)  [DONE]
 *  - update the quantity of an item already in store (in items.txt) [DONE]
 *  - remove an item from the inventory (by removing relevant entry in items.txt) [DONE]
 *  - search for an item in the inventory (items.txt) [DONE]
 *  - generate and print a daily transaction report (using transactions.txt)
 * 
 * Check out the full assignment brief for more information about the report.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;


public class store {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        File itemsFile = new File("items.txt");
        File transactionsFile = new File("transactions.txt");

        
    
        // Create files if they don't exist
        try {
            itemsFile.createNewFile();
            transactionsFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    
        // Load items into memory
        ArrayList<Item> items = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(itemsFile)) {
            // Skip the first line if it is a header
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Line read: " + line); // add this line to print out the read line
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String description = parts[1];
                    BigDecimal unitPrice = new BigDecimal(parts[2]);
                    int qtyInStock = Integer.parseInt(parts[3]);
                    BigDecimal totalPrice = new BigDecimal(parts[4]);
                    Item item = new Item(id, description, unitPrice, qtyInStock, totalPrice);
                    items.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a new item");
            System.out.println("2. Update an existing item");
            System.out.println("3. Remove an item");
            System.out.println("4. Search for an item");
            System.out.println("5. Generate daily transaction report");
            System.out.println("6. Exit");
            System.out.print("Option: ");
            int option = 0;
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } else {
                scanner.nextLine(); // Consume invalid input
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            switch (option) {
                case 1:
                    addItem(items);
                    break;
                case 2:
                    updateItem(items);
                    break;
                case 3:
                    removeItem(items);
                    break;
                case 4:
                    searchItem(items);
                    break;
                case 5:
                    generateReport(transactionsFile, items);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void generateReport(File transactionsFile, ArrayList<Item> items) {
        // Load transactions into memory
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(transactionsFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String description = parts[1];
                    int qtySold = Integer.parseInt(parts[2]);
                    double amount = Double.parseDouble(parts[3]);
                    int stockRemaining = Integer.parseInt(parts[4]);
                    String transactionType = parts[5];
                    Transaction transaction = new Transaction(id, description, qtySold, amount, stockRemaining, transactionType);
                    // Check if there is sufficient stock for the transaction
                    boolean stockAvailable = false;
                for (Item item : items) {
                    if (item.getDescription().equalsIgnoreCase(description)) {
                        if (item.getStock() >= qtySold) {
                            // Reduce the stock of the item
                            item.setStock(item.getStock() - qtySold);
                            stockAvailable = true;
                            break;
                        }
                    }
                }
                if (stockAvailable) {
                    // Add the transaction to the transactions list
                    transactions.add(transaction);
                } else {
                    System.out.println("Error: Not enough stock for transaction with id " + id);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    
        // Get today's transactions
        ArrayList<Transaction> todayTransactions = new ArrayList<>();
    
        // Get today's date
        LocalDate today = LocalDate.now();
    
        // Filter transactions by date
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getTransactionDate();
            if (transactionDate.equals(today)) {
                todayTransactions.add(transaction);
            }
        }
    
        // Print today's transaction report
        System.out.println("\nToday's Transaction Report:");
        System.out.println("Date: " + today);
        
        for (Transaction transaction : todayTransactions) {
            
        }

        // Print contents of transactions file
    try (Scanner fileScanner = new Scanner(transactionsFile)) {
        
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            System.out.println(line);
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    // This modified method will print the transaction report as before, but after that, it will read the "transactions.txt" file and print its contents in the console.
}
    
    

    private static void searchItem(ArrayList<Item> items) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item ID: ");
        int itemId = scanner.nextInt();
    
        // Search for item
        ArrayList<Item> searchResults = new ArrayList<>();
        for (Item item : items) {
            if (item.getId() == itemId) {
                searchResults.add(item);
            }
        }
    
        // Print search results
        if (searchResults.isEmpty()) {
            System.out.println("No items found.");
        } else {
            System.out.printf("%-5s %-20s %-10s %-15s %-15s\n", "ID", "Description", "Unit Price", "Qty in Stock", "Total Price");
            for (Item item : searchResults) {
                System.out.printf("%-5d %-20s %-10.2f %-15d %-15.2f\n", item.getId(), item.getDescription(), item.getUnitPrice(), item.getQtyInStock(), item.getTotalPrice());

            }
        }
    }
    
    

    private static void removeItem(ArrayList<Item> items) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
    
        // Find item
        Item itemToRemove = null;
        for (Item item : items) {
            if (item.getId() == id) {
                itemToRemove = item;
                break;
            }
        }
    
        // Remove item
        if (itemToRemove != null) {
            items.remove(itemToRemove);
            saveItemsToFile(items, "items.txt");
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public static void updateItem(ArrayList<Item> items) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID of item to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
    
        Item itemToUpdate = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                itemToUpdate = item;
                break;
            }
        }
    
        if (itemToUpdate != null) {
            System.out.print("Enter new description for item: ");
            String newDescription = scanner.nextLine();
            if (!newDescription.isEmpty()) {
                itemToUpdate.setDescription(newDescription);
            }
    
            System.out.print("Enter new unit price for item: ");
            String newUnitPriceString = scanner.nextLine();
            if (!newUnitPriceString.isEmpty()) {
                // Use regular expression to validate input
                String regex = "^\\d+(\\.\\d{1,2})?$";
                if (newUnitPriceString.matches(regex)) {
                    double newUnitPrice = Double.parseDouble(newUnitPriceString);
                    if (newUnitPrice != 0) {
                        itemToUpdate.setUnitPrice(new BigDecimal(Double.toString(newUnitPrice)));
                    }
                } else {
                    System.out.println("Error: Invalid input for unit price.");
                }
            }
    
            System.out.print("Enter new quantity in stock for item: ");
            String newQtyInStockString = scanner.nextLine();
            if (!newQtyInStockString.isEmpty()) {
                // Use regular expression to validate input
                String regex = "^\\d+(\\.\\d{1,2})?$";
                if (newQtyInStockString.matches(regex)) {
                    int newQtyInStock = Integer.parseInt(newQtyInStockString);
                    if (newQtyInStock != 0) {
                        itemToUpdate.setQtyInStock(newQtyInStock);
                    }
                } else {
                    System.out.println("Error: Invalid input for quantity in stock.");
                }
            }
    
            itemToUpdate.setTotalPrice();
    
            System.out.println("Item updated: " + itemToUpdate);
    
            // Update the items.txt file
            try {
                FileWriter fileWriter = new FileWriter("items.txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("ID,Description,Unit Price,Quantity in Stock,Total Price");
                for (Item item : items) {
                    printWriter.println(item.getId() + "," + item.getDescription() + "," + item.getUnitPrice() + "," + item.getQtyInStock() + "," + item.getTotalPrice());
                }
                printWriter.close();
                System.out.println("Item file updated.");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Item not found.");
        }

        /* This code uses regular expressions to validate the input for the unit price and quantity, 
        allowing inputs with up to two decimal places. If the input is not valid, 
        the code will print an error message and prompt the user to enter a valid input. */
    }
    
    
    

    private static void addItem(ArrayList<Item> items) {
        System.out.print("Enter item ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
    
        // Check if item ID already exists
        for (Item item : items) {
            if (item.getId() == id) {
                System.out.println("Item ID already exists.");
                return;
            }
        }
    
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
    
        BigDecimal unitPrice = null;
        while (unitPrice == null) {
            System.out.print("Enter unit price: ");
            try {
                unitPrice = scanner.nextBigDecimal();
                if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Unit price cannot be negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.nextLine(); // Consume invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                unitPrice = null; // Reset to null to prompt user to enter a valid unit price
            }
        }
        scanner.nextLine(); // Consume newline character
    
        int qtyInStock = -1;
        while (qtyInStock < 0) {
            System.out.print("Enter quantity in stock: ");
            try {
                qtyInStock = scanner.nextInt();
                if (qtyInStock < 0) {
                    throw new IllegalArgumentException("Quantity cannot be negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer number.");
                scanner.nextLine(); // Consume invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                qtyInStock = -1; // Reset to -1 to prompt user to enter a valid quantity
            }
        }
        scanner.nextLine(); // Consume newline character
    
        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(qtyInStock));
        Item item = new Item(id, description, unitPrice, qtyInStock, totalPrice);
        item.setTotalPrice(totalPrice);
        items.add(item);
        
    
        // Save the changes to the file
        saveItemsToFile(items, "items.txt");


        System.out.printf("Total price: %.2f\n", totalPrice);
        System.out.println("Item added.");
    }
    
    







private static void addTransaction(Transaction transaction, File transactionsFile) {
// Write transaction to file
try (FileWriter writer = new FileWriter(transactionsFile, true)) {
writer.write(String.format("%d,%s,%d,%.2f,%d,%s\n", transaction.getId(), transaction.getDescription(), transaction.getQtySold(), transaction.getAmount(), transaction.getStockRemaining(), transaction.getTransactionType()));
System.out.println("Transaction recorded.");
} catch (IOException e) {
System.out.println("Error writing to file: " + e.getMessage());
}
/*
 The true parameter in the constructor of the FileWriter indicates that the writer should append to the file if it already exists.
The write method of the FileWriter object is called, which formats the transaction object as a string using the String.format method, and writes the resulting string to the file.
 */
}

private static int getNewTransactionId(File transactionsFile) {
// Get last transaction ID from file
int lastTransactionId = 0;
try (Scanner fileScanner = new Scanner(transactionsFile)) {
while (fileScanner.hasNextLine()) {
String line = fileScanner.nextLine();
String[] parts = line.split(",");
if (parts.length > 0) {
int id = Integer.parseInt(parts[0]);
if (id > lastTransactionId) {
lastTransactionId = id;
}
}
}
} catch (IOException e) {
System.out.println("Error reading file: " + e.getMessage());
}

// Increment last transaction ID to get new transaction ID
return lastTransactionId + 1;

}

private static void recordTransaction(Transaction transaction, ArrayList<Item> items, File transactionsFile) {
// Find item
Item itemToUpdate = null;
for (Item item : items) {
if (item.getId() == transaction.getId()) {
itemToUpdate = item;
break;
}
}
}


private static void updateTransactions(File transactionsFile, ArrayList<Item> items) {
    ArrayList<Transaction> transactions = new ArrayList<>();
    try (Scanner fileScanner = new Scanner(transactionsFile)) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 6) {
                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                int qtySold = Integer.parseInt(parts[2]);
                double amount = Double.parseDouble(parts[3]);
                int stockRemaining = Integer.parseInt(parts[4]);
                String transactionType = parts[5];
                Transaction transaction = new Transaction(id, description, qtySold, amount, stockRemaining, transactionType);
                transactions.add(transaction);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        return;
    }

    LocalDate today = LocalDate.now();

    for (Item item : items) {
        int id = item.getId();
        String description = item.getDescription();
        int qtySold = 0;
        double amount = 0.0;
        int stockRemaining = item.getStock();

        // Calculate qtySold and amount based on the transactions
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                if (transaction.getTransactionDate().equals(today)) {
                    qtySold += transaction.getQtySold();
                    amount += transaction.getAmount();
                    stockRemaining = transaction.getStockRemaining();
                }
            }
        }

        // Write the updated transaction to the transactions file
        try (PrintWriter writer = new PrintWriter(new FileWriter(transactionsFile, true))) {
            writer.println((id + 1) + "," + description + "," + qtySold + "," + amount + "," + stockRemaining + ",ADDED," + today);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}



private static void saveItemsToFile(ArrayList<Item> items, String filename) {
    try {
        FileWriter writer = new FileWriter(filename);
        writer.write("ID,Description,Unit Price,Quantity in Stock,Total Price\n"); // add header
        
        for (Item item : items) {
            writer.write(item.getId() + "," + item.getDescription() + "," + item.getUnitPrice() + "," + item.getQtyInStock() + "," + item.getTotalPrice() + "\n");
        }


        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving items to file: " + e.getMessage());
    }

    /* updated version of the code loops through the items in the ArrayList and 
    writes each item to the file in the correct format. 
    The items are written in the same order as the header */
}


}

