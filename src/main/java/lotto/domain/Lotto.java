package lotto.domain;

import static lotto.exception.ExceptionMessage.*;

import java.util.*;
import lotto.util.utils;

public class Lotto {
    private static final int REQUIRED_NUMBER_COUNT = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateNoDuplicates(numbers);
        this.numbers = List.copyOf(numbers.stream().sorted().toList());
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_COUNT_LIMIT.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            utils.validateRange(number);
        }
    }

    private void validateNoDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    public int countMatchNumber(Lotto winLotto) {
        return (int) numbers.stream().filter(winLotto.numbers::contains).count();
    }


    public List<Integer> getNumbers() {
        return numbers;
    }
}
