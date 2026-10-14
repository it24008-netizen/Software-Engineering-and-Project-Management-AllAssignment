class Student {
    int age;

    Student(int age) {
        this.age = age;
    }

    void display() {
        int marks = 85;

        System.out.println("Instance Variable (age): " + age);
        System.out.println("Local Variable (marks): " + marks);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student(20);

        System.out.println("Student Object Created");
        System.out.println("Object Identity Hash Code: "
                + System.identityHashCode(s));
        s.display();
    }
}
