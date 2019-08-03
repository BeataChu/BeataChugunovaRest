package service;

import entities.ResponseDto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;

public class SpellerAssertions {

    public static void verifySingleCorrectLine(ResponseDto[] actualResponse) {
        assertThat(actualResponse, arrayWithSize(0));
    }

    public static void verifySingleIncorrectLine(ResponseDto[] actualResponse, ResponseDto[] expectedResponse) {
        assertThat(actualResponse.length, equalTo(expectedResponse.length));
        for (int i = 0; i < actualResponse.length; i++) {
            assertThat(actualResponse[i].getCode(), equalTo(expectedResponse[i].getCode()));
            String expectedSpelling = expectedResponse[i].getS()[0];
            assertThat(actualResponse[i].getS(), hasItemInArray(expectedSpelling));
        }
    }

    public static void verifyMultipleIncorrectLine(ResponseDto[][] actualResponse, ResponseDto[][] expectedResponse) {
        assertThat(actualResponse.length, equalTo(expectedResponse.length));
        for (int i = 0; i < actualResponse.length; i++) {
            verifySingleIncorrectLine(actualResponse[i], expectedResponse[i]);
        }
    }
}