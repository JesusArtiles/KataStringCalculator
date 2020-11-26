package StringCalculator;

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

        String[] regexAndNumbers = getRegexAndNumbers(numbers);
        numbers = regexAndNumbers[1];
        String regex = regexAndNumbers[0];

        String[] parts = numbers.split(regex);
        return getSum(parts);

    }

    private int getSum(String[] parts) throws Exception {
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

    private String[] getRegexAndNumbers(String numbers){
        String[] result = new String[2];

         if(numbers.startsWith("//")){
            String[] parts = numbers.substring(2).split("\n");
            String[] newNumbers = removeRepeatedDelimiters(parts[1],parts[0]);
            result[0] = newNumbers[1];
            result[1] = newNumbers[0];
        } else {
            result[0] = BASIC_REGEX;
            result[1] = numbers;
        }

        return result;
    }

    private String[] removeRepeatedDelimiters(String numbers, String delimiter) {
        String result = new String();
        if(delimiter.startsWith("[") && delimiter.endsWith("]")){
            delimiter = delimiter.substring(1,delimiter.length()-1).substring(0,1);
        }else{
            delimiter = delimiter.substring(0,1);
        }

        String[] parts = numbers.split(delimiter);
        for(int i = 0;i < parts.length;i++){
            if(!parts[i].isEmpty()){
                result += parts[i] + delimiter;
            }
        }
        return new String[]{result.substring(0,result.length()-1),delimiter.substring(0,1)};
    }

}
