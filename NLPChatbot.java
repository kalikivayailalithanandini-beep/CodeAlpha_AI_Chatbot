import java.util.ArrayList;
import java.util.List;

public class NLPChatbot {

    private List<FAQ> faqList;

    public NLPChatbot() {

        faqList = new ArrayList<>();

        faqList.add(new FAQ(
                "hello",
                "Hello! How can I help you?"
        ));

        faqList.add(new FAQ(
                "hi",
                "Hi! Nice to meet you."
        ));

        faqList.add(new FAQ(
                "what is your name",
                "I am an AI Chatbot developed using Java and NLP."
        ));

        faqList.add(new FAQ(
                "who are you",
                "I am a Java-based Artificial Intelligence Chatbot."
        ));

        faqList.add(new FAQ(
                "what is java",
                "Java is a high-level, object-oriented programming language."
        ));

        faqList.add(new FAQ(
                "what is artificial intelligence",
                "Artificial Intelligence is the ability of machines to perform tasks that normally require human intelligence."
        ));

        faqList.add(new FAQ(
                "what is nlp",
                "NLP stands for Natural Language Processing. It helps computers understand and process human language."
        ));

        faqList.add(new FAQ(
                "what is machine learning",
                "Machine Learning is a branch of AI that allows computers to learn from data and improve their performance."
        ));

        faqList.add(new FAQ(
                "what is chatbot",
                "A chatbot is a software application that communicates with users using natural language."
        ));

        faqList.add(new FAQ(
                "how are you",
                "I am doing great! Thanks for asking."
        ));

        faqList.add(new FAQ(
                "thank you",
                "You're welcome! Happy to help."
        ));

        faqList.add(new FAQ(
                "bye",
                "Goodbye! Have a nice day."
        ));
    }

    // NLP preprocessing
    private String preprocess(String text) {

        text = text.toLowerCase();

        text = text.replaceAll("[^a-zA-Z0-9 ]", "");

        text = text.replaceAll("\\s+", " ").trim();

        return text;
    }

    // Similarity calculation
    private double calculateSimilarity(
            String userInput,
            String faqQuestion) {

        String[] userWords = userInput.split(" ");
        String[] faqWords = faqQuestion.split(" ");

        int matchingWords = 0;

        for (String userWord : userWords) {

            for (String faqWord : faqWords) {

                if (userWord.equals(faqWord)) {
                    matchingWords++;
                    break;
                }
            }
        }

        if (userWords.length == 0) {
            return 0;
        }

        return (double) matchingWords / userWords.length;
    }

    // Generate response
    public String getResponse(String userInput) {

        String processedInput = preprocess(userInput);

        if (processedInput.isEmpty()) {
            return "Please enter a question.";
        }

        double highestSimilarity = 0;
        String bestAnswer = null;

        for (FAQ faq : faqList) {

            String processedQuestion =
                    preprocess(faq.getQuestion());

            double similarity =
                    calculateSimilarity(
                            processedInput,
                            processedQuestion
                    );

            if (similarity > highestSimilarity) {

                highestSimilarity = similarity;
                bestAnswer = faq.getAnswer();
            }
        }

        if (highestSimilarity >= 0.30) {
            return bestAnswer;
        }

        return "Sorry, I don't understand your question.";
    }
}