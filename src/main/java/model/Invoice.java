package model;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class Invoice {

    private int invoiceId;
    private String supplierName;
    private int typeOfProducts;
    private int ammount;
    private BigDecimal ammountToPay;
    private Timestamp dateOfIssue;
    private Time dateOfPayment;

    public Invoice(int invoiceId, String supplierName, int typeOfProducts, int ammount,
                   BigDecimal ammountToPay, Timestamp dateOfIssue, Time dateOfPayment) {
        this.invoiceId = invoiceId;
        this.supplierName = supplierName;
        this.typeOfProducts = typeOfProducts;
        this.ammount = ammount;
        this.ammountToPay = ammountToPay;
        this.dateOfIssue = dateOfIssue;
        this.dateOfPayment = dateOfPayment;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getTypeOfProducts() {
        return typeOfProducts;
    }

    public void setTypeOfProducts(int typeOfProducts) {
        this.typeOfProducts = typeOfProducts;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public BigDecimal getAmmountToPay() {
        return ammountToPay;
    }

    public void setAmmountToPay(BigDecimal ammountToPay) {
        this.ammountToPay = ammountToPay;
    }

    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Time getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Time dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", supplierName='" + supplierName + '\'' +
                ", typeOfProducts=" + typeOfProducts +
                ", ammount=" + ammount +
                ", ammountToPay=" + ammountToPay +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfPayment=" + dateOfPayment +
                '}';
    }
}
