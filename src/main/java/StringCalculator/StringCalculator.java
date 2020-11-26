package StringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import Exception.NegativeNumberException;

public class StringCalculator {

    private final String BASIC_REGEX = "[,\n]";

    public int add(String numbers) throws NegativeNumberException {
        boolean numbersOnlyContainsOneNumber = numbers.split(BASIC_REGEX).length == 1 && !(numbers.startsWith("//"));
        boolean numbersIsEmpty = numbers.length() == 0;

        if (numbersIsEmpty) {
            return 0;
        }else if (numbersOnlyContainsOneNumber) {
            if(numberIsNegative(numbers)) {
                throw new NegativeNumberException("negatives not allowed");
            }else if (numberIsGreaterThan1000(numbers)){
                return 0;
            }
            return Integer.parseInt(numbers);
        }
        String[] numbersToAdd = getNumbers(numbers);
        return performSum(numbersToAdd);
    }

    private int performSum(String[] numbers) throws NegativeNumberException {
        int result = 0;

        for(String number: numbers) {
            if(numberIsNegative(number)) {
                throw new NegativeNumberException("negatives not allowed");
            } else if(!numberIsGreaterThan1000(number)) {
                result += Integer.parseInt(number);
            }
        }
        return result;
    }

    private String[] getNumbers(String numbers) {
        boolean hasCustomDelimiter = numbers.startsWith("//");

         if(hasCustomDelimiter) {
            String[] delimitersAndNumbers = numbers.substring(2).split("\n");
            String delimiterPart = delimitersAndNumbers[0];
            String numberPart = delimitersAndNumbers[1];
            return getListOfNumbersWithRegexAndNumbers(numberPart,delimiterPart);
        } else {
             return getListOfNumbersWithRegexAndNumbers(numbers, BASIC_REGEX);
        }
    }

    private String[] getListOfNumbersWithRegexAndNumbers(String numbers, String delimiter) {
        boolean delimitersAreSeparatedBySquareBrackets = delimiter.startsWith("[")
                && delimiter.endsWith("]") && !delimiter.equals(BASIC_REGEX);

        String delimiterRegex;
        if(delimitersAreSeparatedBySquareBrackets){
            delimiterRegex = getDelimiterRegex(delimiter);
        }else{
            delimiterRegex = removeDelimiterDuplicates(delimiter);
        }

        String[] rawNumberString = numbers.split(delimiterRegex);
        List<String> listOfNumbersToAdd = new ArrayList<>();

        for(String value:rawNumberString){
            boolean isANumber = !value.isEmpty();
            if(isANumber) listOfNumbersToAdd.add(value);
        }

        String[] result = new String[listOfNumbersToAdd.size()];
        return listOfNumbersToAdd.toArray(result);
    }

    private String getDelimiterRegex(String delimiter){
        String[] parts = delimiter.split("]");
        StringBuilder delimiters = new StringBuilder();

        for(int i = 0;i < parts.length;i++){
            if(!parts[i].isEmpty()){
                delimiters.append(parts[i].substring(1));
            }
        }
        boolean onlyOneDelimiter = parts.length == 1;
        if(onlyOneDelimiter) return removeDelimiterDuplicates(delimiters.toString());
        return "["+ removeDelimiterDuplicates(delimiters.toString())+"]";
    }

    private String removeDelimiterDuplicates(String result) {
        return Arrays.asList(result.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
    }

    private boolean numberIsNegative(String number){
        return Integer.parseInt(number) < 0;
    }

    private boolean numberIsGreaterThan1000(String number){
        return Integer.parseInt(number) > 1000;
    }
}
