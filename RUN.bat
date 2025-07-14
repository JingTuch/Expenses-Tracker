  @ECHO OFF
javac -cp ".;lib/gson-2.13.1.jar" *.java
java -cp ".;lib/gson-2.13.1.jar" Main.java
    PAUSE