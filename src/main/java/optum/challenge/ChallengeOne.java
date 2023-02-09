package optum.challenge;

import java.util.*;

class ChallengeOne {

    static String CHALLENGE_TOKEN = "2y6icnuob4f";

    public static void main(String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(StringChallenge(s.nextLine()));
    }

    public static String StringChallenge(String str) {

        Map<String, String> htmlTagMap = new HashMap<String, String>();
        htmlTagMap.put("<div>", "</div>");
        htmlTagMap.put("<p>", "</p>");
        htmlTagMap.put("<b>", "</b>");
        htmlTagMap.put("<i>", "</i>");
        htmlTagMap.put("<em>", "</em>");

        // These data structures will maintain where each key (opening/closing html tag) occurs in
        // the input String
        Map<String, List<Integer>> openingTagOccurrenceMap = new HashMap<String, List<Integer>>();
        openingTagOccurrenceMap.put("<div>", new ArrayList<>());
        openingTagOccurrenceMap.put("<p>", new ArrayList<>());
        openingTagOccurrenceMap.put("<b>", new ArrayList<>());
        openingTagOccurrenceMap.put("<i>", new ArrayList<>());
        openingTagOccurrenceMap.put("<em>", new ArrayList<>());

        Map<String, List<Integer>> closingTagOccurrenceMap = new HashMap<String, List<Integer>>();
        closingTagOccurrenceMap.put("</div>", new ArrayList<Integer>());
        closingTagOccurrenceMap.put("</p>", new ArrayList<Integer>());
        closingTagOccurrenceMap.put("</b>", new ArrayList<Integer>());
        closingTagOccurrenceMap.put("</i>", new ArrayList<Integer>());
        closingTagOccurrenceMap.put("</em>", new ArrayList<Integer>());

        List<Integer> allOpeningIndexesMatched = listAllOccurrencesInAscendingOrder(openingTagOccurrenceMap, str);
        List<Integer> allClosingIndexesMatched = listAllOccurrencesInAscendingOrder(closingTagOccurrenceMap, str);

        List<String> openingTagsInOrderOfAppearance = mapIndexBackToWord(allOpeningIndexesMatched, openingTagOccurrenceMap);
        List<String> closingTagsInOrderOfAppearance = mapIndexBackToWord(allClosingIndexesMatched, closingTagOccurrenceMap);

        return replaceEveryFourthCharacter(getTheFirstIncorrectTag(htmlTagMap, openingTagsInOrderOfAppearance, closingTagsInOrderOfAppearance));
    }

    /**
     * return the first incorrect tag that is not correctly nested.
     * @param htmlTagMap
     * @param openingTagsInOrderOfAppearance
     * @param closingTagsInOrderOfAppearance
     * @return
     */
    public static String getTheFirstIncorrectTag(Map<String, String> htmlTagMap, List<String> openingTagsInOrderOfAppearance, List<String> closingTagsInOrderOfAppearance) {
        for (String openingTag: openingTagsInOrderOfAppearance) {
            String closingTag = htmlTagMap.get(openingTag);

            int numberOfOpeningTags = Collections.frequency(openingTagsInOrderOfAppearance, openingTag);
            int numberOfClosingTags = Collections.frequency(closingTagsInOrderOfAppearance, closingTag);

            if ( numberOfOpeningTags > numberOfClosingTags ) {
                return openingTag.replace("<", "").replace(">", "");
            } else if (numberOfClosingTags > numberOfOpeningTags) {
                return closingTag.replace("<", "").replace(">", "").substring(0, closingTag.length() - 1 );
            }
        }
        return null;
    }

    /**
     * Combine with the challenge and replace every fourth character before returning.
     * @param firstIncorrectTag
     * @return the amended string
     */
    public static String replaceEveryFourthCharacter(String firstIncorrectTag) {
        // initialising to 20 as any tag plus the challenge would not be longer in character length
        StringBuilder stringBuilder = new StringBuilder(20);
        stringBuilder.append(firstIncorrectTag).append(CHALLENGE_TOKEN);
        int numberOfEveryFourthCharacter = stringBuilder.length() / 4;
        int i = 0;
        int characterPosition = 3;
        while (i < numberOfEveryFourthCharacter) {
            stringBuilder.setCharAt(characterPosition, '_');
            characterPosition = characterPosition + 4;
            i++;
        }
        return stringBuilder.toString();
    }

    /**
     * Given a list of indexes, that recorded where a word occurred map the index occurrence back to the word
     * that was originally matched.
     * @param allIndexesMatched
     * @param indexesOfWordOccurrencesMap
     * @return
     */
    public static List<String> mapIndexBackToWord(List<Integer> allIndexesMatched, Map<String, List<Integer>> indexesOfWordOccurrencesMap) {

        List<String> wordsInOrder = new ArrayList<String>();
        for (Integer index: allIndexesMatched) {
            for (String word: indexesOfWordOccurrencesMap.keySet()) {
                if (indexesOfWordOccurrencesMap.get(word).contains(index)) {
                    wordsInOrder.add(word);
                }
            }
        }
        return wordsInOrder;
    }

    /**
     * Given a Map of Words identify where each occurs in the input String argument.
     * return the indexes of all occurrences sorted in ascending order.
     * @param mapOfWordOccurrences
     * @param inputString
     * @return
     */
    public static List<Integer> listAllOccurrencesInAscendingOrder(Map<String, List<Integer>> mapOfWordOccurrences, String inputString) {

        List<Integer> allIndexesMatched = new ArrayList<Integer>();
        mapOfWordOccurrences.forEach((word, listOfIndexes) -> allIndexesMatched.addAll(listIndexOfEachOccurrence(inputString, word, listOfIndexes) ));
        Collections.sort(allIndexesMatched);

        return allIndexesMatched;
    }

    /**
     * Return the list of indexes in a String where the word argument occurs.
     * This is a case insensitive search.
     * This uses a brute force approach. @TODO Performance Review
     * @param textString
     * @param word
     * @return the list of indexes, NonNull
     */
    public static List<Integer> listIndexOfEachOccurrence(String textString, String word, List<Integer> listOfIndexes) {

        String lowerCaseTextString = textString.toLowerCase();
        String lowerCaseWord = word.toLowerCase();

        int index = 0;
        while(index != -1){
            index = lowerCaseTextString.indexOf(lowerCaseWord, index);
            if (index != -1) {
                listOfIndexes.add(index);
                index++;
            }
        }
        return listOfIndexes;
    }
}