import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Transaction {

    public Transaction(Date date, String customerId, List<TransactionItem> items) {
    }

    public Transaction(int id, String description, int qtySold, double amount, int stockRemaining,
            String transactionType) {
    }

    public Transaction(int id, String description, int qtySold, double amount, BigDecimal unitPrice,
            BigDecimal totalPrice) {
    }

    public Date getDate() {
        return null;
    }

    public Object getItemsAsString() {
        return null;
    }

    public double getAmount() {
        return 0;
    }

    public int getQtySold() {
        return 0;
    }

    public int getItemPrice() {
        return 0;
    }

    public String getId() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public int getStockRemaining() {
        return null;
    }

    public String getTransactionType() {
        return null;
    }

    public LocalDate getTransactionDate() {
        return null;
    }

    public Object getUnitPrice() {
        return null;
    }

    public Object getTotalPrice() {
        return null;
    }

       /*
        ------ IMPROVEMENTS TO MAKE -----------

          using private class variables, adding getters and setters for all class variables, 
          and updating the method signatures to use the correct return types and parameter types. 
          These changes will make it easier to work with the class and maintain it over time.

        */


}
