import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private final String BLANK_SPACE = "\\s+";

    public String getResult(String sentence) {
        if (sentence.split(BLANK_SPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<String> words = splitByWords(sentence);
                List<WordInfo> wordInfoList = generateWordsInfo(words);

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : wordInfoList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> generateWordsInfo(List<String> words) {
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordInfo> wordInfos = new LinkedList<>();
        distinctWords.forEach(distinctWord -> {
            int count = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            wordInfos.add(new WordInfo(distinctWord, count));
        });
        return wordInfos;
    }

    private List<String> splitByWords(String sentence) {
        return Arrays.asList(sentence.split(BLANK_SPACE));
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            //       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
