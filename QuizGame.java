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

        scanner.close();
    }

    static void createQuiz(Scanner scanner, List<Quiz> quizzes) {
        System.out.println("Enter the name of the quiz:");
        String quizName = scanner.nextLine();
        Quiz quiz = new Quiz(quizName);

        System.out.print("Enter the number of questions for the quiz: ");
        int numberOfQuestions = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.print("Enter question " + (i + 1) + ": ");
            String question = scanner.nextLine();

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

        int quizNumber = readInt(scanner, "Enter the number of the quiz you want to take: ", 1, quizzes.size());
        Quiz selectedQuiz = quizzes.get(quizNumber - 1);
        int score = 0;

        for (int i = 0; i < selectedQuiz.getQuestionCount(); i++) {
            Question currentQuestion = selectedQuiz.getQuestion(i);

            System.out.println("Q" + (i + 1) + ": " + currentQuestion.getQuestion());

            char choiceChar = 'A';

            for (String choice : currentQuestion.getChoices()) {
                System.out.println(choiceChar + ": " + choice);
                choiceChar++;
            }

            System.out.print("Your choice (A, B, C, ...): ");
            String userAnswer = scanner.nextLine().toUpperCase();

            if (userAnswer.equals(currentQuestion.getCorrectChoice())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + currentQuestion.getCorrectChoice());
            }
        }

        System.out.println("Quiz completed. Your score: " + score + " out of " + selectedQuiz.getQuestionCount());
    }

    static void viewQuiz(Scanner scanner, List<Quiz> quizzes) {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
            return;
        }

        System.out.println("Available Quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getName());
        }

        int quizNumber = readInt(scanner, "Enter the number of the quiz you want to view: ", 1, quizzes.size());
        Quiz selectedQuiz = quizzes.get(quizNumber - 1);

        System.out.println("Quiz: " + selectedQuiz.getName());

        for (int i = 0; i < selectedQuiz.getQuestionCount(); i++) {
            Question currentQuestion = selectedQuiz.getQuestion(i);

            System.out.println("Q" + (i + 1) + ": " + currentQuestion.getQuestion());

            char choiceChar = 'A';

            for (String choice : currentQuestion.getChoices()) {
                System.out.println(choiceChar + ": " + choice);
                choiceChar++;
            }

            System.out.println("Correct Answer: " + currentQuestion.getCorrectChoice());
        }
    }

    static void listQuizzes(List<Quiz> quizzes) {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
        } else {
            System.out.println("Available Quizzes:");
            for (int i = 0; i < quizzes.size(); i++) {
                System.out.println((i + 1) + ". " + quizzes.get(i).getName());
            }
        }
    }
}
