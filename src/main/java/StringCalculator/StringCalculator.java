package StringCalculator;

public class StringCalculator {

    public int add(String numbers){
        if(numbers.length() == 0) return 0;

        String[] parts = numbers.split(",");
        if(parts.length == 1) {
            return Integer.parseInt(numbers);
        } else if (parts.length == 2) {
            return Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]);
        }

        return 0;
    }
}
