
/**
 * Homework Assignment 1
 * CSE 214 Recitation 8
 * Recitation TA: Daniel Scanteianu
 * Grading TA: ?
 *
 * @author Shariq Syed
 * ID: ##########
 * NETID: ##########
 */

import java.util.Scanner;

public class ResidenceHallManager {

    //Create new Floors
    private static Floor floor1 = new Floor();
    private static Floor floor2 = new Floor();
    private static Floor floor3 = new Floor();
    private static int currentFloor = 1;
    private static Scanner input;

    public static void main(String[] args) {

        System.out.println("Welcome to Stony Brook Residence Manager!\n");

        System.out.print("Menu:\n");
        menu();

        //Choice Menu
        char choice;
        while (true) {
            System.out.print("\nPlease select an option: ");
            choice = validateChar();

            switch (choice) {
                case 'a':
                    main_addStudent();
                    break;
                case 'r':
                    main_removeStudent();
                    break;
                case 's':
                    main_swapStudent();
                    break;
                case 'm':
                    main_moveStudent();
                    break;
                case 'f':
                    main_selectFloor();
                    break;
                case 'c':
                    main_copyFloor();
                    break;
                case 'p':
                    main_printCurrentFloor();
                    break;
                case 'w':
                    main_writeUpStudent();
                    break;
                case 'q':
                    System.out.print("\nThank you for using Stony Brook's Residence Hall Manager");
                    System.exit(0);
                default:
                    System.out.print("\nInvalid choice. Here's the menu:\n");
                    menu();
            }
        }
    }

    //Add Student
    private static void main_addStudent() {
        String name;
        int idNumber, position;
        Floor tempFloor = getCurrentFloor(currentFloor);

        try {
            System.out.println("Add a Student");
            input.nextLine();

            System.out.print("Enter a name: ");
            name = validate(0);

            System.out.print("Enter an ID number: ");
            idNumber = Integer.parseInt(validate(1));

            System.out.print("Enter a room number: ");
            position = Integer.parseInt(validate(1));

            tempFloor.addStudent((new Student(name, idNumber, 0)), position - 1);
            System.out.println(tempFloor.getStudent(position - 1).getName() +
                    " was added to Floor " + currentFloor + " Room " + position);

        } catch (FullFloorException ex) {
            System.out.println("The current floor is full");
        } catch (EmptyFloorException ex) {
            System.out.println("Please use the available empty room at " + (tempFloor.count() + 1));
        }

    }

    //Remove Student
    private static void main_removeStudent() {
        int position;
        Floor tempFloor = getCurrentFloor(currentFloor);

        try {
            System.out.print("Enter the student's room number: ");
            position = Integer.parseInt(validate(1));

            System.out.println(tempFloor.getStudent(position - 1).getName() +
                    " was removed");
            tempFloor.removeStudent(position - 1);
        } catch (EmptyFloorException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("There is no student at this position");
        } catch (IllegalArgumentException ex) {
            System.out.println("Please enter a valid room number");
        }
    }

    //Swap Students
    private static void main_swapStudent() {
        int s1Floor, s1Room, s2Floor, s2Room;

        try {
            System.out.print("Enter the student one's floor: ");
            input.nextLine();
            s1Floor = Integer.parseInt(validate(1));

            System.out.print("Enter the student one's room: ");
            s1Room = Integer.parseInt(validate(1));

            System.out.print("Enter the student two's floor: ");
            s2Floor = Integer.parseInt(validate(1));

            System.out.print("Enter the student two's room: ");
            s2Room = Integer.parseInt(validate(1));


            Student tempStudent = (Student) getCurrentFloor(s1Floor).getStudent(s1Room - 1).clone();
            getCurrentFloor(s1Floor).setStudent(getCurrentFloor(s2Floor).getStudent(s2Room - 1), s1Room - 1);
            getCurrentFloor(s2Floor).setStudent(tempStudent, s2Room - 1);

            System.out.println(tempStudent.getName() + " was swapped with " +
                    getCurrentFloor(s1Floor).getStudent(s1Room - 1).getName());

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Enter an appropriate value");
        }
    }

    //Move Student
    private static void main_moveStudent() {
        int sFloor, sRoom, dFloor, dRoom;

        try {
            System.out.print("Enter the source floor: ");
            input.nextLine();
            sFloor = Integer.parseInt(validate(1));

            System.out.print("Enter the source room: ");
            sRoom = Integer.parseInt(validate(1));

            System.out.print("Enter the destination floor: ");
            dFloor = Integer.parseInt(validate(1));

            System.out.print("Enter the destination room: ");
            dRoom = Integer.parseInt(validate(1));

            Floor sourceFloor = getCurrentFloor(sFloor);
            Floor destinationFloor = getCurrentFloor(dFloor);

            destinationFloor.addStudent(sourceFloor.getStudent(sRoom - 1), dRoom - 1);
            sourceFloor.removeStudent(sRoom - 1);

            System.out.println(destinationFloor.getStudent(dRoom - 1).getName() +
                    " was moved to Floor " + dFloor + " Room " + dRoom);

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Enter an appropriate value");
        } catch (EmptyFloorException ex) {
            System.out.println("Please use the first available room on the floor");
        } catch (FullFloorException ex) {
            System.out.print("The current floor is full");
        }
    }

