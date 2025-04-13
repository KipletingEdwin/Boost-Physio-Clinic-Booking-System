package com.boostphysio.model;

public class Patient {

    private int id;
    private String name;
    private String address;
    private String contact;

    public Patient(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return name + " | Address: " + address + " | Contact: " + contact;
    }
}