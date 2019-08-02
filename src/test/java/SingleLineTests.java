import entities.RequestOptions.Languages;
import entities.RequestOptions.Options;
import entities.ResponseErrors;
import entities.SpellerResponseDto;
import org.testng.annotations.Test;
import service.SpellerAssertions;
import service.SpellerService;

import java.util.HashMap;

import static entities.RequestOptions.PARAM_LANG;
import static entities.RequestOptions.PARAM_OPTIONS;

public class SingleLineTests {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "plain Russian correct text")
    public void correctRussianTextWithNoOptionsShouldNotReturnErrorTest(String text) {

        SpellerResponseDto[] response = new SpellerService(text).getWithNoParams().getBody()
                .as(SpellerResponseDto[].class);
        new SpellerAssertions(response)
                .verifySingleCorrectLine();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "plain English correct text")
    public void correctEnglishTextWithNoOptionsShouldNotReturnErrorTest(String text) {

        SpellerResponseDto[] response = new SpellerService(text).getWithNoParams().getBody()
                .as(SpellerResponseDto[].class);
        new SpellerAssertions(response)
                .verifySingleCorrectLine();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "plain incorrect text")
    public void singlePlainRussianTextTest(String incorrectText, String correctText) {
        SpellerResponseDto[] response = new SpellerService(incorrectText).getWithNoParams().getBody()
                .as(SpellerResponseDto[].class);

        new SpellerAssertions(response)
                .verifySingleIncorrectLine(correctText, 1);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "correctTextWithOptions")
    public void textWithOptionsShouldNotReturnErrorTest(String text, Languages language, Options... options) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(PARAM_LANG, language);
        int optionSum = 0;
        for (int i = 0; i < options.length; i++) {
            optionSum += options[i].getCode();
        }
        params.put(PARAM_OPTIONS, optionSum);
        SpellerResponseDto[] response = new SpellerService(text).getWithParams(params).getBody()
                .as(SpellerResponseDto[].class);
        new SpellerAssertions(response)
                .verifySingleCorrectLine();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "incorrectTextIgnored")
    public void incorrectTextShouldBeIngnoredTest(String incorrectText, Languages language, Options... options) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(PARAM_LANG, language.toString());
        int optionSum = 0;
        for (int i = 0; i < options.length; i++) {
            optionSum += options[i].getCode();
        }
        params.put(PARAM_OPTIONS, optionSum);
        SpellerResponseDto[] response = new SpellerService(incorrectText).getWithParams(params).getBody()
                .as(SpellerResponseDto[].class);
        new SpellerAssertions(response)
                .verifySingleCorrectLine();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "incorrectTextWithOptions")
    public void textWithOptionsShouldReturnErrorTest(String incorrectText, String correctText,
                                                     ResponseErrors error, Languages language, Options... options) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(PARAM_LANG, language.toString());
        int optionSum = 0;
        for (int i = 0; i < options.length; i++) {
            optionSum += options[i].getCode();
        }
        params.put(PARAM_OPTIONS, optionSum);
        SpellerResponseDto[] response = new SpellerService(incorrectText).getWithParams(params).getBody()
                .as(SpellerResponseDto[].class);
        new SpellerAssertions(response)
                .verifySingleIncorrectLine(correctText, error.getCode());
    }
}


