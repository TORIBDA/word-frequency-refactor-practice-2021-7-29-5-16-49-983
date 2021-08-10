import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private final String BLANK_SPACE = "\\s+";
    private final String CALCULATE_ERROR_MESSAGE = "Calculate Error";
    private final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        try {
            List<String> words = splitByWords(sentence);
            List<WordInfo> wordInfoList = generateWordsInfo(words);
            wordInfoList = sortWordsDescending(wordInfoList);
            return generateResultingWordFrequency(wordInfoList);
        } catch (Exception e) {
            //TODO: use customized exception to be thrown when failed to execute code
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private String generateResultingWordFrequency(List<WordInfo> wordsInfo) {
        return wordsInfo.stream()
                .map(WordInfo::generateWordWithCount)
                .collect(Collectors.joining(NEW_LINE));
    }

    private List<WordInfo> sortWordsDescending(List<WordInfo> wordInfoList) {
        //TODO: use sort
        return wordInfoList.stream()
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .collect(Collectors.toList());
    }

    private List<WordInfo> generateWordsInfo(List<String> words) {
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordInfo> wordInfos = new LinkedList<>();
        distinctWords.forEach(distinctWord -> {
            int count = computeWordCountFromWords(distinctWord, words);
            wordInfos.add(new WordInfo(distinctWord, count));
        });
        return wordInfos;
    }

    private int computeWordCountFromWords(String distinctWord, List<String> words) {
        //TODO: check collections frequency
        return (int) words.stream().filter(word -> word.equals(distinctWord)).count();
    }

    private List<String> splitByWords(String sentence) {
        return Arrays.asList(sentence.split(BLANK_SPACE));
    }
}
