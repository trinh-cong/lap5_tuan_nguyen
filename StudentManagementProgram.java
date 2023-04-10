package lap5_tuan_nguyen;

import java.io.*;
import java.util.*;

class Student {
    private int rollNumber;
    private String name;
    private int age;
    private int mark;

    public Student(int rollNumber, String name, int age, int mark) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.mark = mark;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Age: " + age + ", Mark: " + mark;
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(int rollNumber, String newName, int newAge, int newMark) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                student.setName(newName);
                student.setAge(newAge);
                student.setMark(newMark);
                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void deleteStudent(int rollNumber) {
        Iterator<Student> iter = students.iterator();
        while (iter.hasNext()) {
            Student student = iter.next();
            if (student.getRollNumber() == rollNumber) {
                iter.remove();
                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public Student findStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }

    public void readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                students = (List<Student>) obj;
            } else {
                System.out.println("Invalid object type: " + obj.getClass().getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading from file: " + e.getMessage());
        }
    }
}

public class StudentManagementProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Update a Student");
            System.out.println("3. Delete a Student");
            System.out.println("4. Search Students");
            System.out.println("5. Display All Students");
            System.out.println("6. Save to File");
            System.out.println("7. Load from File");
            System.out.println("8. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();

                    System.out.print("Enter mark: ");
                    int mark = scanner.nextInt();

                    manager.addStudent(new Student(rollNumber, name, age, mark));
                    break;

                case 2:
                    System.out.print("Enter roll number of the student to update: ");
                    rollNumber = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();

                    System.out.print("Enter new age: ");
                    age = scanner.nextInt();

                    System.out.print("Enter new mark: ");
                    mark = scanner.nextInt();

                    manager.updateStudent(rollNumber, name, age, mark);
                    break;

                case 3:
                    System.out.print("Enter roll number of the student to delete: ");
                    rollNumber = scanner.nextInt();

                    manager.deleteStudent(rollNumber);
                    break;

                case 4:
                    System.out.print("Enter roll number of the student to search: ");
                    rollNumber = scanner.nextInt();

                    Student student = manager.findStudent(rollNumber);
                    if (student != null) {
                        System.out.println(student);
                    }

                    break;

                case 5:
                    manager.displayAllStudents();
                    break;

                case 6:
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();

                    manager.saveToFile(fileName);
                    break;

                case 7:
                    System.out.print("Enter file name: ");
                    fileName = scanner.nextLine();

                    manager.readFromFile(fileName);
                    break;

                case 8:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println();
        }
    }
}
