package StringCalculator;

public class StringCalculator {

    public int add(String numbers){
        if(numbers.length() == 0) return 0;

        String[] parts = numbers.split(",");
        if(parts.length == 1) {
            return Integer.parseInt(numbers);
        } else if (parts.length > 1) {
            return getSum(parts);
        }

        return 0;
    }

    private int getSum(String[] parts) {
        int sumResult = 0;

        for(String s: parts) {
            sumResult += Integer.parseInt(s);
        }
        return sumResult;
    }
}
