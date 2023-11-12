import java.util.*;

public class QuizGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Quiz> quizzes = new ArrayList<>();

         while (true) {
            System.out.println("1. Create a Quiz"); 
            System.out.println("2. Take a Quiz");
            System.out.println("3. View a Quiz"); 
            System.out.println("4. List of Quizzes");
            System.out.println("5. Exit");
            System.out.println("Enter a command:");

            String command = scanner.nextLine();
            if (command.equals("1")) {
                createQuiz(scanner, quizzes);
            } else if (command.equals("2")) {
                takeQuiz(scanner, quizzes);
            } else if (command.equals("3")) {
                viewQuiz(scanner, quizzes);
            } else if (command.equals("4")) {
                listQuizzes(quizzes);
            } else if (command.equals("5")) {
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    static void createQuiz(Scanner scanner, List<Quiz> quizzes) {
        System.out.println("Enter the name of the quiz:");
        String quizName = scanner.nextLine();
        Quiz quiz = new Quiz(quizName);

        while (true) {
            System.out.print("Enter a question or 'done' to finish: ");
            String question = scanner.nextLine();
            if (question.equalsIgnoreCase("done")) {
                break;
            }

            List<String> choices = new ArrayList<>();
            char choiceChar = 'A';

            while (true) {
                System.out.print("Enter choice " + choiceChar + " or 'done' to finish: ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("done")) {
                    break;
                }
                choices.add(choice);
                choiceChar++;
            }

            System.out.print("Enter the correct choice (e.g., A, B, C): ");
            String correctChoice = scanner.nextLine().toUpperCase();

            quiz.addQuestion(question, choices, correctChoice);
        }

        quizzes.add(quiz);
        System.out.println("Quiz created.");
    }

    static void takeQuiz(Scanner scanner, List<Quiz> quizzes) {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
            return;
        }

        System.out.println("Available Quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getName());
        }
