package lotto.input;

import static lotto.input.PurchaseAmountProcessor.validate;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


public class PurchaseAmountProcessorTest {

    @DisplayName("빈 문자열 또는 공백인 경우 - IllegalArgumentException 반환")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void testEmptyOrBlankInput(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validate(input));
    }

    @DisplayName("문자열인 경우 - IllegalArgumentException 반환")
    @ParameterizedTest
    @ValueSource(strings = {"천 원", "문자열", "돈입니다"})
    void testStringInput(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validate(input));
    }

    @DisplayName("1,000원 단위로 나누어 떨어지지 않는 경우 - IllegalArgumentException 반환")
    @ParameterizedTest
    @ValueSource(strings = {"100", "10001", "1111", "1010"})
    void testInvalidUnitInput(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validate(input));
    }

    @DisplayName("유효한 입력 - 발행할 로또 수량 반환")
    @ParameterizedTest
    @CsvSource(value = {"1000, 1", "10000, 10", "12000, 12"})
    void testValidInput(String input, int expected){
        int result = validate(input);
        assertEquals(expected, result);
    }


}
