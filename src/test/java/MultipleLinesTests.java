import entities.MultipleTestSet;
import entities.ResponseDto;
import org.testng.annotations.Test;
import service.SpellerAssertions;
import service.SpellerService;

import java.util.HashMap;

import static entities.RequestOptions.PARAM_LANG;
import static entities.RequestOptions.PARAM_OPTIONS;

public class MultipleLinesTests {

    private SpellerService spellerService;

    @Test(dataProviderClass = DataProviders.class, dataProvider = "multipleIncorrectRequest")
    public void textsWithOptionsShouldReturnErrorTest(MultipleTestSet testSet) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(PARAM_LANG, testSet.getRequestDto().getLanguage().toString());
        params.put(PARAM_OPTIONS, Utils.getOptionsSum(testSet.getRequestDto().getOptions()));
        ResponseDto[][] actualResponse = spellerService
                .checkTexts(testSet.getRequestDto().getText(), params)
                .getBody()
                .as(ResponseDto[][].class);
        SpellerAssertions
                .verifyMultipleIncorrectLine(actualResponse, testSet.getResponseDto());
    }
}