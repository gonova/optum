package optum.challenge;

import java.util.*;
import java.io.*;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ChallengeTwo {

    public static String StringChallenge(String str) {

        if (str.length() > 2) {

            Predicate<StringBuilder> canBeReduced = reducedString -> ((reducedString.charAt(reducedString.length()-2) != reducedString.charAt(reducedString.length()-1)));

            UnaryOperator<StringBuilder> reductionAction = reducedString -> reducedString.length() == 2 ? reducedString.delete(reducedString.length()-2,reducedString.length()) : new StringBuilder("");

            // initialise the String to check
            StringBuilder initialString = new StringBuilder(str);

            initialString = reduceString(initialString, canBeReduced, reductionAction);

            return initialString != null ? "" + initialString.length() : "0";
        }
        return str != null ? "" + str.length() : "0";
    }

    /**
     *
     * @param stringToCheck
     * @param canBeReduced
     * @param reductionAction
     * @return
     */
    public static StringBuilder reduceString(StringBuilder stringToCheck, Predicate<StringBuilder> canBeReduced, UnaryOperator<StringBuilder> reductionAction) {

        StringBuilder reducedString = null;

        // initialise the reduced String
        if (stringToCheck.length() == 3) {
            // initialise the reduced String
            reducedString = new StringBuilder(stringToCheck.substring(0,2));

            if (canBeReduced.test(reducedString)) {
                return reductionAction.apply(reducedString).append(stringToCheck.charAt(2));
            }

        } else {
            // initialise the reduced String
            reducedString = new StringBuilder(stringToCheck.substring(0,2));

            for (int i = 2; i < stringToCheck.length(); i++) {
                String nextElement = "" + stringToCheck.charAt(i);
                if (reducedString.length() >= 2 && canBeReduced.test(reducedString)) {
                    reductionAction.apply(reducedString).append(nextElement);
                } else {
                    reducedString.append(nextElement);
                }
            }

            return reducedString;
        }
        return new StringBuilder();
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(StringChallenge(s.nextLine()));
    }

}
