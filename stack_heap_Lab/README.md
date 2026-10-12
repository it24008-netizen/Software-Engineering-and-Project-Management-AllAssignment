#### Stack and Heap in Java ####
## Name: Apurbo Kumar Roy | ID: IT24008 ##

### Files ###
~~~
StudentMemoryDemo.java - Problem 1 (Student: instance variable, local variable, identity hash code)
EmployeeMemoryDemo.java - Problem 2 (Employee with local inner class Report)
~~~
### How to run ###
~~~
javac StudentMemoryDemo.java
java StudentMemoryDemo

javac EmployeeMemoryDemo.java
java EmployeeMemoryDemo
~~~
### Output ###
<img width="1400" height="774" alt="image" src="https://github.com/user-attachments/assets/f8790770-b444-4bfb-9e78-5a5742dce661" />

### Memory concepts ###
~~~
Heap: objects created with new (Student, Employee, Report) and their instance fields (age, salary).
Stack: local variables (marks, bonus) and reference variables (s, e, r) live in method stack frames. show() runs in its own frame.
System.identityHashCode() gives an object identity number, not the real memory address, so it differs between runs.
~~~
