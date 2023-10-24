import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Calculator");
        
        // Input grades for assignments, quizzes, exams, etc.
        double assignments = inputGrade(scanner, "assignments");
        double quizzes = inputGrade(scanner, "quizzes");
        double midtermExam = inputGrade(scanner, "midterm exam");
        double finalExam = inputGrade(scanner, "final exam");

        // Calculate the final grade
        double finalGrade = calculateFinalGrade(assignments, quizzes, midtermExam, finalExam);

        // Display the final grade and letter grade
        System.out.println("Your final grade is: " + finalGrade);
        String letterGrade = calculateLetterGrade(finalGrade);
        System.out.println("Letter grade: " + letterGrade);

        scanner.close();
    }

    // Function to input grades
    private static double inputGrade(Scanner scanner, String gradeType) {
        double grade = -1;
        while (grade < 0 || grade > 100) {
            System.out.print("Enter your " + gradeType + " grade (0-100): ");
            grade = scanner.nextDouble();

            if (grade < 0 || grade > 100) {
                System.out.println("Invalid input. Please enter a grade between 0 and 100.");
            }
        }
        return grade;
    }

    // Calculate the final grade
    private static double calculateFinalGrade(double assignments, double quizzes, double midtermExam, double finalExam) {
        return (assignments * 0.3) + (quizzes * 0.2) + (midtermExam * 0.2) + (finalExam * 0.3);
    }

    // Calculate the letter grade
    private static String calculateLetterGrade(double finalGrade) {
        if (finalGrade >= 90) {
            return "A";
        } else if (finalGrade >= 80) {
            return "B";
        } else if (finalGrade >= 70) {
            return "C";
        } else if (finalGrade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
