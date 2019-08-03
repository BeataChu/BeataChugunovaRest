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

    //todo я не вижу смясла в таком разеденеии, по языкам. Но это вопрос тестовому дизайну.
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
        //todo .getWithNoParams().getBody()
        //                .as(SpellerResponseDto[].class) - вот такая запись тоже усложняет тест,
        // написание в том числе, НО это тоже вариант, так что можешь не прятать в метода, так пишут
        SpellerResponseDto[] response = new SpellerService(incorrectText).getWithNoParams().getBody()
                .as(SpellerResponseDto[].class);
//todo а вот число из эстетических соображений, вот пример. Ну это так out of scope
	   /* SpellerResponseDto[] response5 = new SpellerService(incorrectText)
			    .getWithNoParams()
			    .getBody()
			    .as(SpellerResponseDto[].class);*/


        new SpellerAssertions(response)
                .verifySingleIncorrectLine(correctText, 1);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "correctTextWithOptions")
    public void textWithOptionsShouldNotReturnErrorTest(String text, Languages language, Options... options) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(PARAM_LANG, language);
        int optionSum = 0;
        //todo НЕ используй в тестах if/for и подобное. Это усложняет читабельность теста. Тест ДОЛЖЕН быть читабельным.
        //а то мне вот, например,  сильно подумать надо, чтобв разобраться, что тут происходит
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


