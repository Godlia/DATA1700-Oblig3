package com.example.oblig3;

public class Ticket {
    private int ID;
    private String movieName;
    private int amount;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;

    public Ticket(int ID,String movieName, int amount, String firstName, String lastName, String phoneNumber, String eMail) {
        this.ID = ID;
        this.movieName = movieName;
        this.amount = amount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }
    public Ticket() {}

    public Ticket(String movieName, int amount, String firstName, String lastName, String phoneNumber, String eMail) {
        this.movieName = movieName;
        this.amount = amount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "movieName='" + movieName + '\'' +
                ", amount=" + amount +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
