import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatbotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JButton clearButton;

    private NLPChatbot chatbot;

    public ChatbotGUI() {

        chatbot = new NLPChatbot();

        setTitle("CodeAlpha AI Chatbot");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Buttons
        sendButton = new JButton("Send");
        clearButton = new JButton("Clear");

        // Bottom panel
        JPanel bottomPanel =
                new JPanel(new BorderLayout(10, 10));

        bottomPanel.add(
                inputField,
                BorderLayout.CENTER
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Welcome message
        chatArea.append(
                "AI Chatbot: Hello! I am your AI Assistant.\n"
        );

        chatArea.append(
                "AI Chatbot: Ask me about Java, AI, NLP or Machine Learning.\n\n"
        );

        // Send button
        sendButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        sendMessage();
                    }
                }
        );

        // Enter key
        inputField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        sendMessage();
                    }
                }
        );

        // Clear button
        clearButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        chatArea.setText("");

                        chatArea.append(
                                "AI Chatbot: Chat cleared.\n\n"
                        );
                    }
                }
        );
    }

    private void sendMessage() {

        String userMessage =
                inputField.getText().trim();

        if (userMessage.isEmpty()) {
            return;
        }

        // Display user message
        chatArea.append(
                "You: " + userMessage + "\n"
        );

        // Get response
        String response =
                chatbot.getResponse(userMessage);

        // Display response
        chatArea.append(
                "AI Chatbot: " + response + "\n\n"
        );

        // Clear input
        inputField.setText("");

        // Scroll to bottom
        chatArea.setCaretPosition(
                chatArea.getDocument().getLength()
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ChatbotGUI gui =
                    new ChatbotGUI();

            gui.setVisible(true);
        });
    }
}