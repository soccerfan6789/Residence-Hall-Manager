
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
public class Floor {
    final int CAPACITY = 5;
    int count;
    private Student[] students;

    /**
     * Creates a new Floor with an initialized Capacity
     */
    public Floor() {
        students = new Student[CAPACITY];
    }

    /**
     * Add Student Method -> Adds the student to the students array
     *
     * @param student  -> Student Object
     * @param position -> Position where student would be placed in students array
     * @throws FullFloorException  -> If the if floor is full
     * @throws EmptyFloorException -> If Floor is empty
     * @returns -> Nothing
     */
    public void addStudent(Student student, int position) throws FullFloorException, EmptyFloorException {

        //Checks if the position entered is the first open empty room
        if (position >= count + 1)
            throw new EmptyFloorException();

        //Check if floor is full
        boolean full = true;
        for (int i = 0; i < CAPACITY; i++) {
            if (students[i] == null) {
                full = false;
                break;
            }
        }

        //Throw exceptions or continue
        if (full == true)
            throw new FullFloorException();
        else if (position >= CAPACITY || position < 0) {
            throw new IllegalArgumentException();
        } else
            count++;

        //If not used, put student in array. Else Shift Right, then put in
        if (students[position] == null) {
            students[position] = student;
        } else {
            for (int i = count - 1; i >= position; i--) {
                if (students[i] != null) {
                    students[i + 1] = students[i];
                }
            }
            students[position] = student;
        }
    }

    /**
     * Remove Student Method removes the student from the students
     * array indicated by the position parameter
     *
     * @param position -> Position of Student in students array
     * @return -> Student at that position
     * @throws EmptyFloorException -> No student that the position
     */
    public Student removeStudent(int position) throws EmptyFloorException {

        //Check if valid position
        if (students[position] == null)
            throw new EmptyFloorException();
        else if (position >= CAPACITY || position < 0) {
            throw new IllegalArgumentException();
        }

        //Create tempStudent, set actual student to null, reduce count
        Student tempStudent = (Student) students[position].clone();
        students[position] = null;
        count--;

        //Shift Left
        for (int i = position + 1; i <= CAPACITY - 1; i++) {
            if (students[i] != null) {
                students[i - 1] = students[i];
                students[i] = null;
            }
        }

        //Return the Student copy
        return tempStudent;
    }

    /**
     * Get Student Method returns the student at the indicated position
     *
     * @param position -> Position of Student in students array
     * @return Student at the position indicated
     * @throws IllegalArgumentException -> Position is invalid
     */
    public Student getStudent(int position) {

        //Check if position in array is valid and throw Exception if necessary
        if (students[position] == null
                || position >= CAPACITY
                || position < 0) {
            throw new IllegalArgumentException();
        }

        //Return the Student at the position
        return students[position];
    }

    /**
     * Sets a student at the given position
     *
     * @param student  -> Student Object
     * @param position -> Position desired
     * @throws IllegalArgumentException -> Position invalid
     * @returns -> Nothing
     */
    public void setStudent(Student student, int position) {
        if (position >= CAPACITY || position < 0) {
            throw new IllegalArgumentException();
        }

        //Add Student to position
        students[position] = student;
    }

    /**
     * Count Method returns number of students, count
     * O(1)
     *
     * @return Number of students in students array
     */
    public int count() {
        return count;
    }

    /**
     * Creates a clone of the current Floor
     *
     * @return Floor Clone
     */
    public Floor clone() {
        //Deep Copy
        Floor floorClone = new Floor();

        //Iterates through students array and clones students to new tempFloor
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                floorClone.students[i] = (Student) this.students[i].clone();
            }
        }

        return floorClone;
    }
}