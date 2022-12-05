package org.example;

public class Person {
        static int id = AddressBook.people.size();
        private static String name;
        private static String surname;
        private static String phoneNumber;
        private static String email;
        private static String address;

        Person(String name, String surname, String phoneNumber, String email, String address) {
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            id++;
        }

        static String getName() {
            return name;
        }

        static String getSurname() {
            return surname;
        }

        static String getPhoneNumber() {
            return phoneNumber;
        }

        static String getEmail() {
            return email;
        }

        static String getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "\n\nName: " + getName() + "\nSurname: " + getSurname() + "\nPhone number: " + getPhoneNumber() + "\nEmail: " +
                    getEmail() + "\nAddress: " + getAddress();
        }
    }
