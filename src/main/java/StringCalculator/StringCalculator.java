package StringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private String BASIC_REGEX = "[,\n]";

    public int add(String numbers) throws Exception {
        if(numbers.length() == 0) return 0;
        if(numbers.split(BASIC_REGEX).length == 1 && !(numbers.startsWith("//"))){
            if(Integer.parseInt(numbers) < 0) {
                throw new Exception("negatives not allowed");
            }else if (Integer.parseInt(numbers) > 1000){
                return 0;
            }
            return Integer.parseInt(numbers);
        }
        String[] newNumbers = getNumbers(numbers);
        return performSum(newNumbers);

    }

    private int performSum(String[] parts) throws Exception {
        int sumResult = 0;

        for(String s: parts) {
            if(Integer.parseInt(s) < 0) {
                throw new Exception("negatives not allowed");
            } else if(Integer.parseInt(s) <= 1000) {
                sumResult += Integer.parseInt(s);
            }
        }

        return sumResult;
    }

    private String[] getNumbers(String numbers) throws Exception {
        String[] result = new String[2];

         if(numbers.startsWith("//")){
            String[] parts = numbers.substring(2).split("\n");
            String[] newNumbers = getNumbersToAdd(parts[1],parts[0]);
            return newNumbers;
        } else {
            String[] parts = getNumbersToAdd(numbers, BASIC_REGEX);
            return parts;
        }
    }

    private String[] getNumbersToAdd(String numbers, String delimiter) throws Exception {
        if(delimiter.startsWith("[") && delimiter.endsWith("]") && !delimiter.equals(BASIC_REGEX)){
            delimiter = getDelimiters(delimiter);
        }else{
            delimiter = removeDuplicates(delimiter);
        }
        String[] parts = numbers.split(delimiter);
        List<String> results = new ArrayList<>();

        for(String s:parts){
            if(!s.isEmpty()) results.add(s);
        }
        String result[] = new String[results.size()];
        return results.toArray(result);
    }

    private String getDelimiters(String delimeter){
        String[] parts = delimeter.split("]");
        String result = new String();
        for(int i = 0;i < parts.length;i++){
            if(!parts[i].isEmpty()){
                result += parts[i].substring(1,parts[i].length());
            }
        }
        if(parts.length == 1) return removeDuplicates(result);
        return "["+ removeDuplicates(result)+"]";
    }

    private String removeDuplicates(String result) {
        String noDuplicates = Arrays.asList(result.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
        return noDuplicates;
    }
}
