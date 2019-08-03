import entities.RequestDto;
import entities.ResponseDto;
import entities.SingleTestSet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.SpellerAssertions;
import service.SpellerService;

import java.util.HashMap;

import static entities.RequestOptions.PARAM_LANG;
import static entities.RequestOptions.PARAM_OPTIONS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;

public class SingleLineTests {

    private SpellerService spellerService;

    @BeforeMethod
    public void setUp() {
        spellerService = new SpellerService();
    }

    //todo я не вижу смясла в таком разеденеии, по языкам. Но это вопрос тестовому дизайну. - fixed
    @Test(dataProviderClass = DataProviders.class, dataProvider = "plain correct text")
    public void correctTextWithNoOptionsShouldNotReturnErrorTest(RequestDto requestDto) {

        ResponseDto[] actualResponse = spellerService.checkText(requestDto.getText(), null)
                .getBody()
                .as(ResponseDto[].class);
        SpellerAssertions
                .verifySingleCorrectLine(actualResponse);
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "plain incorrect text")
    public void singleIncorrectRussianTextShouldReturnError1Test(SingleTestSet testSet) {
        //todo .getWithN0Params().getBody()
        //                .as(ResponseDto[].class) - вот такая запись тоже усложняет тест,
        // написание в том числе, НО это тоже вариант, так что можешь не прятать в метода, так пишут
        assertThat(testSet.getRequestDto().getText(), arrayWithSize(1));
        ResponseDto[] actualResponse = spellerService
                .checkText(testSet.getRequestDto().getText(), null)
                .getBody()
                .as(ResponseDto[].class);
//todo а вот число из эстетических соображений, вот пример. Ну это так out of scope - fixed
	   /* ResponseDto[] response5 = new SpellerService(incorrectText)
			    .getWithN0Params()
			    .getBody()
			    .as(ResponseDto[].class);*/

        SpellerAssertions
                .verifySingleIncorrectLine(actualResponse, testSet.getResponseDto());
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "incorrectTextIgnored")
    public void textWithOptionsShouldNotReturnErrorTest(RequestDto requestDto) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(PARAM_LANG, requestDto.getLanguage());
        //todo НЕ используй в тестах if/for и подобное. Это усложняет читабельность теста. Тест ДОЛЖЕН быть читабельным. - fixed
        //а то мне вот, например,  сильно подумать надо, чтобв разобраться, что тут происходит
        params.put(PARAM_OPTIONS, Utils.getOptionsSum(requestDto.getOptions()));
        ResponseDto[] actualResponse = spellerService
                .checkText(requestDto.getText(), params)
                .getBody()
                .as(ResponseDto[].class);
        SpellerAssertions
                .verifySingleCorrectLine(actualResponse);
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "incorrectTextWithOptions")
    public void textWithOptionsShouldReturnErrorTest(SingleTestSet testSet) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(PARAM_LANG, testSet.getRequestDto().getLanguage().toString());
        params.put(PARAM_OPTIONS, Utils.getOptionsSum(testSet.getRequestDto().getOptions()));
        ResponseDto[] actualResponse = spellerService
                .checkText(testSet.getRequestDto().getText(), params)
                .getBody()
                .as(ResponseDto[].class);
        SpellerAssertions
                .verifySingleIncorrectLine(actualResponse, testSet.getResponseDto());
    }


}


