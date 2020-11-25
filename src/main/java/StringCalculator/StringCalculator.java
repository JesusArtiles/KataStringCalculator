package StringCalculator;

public class StringCalculator {

    private String BASIC_REGEX = "[,\n]";

    public int add(String numbers) throws Exception {
        if(numbers.length() == 0) return 0;
        if(numbers.split(BASIC_REGEX).length == 1){
            if(Integer.parseInt(numbers) < 0) throw new Exception("negatives not allowed");
            return Integer.parseInt(numbers);
        }

        String[] regexAndNumbers = getRegexAndNumbers(numbers);
        numbers = regexAndNumbers[1];
        String regex = regexAndNumbers[0];

        String[] parts = numbers.split(regex);
        if (parts.length == 1) {
            return Integer.parseInt(numbers);
        } else if (parts.length > 1) {
            return getSum(parts);
        }

        return 0;
    }

    private int getSum(String[] parts) throws Exception {
        int sumResult = 0;

        for(String s: parts) {
            if(Integer.parseInt(s) < 0) throw new Exception("negatives not allowed");
            sumResult += Integer.parseInt(s);
        }

        return sumResult;
    }

    private String[] getRegexAndNumbers(String numbers){
        String[] result = new String[2];

         if(numbers.startsWith("//")){
            String[] parts = numbers.substring(2).split("\n");
            result[0] = parts[0];
            result[1] = parts[1];
        } else {
            result[0] = BASIC_REGEX;
            result[1] = numbers;
        }

        return result;
    }
}
