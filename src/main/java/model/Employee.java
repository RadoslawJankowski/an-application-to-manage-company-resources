package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Employee {

    private int emp_id;
    private String firstName;
    private String lastName;
    private Date birthDayDate;
    private String sex;
    private int empPosition;
    private BigDecimal salary;
    private Timestamp work_from;

    public Employee(int emp_id, String firstName, String lastName, Date birthDayDate, String sex, int empPosition, BigDecimal salary, Timestamp work_from) {
        this.emp_id = emp_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDayDate = birthDayDate;
        this.sex = sex;
        this.empPosition = empPosition;
        this.salary = salary;
        this.work_from = work_from;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDayDate() {
        return birthDayDate;
    }

    public void setBirthDayDate(Date birthDayDate) {
        this.birthDayDate = birthDayDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(int empPosition) {
        this.empPosition = empPosition;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Timestamp getWork_from() {
        return work_from;
    }

    public void setWork_from(Timestamp work_from) {
        this.work_from = work_from;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDayDate=" + birthDayDate +
                ", sex='" + sex + '\'' +
                ", empPosition=" + empPosition +
                ", salary=" + salary +
                ", work_from=" + work_from +
                '}';
    }
}
