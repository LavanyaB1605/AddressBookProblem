package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AddressBook {

    private static Scanner in = new Scanner(System.in);
        private static File file = new File("src/main/resources/PersonDetails.txt");
        static List<Person> people = new ArrayList<Person>();
    public static ArrayList<Person> addressBook = new ArrayList<Person>();

        public static void main(String[] args) throws IOException {
            System.out.println("Welcome to AddressBook");
            readPeopleFromFile();
            String bookName = null;
            showMainMenu(null);
        }
        private static void findPerson() throws IOException {
            System.out.println("1. Find FirstName");
            System.out.println("2. Find LastName");

            String choice;
            do {
                choice = in.nextLine();
                switch (choice) {
                    case "1":
                        findByName();
                        break;
                    case "2":
                        findBySurname();
                        break;
                    default:
                        System.out.print("Enter between 1 or 2: ");
                }
            } while (!choice.equals("1") && !choice.equals("2"));
            System.out.println();
            String bookName = null;
            showMainMenu(null);
        }

        private static void findBySurname() {
            System.out.print("Enter LastName: ");
            String surnameToFind = in.nextLine();
            int matches = 0;
            for(Person person : people) {
                if(person.getSurname().equals(surnameToFind)) {
                    System.out.println(person);
                    matches++;
                }
            }
            if(matches<=0) {
                System.out.println("No match found");
            }
        }

        private static void findByName() {
            System.out.print("Enter name: ");
            String nameToFind = in.nextLine();
            int matches = 0;
            for(Person person : people) {
                if(person.getName().equals(nameToFind)) {
                    System.out.println(person);
                    matches++;
                }
            }
            if(matches<=0) {
                System.out.println("No match found ");
            }
        }

        private static void addPerson() throws IOException {

            System.out.println("Enter FirstName: ");
            String name = in.nextLine();
            System.out.println("Enter LastName: ");
            String surname = in.nextLine();
            System.out.println("Enter mobile number: ");
            String phoneNumber = in.nextLine();
            System.out.println("Enter email ID: ");
            String email = in.nextLine();
            System.out.println("Enter Address: ");
            String address = in.nextLine();

            Person person = new Person(name, surname, phoneNumber, email, address);
            addToFile(person);
            people.add(person);
            String id = null;
            System.out.println("Added: " + id + person);
            System.out.println();
            String bookName = null;
            showMainMenu(null);
        }

        private static void addToFile(Person person) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(person.getName()+"\r\n" + person.getSurname() + "\r\n" + person.getPhoneNumber() + "\r\n" + person.getEmail() +
                        "\r\n" + person.getAddress() + "\r\n\r\n");
            } catch(IOException e) {
                System.out.println(e);
            }
        }

        private static boolean readPeopleFromFile() throws IOException {
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String name = null;
                while((name = reader.readLine()) != null) {
                    Person person = new Person(name, reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine());
                    people.add(person);        //adds person to the list
                    reader.readLine();
                }
                return true;
            }
            catch ( IOException e) {
                System.out.println(e);
            }
            return false;
        }
    //Read or Write the Address Book with Persons Contact into a File using csv file
    public void readAndWriteCsvFile() throws IOException {
        System.out.println("Please select the book");
        String bookName = in.nextLine();

        showMainMenu(bookName);

        List<String> rows = new ArrayList<>();
        for (Person person : addressBook) {

            rows.add(Person.getName() + "," + Person.getSurname() + "," + Person.getPhoneNumber() + "," +
                    Person.getEmail() + "," + Person.getAddress());

        }

        FileWriter csvWriter = new FileWriter("src/" + bookName + ".csv");
        csvWriter.append("FirstName");
        csvWriter.append(",");
        csvWriter.append("LastName");
        csvWriter.append(",");
        csvWriter.append("Address");
        csvWriter.append(",");
        csvWriter.append(",");
        csvWriter.append("MobileNumber");
        csvWriter.append("\n");

        for (String rowData : rows) {
            csvWriter.append(rowData);
            csvWriter.append("\n");
        }
        System.out.println("Written");
        csvWriter.flush();
        csvWriter.close();

        //To read data from file
        System.out.println("Read below data from file");
        Scanner sc = new Scanner(new File("src/" + bookName + ".csv"));
        sc.useDelimiter(" ");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            System.out.println(sc.next());  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner

    }

        private static void showMainMenu(String bookName) throws IOException {
            System.out.println("1. Add person");
            System.out.println("2. Search person");
            System.out.println("3. Show Contacts");
            System.out.println("4. Exit");

            String choice;
            do {
                choice = in.nextLine();
                switch (choice) {
                    case "1":
                        addPerson();
                        break;
                    case "2":
                        findPerson();
                        break;
                    case "3":
                        System.out.println(people);
                        System.out.println();
                        showMainMenu(bookName);
                        break;
                    case "4":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter option between 1 to 4:");
                }
            }while(!choice.equals("4"));
        }
    }
