import java.text.MessageFormat;

public class WordInfo {
    private final String word;
    private final int wordCount;

    public WordInfo(String word, int wordCount) {
        this.word = word;
        this.wordCount = wordCount;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public String generateWordWithCount() {
        return MessageFormat.format("{0} {1}", word, wordCount);
    }
}
