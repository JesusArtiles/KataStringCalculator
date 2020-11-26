import StringCalculator.StringCalculator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


public class StringCalculatorShould {

    private StringCalculator calculator = new StringCalculator();

    @Test
    public void return_0_when_given_String_is_empty() throws Exception {
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void return_number_when_given_only_one_number() throws Exception {
        assertThat(calculator.add("0")).isEqualTo(0);
        assertThat(calculator.add("2")).isEqualTo(2);
        assertThat(calculator.add("10")).isEqualTo(10);
    }

    @Test
    public void return_sum_of_numbers_when_given_2_numbers() throws Exception {
        assertThat((calculator.add("1,1"))).isEqualTo(2);
        assertThat((calculator.add("1,2"))).isEqualTo(3);
        assertThat((calculator.add("2,3"))).isEqualTo(5);
        assertThat((calculator.add("2,3"))).isEqualTo(5);

    }

    @Test
    public void return_sum_of_numbers_when_given_unknown_number_of_numbers() throws Exception {
        assertThat((calculator.add("1,1,1"))).isEqualTo(3);
        assertThat((calculator.add("1,2,5,10"))).isEqualTo(18);
        assertThat((calculator.add("2,30,2,3,4,5"))).isEqualTo(46);

        assertThat((calculator.add("1\n1"))).isEqualTo(2);
        assertThat((calculator.add("3\n1,1"))).isEqualTo(5);
        assertThat((calculator.add("1\n8\n1,3,1"))).isEqualTo(14);
    }

    @Test
    public void return_sum_of_numbers_when_given_string_with_delimiter_change() throws Exception {
        assertThat((calculator.add("//;\n1;2"))).isEqualTo(3);
        assertThat((calculator.add("//@\n1@2@3"))).isEqualTo(6);

        assertThat((calculator.add("//[@@@@@]\n1@@@@2@@@@3"))).isEqualTo(6);

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void throw_exception_when_given_negative_number() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("negatives not allowed");
        calculator.add("-1");
        calculator.add("1,2,5,-10");
        calculator.add("//;\n1;-2");
    }

    @Test
    public void return_correct_sum_of_numbers_ignoring_numbers_grater_than_1000() throws Exception {
        assertThat((calculator.add("1\n8\n1,1001,1"))).isEqualTo(11);
        assertThat((calculator.add("2,3,10000"))).isEqualTo(5);
        assertThat((calculator.add("//;\n10000;2"))).isEqualTo(2);
    }
}
