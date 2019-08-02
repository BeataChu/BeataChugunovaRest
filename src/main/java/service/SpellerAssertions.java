package service;

import entities.SpellerResponseDto;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;

public class SpellerAssertions {
    private SpellerResponseDto[] response;

    public SpellerAssertions(SpellerResponseDto[] response) {
        this.response = response;
    }

    public void verifySingleIncorrectLine(String correctLine, int errorCode) {
        assertThat(response, arrayWithSize(greaterThan(0)));
        for (SpellerResponseDto dto : response) {
            assert (Arrays.asList(dto.getS()).contains(correctLine));
            assertThat(dto.getCode(), equalTo(errorCode));
        }
    }

    public void verifySingleCorrectLine() {
        assertThat(response, arrayWithSize(0));
    }
}