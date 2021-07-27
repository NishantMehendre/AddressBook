import java.io.*;
import java.util.*;

public class AddressBook {

    public void add() {
        Scanner iName = new Scanner(System.in);
        Scanner iAdd = new Scanner(System.in);
        Scanner iNo = new Scanner(System.in);
        Scanner newNo = new Scanner(System.in);
        boolean nextEntry = true;
        Scanner newEntry = new Scanner(System.in);
        ArrayList<String> address = new ArrayList<String>();
        ArrayList<ArrayList<String>> numbers = new ArrayList<ArrayList<String>>();
        ArrayList<String> name = new ArrayList<String>();
        boolean invalidNo = true;
        // Continues receiving inputs for new addresses as long as the user desires
        while (nextEntry == true) {
            ArrayList<String> number = new ArrayList<String>();
            System.out.println("Enter name:");
            name.add(iName.nextLine());
            System.out.println("Enter address:");
            address.add(iAdd.nextLine());
            // Continues to recieve number from user if user enters an incorrect number or
            // desires to add another number
            while (invalidNo == true) {
                System.out.println("Enter number:");
                String num = iNo.nextLine();
                if (num.length() == 10) {
                    number.add(num);
                    System.out.println("Do you wish to add another number?(enter \"yes\" or \"no\")");
                    if (newNo.nextLine().equals("yes"))
                        invalidNo = true;
                    else
                        invalidNo = false;
                } else {
                    invalidNo = true;
                    System.out.println("Enter valid number");
                }
            }
            numbers.add(number);
            System.out.println("Do you wish to add another address?(enter \"yes\" or \"no\")");
            if (newEntry.nextLine().equals("yes")) {
                nextEntry = true;
                invalidNo = true;
            } else
                nextEntry = false;
        }
        File file = new File("Book.txt");
        boolean exists = file.exists();
        // Writes the addresses entered by the user in the file book.txt
        try {
            if (exists == false) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            // Writes the addresses in book.txt in the order the user entered them
            for (int i = 0; i < name.size(); i++) {
                fw.write("Name: " + name.get(i) + "\n");
                fw.write("Address: " + address.get(i) + "\n");
                fw.write("Number:");
                for (int j = 0; j < numbers.get(i).size(); j++) {
                    if (j > 0) {
                        fw.write("       ");
                    }
                    fw.write(" " + numbers.get(i).get(j) + "\n");
                }
                    fw.write("\n\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Driver {
    public static void main(String[] args) {
        AddressBook ab = new AddressBook();
        ab.add();
    }
}