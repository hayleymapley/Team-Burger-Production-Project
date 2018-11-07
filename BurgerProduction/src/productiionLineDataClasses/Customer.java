package productiionLineDataClasses;

public class Customer {
    private int customerID;
    private String customerName;
    private String customerEmail;
    
    public Customer(int customerID, String customerName, String customerEmail) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
    
    
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}