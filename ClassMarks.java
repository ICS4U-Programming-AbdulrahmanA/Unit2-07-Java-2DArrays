import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

// Stores and displays marks for the class.
public class ClassMarks {
    // Starts the program.
    public static void main(String[] args) throws IOException {
        // Read student and assignment names from their text files.
        String[] studentArray = readLines("Unit2-08-students.txt");
        String[] assignmentsArray = readLines("Unit2-08-assignments.txt");

        // Generate one mark for every student-assignment pair.
        int[][] marks = GenerateMarks(studentArray, assignmentsArray);

        // Build the CSV data without a heading row.
        StringBuilder csvData = new StringBuilder();

        // Add one CSV row for each student.
        for (int studentIndex = 0; studentIndex < studentArray.length; studentIndex++) {
            csvData.append(studentArray[studentIndex]);
            for (int assignmentIndex = 0; assignmentIndex < assignmentsArray.length; assignmentIndex++) {
                csvData.append(",").append(marks[studentIndex][assignmentIndex]);
            }
            csvData.append(System.lineSeparator());
        }

        // Save the CSV file in the current folder.
        Files.writeString(Paths.get("ClassMarks.csv"), csvData.toString());
        System.out.println("ClassMarks.csv created.");
    }

    // Return a 2D array where each row is a student and each column is an assignment.
    public static int[][] GenerateMarks(String[] studentArray, String[] assignmentsArray) {
        // Create a random number generator.
        Random random = new Random();
        // Make space for each student's mark on every assignment.
        int[][] marks = new int[studentArray.length][assignmentsArray.length];

        // Fill every student-assignment position with a random mark from 0 to 100.
        for (int studentIndex = 0; studentIndex < studentArray.length; studentIndex++) {
            // Move through each assignment for this student.
            for (int assignmentIndex = 0; assignmentIndex < assignmentsArray.length; assignmentIndex++) {
                // Generate a mark from 0 through 100.
                marks[studentIndex][assignmentIndex] = random.nextInt(101);
            }
        }

        // Send the completed marks back to main.
        return marks;
    }

    // Read all lines in a file and return them as an array of strings.
    private static String[] readLines(String fileName) throws IOException {
        // Split the file contents into an array using its line breaks.
        return Files.readString(Paths.get(fileName)).strip().split("\\R");
    }
}
