import StringCalculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StringCalculatorShould {

    private StringCalculator calculator;

    @Before
    public void setup(){
        calculator = new StringCalculator();
    }

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



}
