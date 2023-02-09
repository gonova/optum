package optum.challenge;

import java.util.*;
import java.io.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Stream;

class ChallengeTwo {

    public static String StringChallenge(String str) {

        // only letters a,b and c will be input - can ignore error handling for now

        // split the string up into a list of three character strings
        // note not all groups will be of length 3 e.g. str = "abcab" or "aa"
        String[] groupsOfThree = str.split("(?<=\\G...)");

        Predicate<String> canBeReduced = element -> (element.length() == 3 && (element.charAt(0) != element.charAt(1)));
        UnaryOperator<String> reductionAction = element -> element.substring(2,3).concat(element.substring(2,3));

        String newString = Arrays.stream(groupsOfThree).reduce("", ( reducedString, element)
                -> canBeReduced.test(element) ? reducedString.concat(reductionAction.apply(element)) : reducedString.concat(element) );

        return newString != null ? "" + newString.length() : "0";
    }



    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(StringChallenge(s.nextLine()));
    }

}
