package org.example;
public class User {
    final int id;
    final String name;
    final String company;
    final String username;
    final String email;
    final String address;
    final String zip;
    final String state;
    final String country;
    final String phoneNumber;
    final String photo;

    public User(int id, String name, String company, String username, String email, String address, String zip, String state, String country, String phoneNumber, String photo) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.username = username;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.state = state;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }

    void print() {
        System.out.println("id - " + id);
        System.out.println("name - " + name);
        System.out.println("company - " + company);
        System.out.println("username - " + username);
        System.out.println("email - " + email);
        System.out.println("address - " + address);
        System.out.println("zip - " + zip);
        System.out.println("state - " + state);
        System.out.println("country - " + country);
        System.out.println("phoneNumber - " + phoneNumber);
        System.out.println("photo - " + photo);
        System.out.println();
    }
}