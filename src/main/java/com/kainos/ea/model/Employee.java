package com.kainos.ea.model;

//import com.kainos.ea.employee_stuff.BadNumber;
//import com.kainos.ea.employee_stuff.Payable;
//import com.kainos.ea.employee_stuff.SalaryTooLowException;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee implements Comparable<Employee>{
    private int number; // employee number
    private String forename; // employee forename
    private String surname;
    private int salary; // employee salary in pence
    private String bankAccountNum;
    private String sortCode;
    private String nin;
    private String telNo;
    private String email;
    public static final int MIN_SALARY = 7_000_00;

    public Employee() {
        number = -1;
        setSalary(MIN_SALARY);
        setForename("");
    }

    public Employee (int newNumber) {
        this();
        setNumber(newNumber);
    }

    public Employee (int newNumber, String newForename) {
        this(newNumber);
        setForename(newForename);
    }

    public Employee (int newNumber, String newForename, String newSurname) {
        this(newNumber, newForename);
        setSurname(newSurname);
    }

    public Employee (int newNumber, String newForename, String newSurname, int newSalary) {
        this(newNumber, newForename, newSurname);
        setSalary(newSalary);
    }

    public Employee (@JsonProperty("id") int newNumber,@JsonProperty("fname") String newForename,@JsonProperty("sname") String newSurname,@JsonProperty("salary") int newSalary, @JsonProperty("bankno") String newBAN, @JsonProperty("sortcode") String newSortCode, @JsonProperty("nin") String newNIN, @JsonProperty("telno") String newTelNo, @JsonProperty("email") String newEmail) {
        this(newNumber, newForename, newSurname, newSalary);
        setBankAccountNum(newBAN);
        setSortCode(newSortCode);
        setNin(newNIN);
        setTelNo(newTelNo);
        setEmail(newEmail);
    }

    public int getNumber() {
        return number;
    }

    public boolean setNumber(int thisNumber){
        if (thisNumber > 0) {
            number = thisNumber;
            return true;
        } else return false;
    }

    public boolean setNumber(String thisNumber) {
            int s = Integer.parseInt(thisNumber);
            return setNumber(s);
    }

    public int calcPay() {
        return getSalary() / 12;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary >= MIN_SALARY) {
            this.salary = salary;
        } else {
//            throw new SalaryTooLowException(
//                    String.format("Salary £%,.2f is below the minimum salary £%,.2f.",
//                            salary/100.0, MIN_SALARY/100.0));
        }
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String name) {
        this.forename = name;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Employee){
            Employee emp = (Employee) obj;
            return this.getNumber() == emp.getNumber()
                    && this.getForename().equals(emp.getForename())
                    && this.getSalary() == emp.getSalary();
        } else return false;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "number = " + number +
                ", name = " + forename + " " + surname +
                ", salary = " + salary +
                ", bank-acc-no = " + bankAccountNum +
                ", sort-code = " + sortCode +
                ", nin = " + nin +
                ", tel-no = " + telNo +
                ", tel-no = " + email +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return Float.compare(this.getSalary(), o.getSalary());
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
