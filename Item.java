import java.math.BigDecimal;
import java.sql.ResultSet;


public class Item {
    private Integer id;
    private String description;
    private BigDecimal unitPrice;
    private int qtyInStock;
    private BigDecimal totalPrice;
    private int stock;

    public Item(int id, String description, BigDecimal unitPrice, int qtyInStock, BigDecimal totalPrice) {
        this.id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyInStock = qtyInStock;
        this.totalPrice = totalPrice;
    }
    
    public Item(int id, String description, BigDecimal unitPrice, int qtyInStock) {
        this.id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyInStock = qtyInStock;
        setTotalPrice();
    }

    public Item(String string, String string2, BigDecimal bigDecimal, int i) {
    }

    public Integer getId() {
        return this.id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public int getQtyInStock() {
        return qtyInStock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }
    
    public void setTotalPrice() {
        this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(qtyInStock));
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%.2f,%d,%.2f",
            id, description, unitPrice, qtyInStock, totalPrice);
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = new BigDecimal(Double.toString(unitPrice));
    }

    public static Object fromResultSet(ResultSet rs) {
        return null;
    }
}