    //Change the current floor
    private static void main_selectFloor() {
        try {
            System.out.print("Enter a floor number: ");
            setFloorNumber(Integer.parseInt(validate(1)));
            System.out.println("You are now on Floor " + currentFloor);

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please select a floor between 1-3");
        }
    }

    //Copy Floors
    private static void main_copyFloor() {
        int sFloor, dFloor;
        try {
            System.out.print("Enter the source floor: ");
            input.nextLine();
            sFloor = Integer.parseInt(validate(1));

            System.out.print("Enter the destination floor: ");
            dFloor = Integer.parseInt(validate(1));

            if (!(sFloor >= 1 && sFloor <= 3) || !(dFloor >= 1 && dFloor <= 3))
                throw new IllegalArgumentException();
            else if (dFloor == 1)
                floor1 = getCurrentFloor(sFloor).clone();
            else if (dFloor == 2)
                floor2 = getCurrentFloor(sFloor).clone();
            else if (dFloor == 3)
                floor3 = getCurrentFloor(sFloor).clone();

            System.out.println("Floor " + sFloor + " was copied to Floor " + dFloor);

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid destination floor");
        }
    }

    //Print the current floor
    private static void main_printCurrentFloor() {
        Floor tempFloor = getCurrentFloor(currentFloor);

        System.out.println("Floor " + currentFloor + ":");
        System.out.printf("%-10s %-15s %-10s %-10s", "Room", "Name", "ID", "Writeups");
        System.out.println("\n----------------------------------------------");
        for (int i = 0; i < tempFloor.CAPACITY; i++) {
            try {
                if (tempFloor.getStudent(i) == null) {
                    break;
                } else {
                    Student tempStudent = tempFloor.getStudent(i);
                    System.out.printf("%-10d %-15s %-10d %-10d\n",
                            i + 1,
                            tempStudent.getName(),
                            tempStudent.getIdNumber(),
                            tempStudent.getNumWriteups());
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            }
            ;
        }
    }

    //Write up a student
    private static void main_writeUpStudent() {

        String name;
        boolean found = false;

        try {
            Floor tempFloor = getCurrentFloor(currentFloor);

            System.out.print("Enter the students name: ");
            input.nextLine();
            name = validate(0);


            for (int i = 0; i < getCurrentFloor(currentFloor).CAPACITY; i++) {
                try {
                    //Found the name
                    if (tempFloor.getStudent(i).getName().equalsIgnoreCase(name)) {

                        //Eviction
                        if (tempFloor.getStudent(i).getNumWriteups() == 2) {
                            System.out.println(tempFloor.getStudent(i).getName()
                                    + " was evicted because they have three writeups.");
                            tempFloor.removeStudent(i);
                            found = true;
                            break;
                        }
                        //Write them up if not evicted
                        else {
                            tempFloor.getStudent(i).writeup();
                            System.out.println(tempFloor.getStudent(i).getName()
                                    + " has " + tempFloor.getStudent(i).getNumWriteups() +
                                    " writeups");
                            found = true;
                            break;
                        }
                    }
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
                }
                ;
            }
            //Name wasn't found
            if (found == false)
                System.out.println("You entered an invalid name");
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Enter a appropriate entry");
        } catch (EmptyFloorException ex) {
        }
        ;
    }

    //Menu
    private static void menu() {
        System.out.println(
                "A) Add a student\n" +
                        "R) Remove a student\n" +
                        "S) Swap Students\n" +
                        "M) Move Student\n" +
                        "F) Select Floor\n" +
                        "C) Copy Floor\n" +
                        "P) Print Current Floor\n" +
                        "W) Write Up Student\n" +
                        "Q) Quit");
    }

    //Helper Method
    private static void setFloorNumber(int floorNumber) {
        if (floorNumber > 3 || floorNumber < 1) {
            throw new IllegalArgumentException();
        }
        currentFloor = floorNumber;
    }

    //Helper Method that returns a floor given the number
    private static Floor getCurrentFloor(int currentFloor) {
        if (currentFloor == 1)
            return floor1;
        else if (currentFloor == 2)
            return floor2;
        else
            return floor3;
    }

    //Helper Method which checks if the input was valid
    private static String validate(int type) {
        input = new Scanner(System.in);
        String testCase;
        int testInteger;
        if (type == 0) {

            testCase = input.nextLine();
            if (testCase.matches("^[a-zA-Z\\s]+$"))
                return testCase;
            else {
                System.out.print("Try again: ");
                return validate(0);
            }
        } else if (type == 1) {
            try {
                testCase = input.nextLine();
                testInteger = Integer.parseInt(testCase);
                return testCase;
            } catch (NumberFormatException ex) {
                System.out.print("Try again: ");
                return validate(1);
            }
        }
        return "";
    }

    //Helper Method to check valid input for the menu
    private static char validateChar() {
        input = new Scanner(System.in);
        return input.next().toLowerCase().charAt(0);
    }

}

