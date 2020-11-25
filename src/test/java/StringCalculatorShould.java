import StringCalculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StringCalculatorShould {

    private StringCalculator calculator = new StringCalculator();

    @Test
    public void return_0_when_given_String_is_empty(){
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void return_number_when_given_only_one_number(){
        assertThat(calculator.add("0")).isEqualTo(0);
        assertThat(calculator.add("2")).isEqualTo(2);
        assertThat(calculator.add("10")).isEqualTo(10);
    }

    @Test
    public void return_sum_of_numbers_when_given_2_numbers(){
        assertThat((calculator.add("1,1"))).isEqualTo(2);
        assertThat((calculator.add("1,2"))).isEqualTo(3);
        assertThat((calculator.add("2,3"))).isEqualTo(5);
    }

    @Test
    public void return_sum_of_numbers_when_given_unknown_number_of_numbers(){
        assertThat((calculator.add("1,1,1"))).isEqualTo(3);
        assertThat((calculator.add("1,2,5,10"))).isEqualTo(18);
        assertThat((calculator.add("2,30,2,3,4,5"))).isEqualTo(46);
    }



}
