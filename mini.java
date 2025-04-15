import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class mini {
    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static ArrayList<String[]> questions = new ArrayList<>();
    private static ArrayList<String[]> selectedQuestions = new ArrayList<>();

    public static void main(String[] args) {
        // Predefined pool of questions
        questions.add(new String[]{"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "3"});
        questions.add(new String[]{"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "2"});
        questions.add(new String[]{"Who wrote 'Hamlet'?", "Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen", "2"});
        questions.add(new String[]{"What is the largest ocean on Earth?", "Atlantic", "Indian", "Arctic", "Pacific", "4"});
        questions.add(new String[]{"What is the square root of 64?", "6", "7", "8", "9", "3"});
        questions.add(new String[]{"What is the chemical symbol for water?", "H2O", "O2", "CO2", "NaCl", "1"});
        questions.add(new String[]{"Who painted the Mona Lisa?", "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet", "3"});
        questions.add(new String[]{"What is the smallest prime number?", "0", "1", "2", "3", "3"});

        // Randomly select 5 questions for the quiz
        Collections.shuffle(questions);
        selectedQuestions.addAll(questions.subList(0, 5));

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quiz Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel questionLabel = new JLabel(selectedQuestions.get(currentQuestionIndex)[0], SwingConstants.CENTER);
            panel.add(questionLabel, BorderLayout.NORTH);

            JPanel optionsPanel = new JPanel();
            optionsPanel.setLayout(new GridLayout(4, 1));
            ButtonGroup buttonGroup = new ButtonGroup();
            JRadioButton[] options = new JRadioButton[4];
            for (int i = 0; i < 4; i++) {
                options[i] = new JRadioButton(selectedQuestions.get(currentQuestionIndex)[i + 1]);
                buttonGroup.add(options[i]);
                optionsPanel.add(options[i]);
            }
            panel.add(optionsPanel, BorderLayout.CENTER);

            JButton nextButton = new JButton("Next");
            panel.add(nextButton, BorderLayout.SOUTH);

            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedOption = -1;
                    for (int i = 0; i < 4; i++) {
                        if (options[i].isSelected()) {
                            selectedOption = i + 1;
                            break;
                        }
                    }

                    if (selectedOption == -1) {
                        JOptionPane.showMessageDialog(frame, "Please select an option!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (String.valueOf(selectedOption).equals(selectedQuestions.get(currentQuestionIndex)[5])) {
                        score++;
                    }

                    currentQuestionIndex++;
                    if (currentQuestionIndex < selectedQuestions.size()) {
                        questionLabel.setText(selectedQuestions.get(currentQuestionIndex)[0]);
                        buttonGroup.clearSelection();
                        for (int i = 0; i < 4; i++) {
                            options[i].setText(selectedQuestions.get(currentQuestionIndex)[i + 1]);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Quiz Over! Your final score is: " + score + "/" + selectedQuestions.size());
                        frame.dispose();
                    }
                }
            });

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
