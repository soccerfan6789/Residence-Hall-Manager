
/**
 * Homework Assignment 1
 * CSE 214 Recitation 8
 * Recitation TA: Daniel Scanteianu
 * Grading TA: ?
 *
 * @author Shariq Syed
 *         ID: ##########
 *         NETID: ##########
 */
public class Student {

    //Constants and variables
    final int MAX_WRITEUPS = 3;
    private String name;
    private int idNumber;
    private int numWriteups;

    /**
     * Empty Student Constructor
     */
    public Student() {
        this.name = null;
        this.idNumber = -1;
    }

    /**
     * Student Constructor
     *
     * @param name -> Name
     * @param idNumber -> ID Number
     * @param numWriteups -> Number of Writeups
     */
    public Student(String name, int idNumber, int numWriteups) {
        this.name = name;
        this.idNumber = idNumber;
        this.numWriteups = numWriteups;
    }

    /**
     * Getter for Name
     *
     * @return -> Name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for ID Number
     *
     * @return -> ID Number
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * Getter for Writeups
     *
     * @return -> Writeups
     */
    public int getNumWriteups() {
        return numWriteups;
    }

    /**
     * Increments Writeups
     *
     * @return -> Nothing
     */
    public void writeup() {
        numWriteups++;
    }

    /**
     * Clones the Students attributes
     *
     * @return Student Clone
     */
    public Object clone() {
        Student clone = new Student();

        clone.name = this.getName();
        clone.idNumber = this.getIdNumber();
        clone.numWriteups = this.getNumWriteups();

        return clone;
    }
}
