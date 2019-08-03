import entities.*;
import org.testng.annotations.DataProvider;

import static entities.RequestOptions.Languages;
import static entities.RequestOptions.Options;


public class DataProviders {

    @DataProvider(name = "plain correct text")
    public static Object[] plainCorrectData() {
        RequestDto req1 = RequestDto.builder()
                .text(new String[]{"марсоход"})
                .build();

        RequestDto req2 = RequestDto.builder()
                .text(new String[]{"Ах, если бы сбылась моя мечта"})
                .build();

        RequestDto req3 = RequestDto.builder()
                .text(new String[]{"Куда, куда, куда вы удалились, весны моей златые годы"})
                .build();

        RequestDto req4 = RequestDto.builder()
                .text(new String[]{"astronaut"})
                .build();

        RequestDto req5 = RequestDto.builder()
                .text(new String[]{"Tiger, tiger in the wood"})
                .build();

        RequestDto req6 = RequestDto.builder()
                .text(new String[]{"London is the capital of Great Britain"})
                .build();

        return new Object[]{req1, req2, req3, req4, req5, req6};
    }

    @DataProvider(name = "plain incorrect text")
    public static Object[] plainIncorrectData() {
        RequestDto req1 = RequestDto.builder()
                .text(new String[]{"зиленая книла"})
                .build();

        ResponseDto resp1a = ResponseDto.builder()
                .s(new String[]{"зеленая"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        ResponseDto resp1b = ResponseDto.builder()
                .s(new String[]{"книга"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        SingleTestSet set1 = SingleTestSet.builder()
                .requestDto(req1)
                .responseDto(new ResponseDto[]{resp1a, resp1b})
                .build();

        RequestDto req2 = RequestDto.builder()
                .text(new String[]{"Ридиска"})
                .build();

        ResponseDto resp2 = ResponseDto.builder()
                .s(new String[]{"Редиска"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        SingleTestSet set2 = SingleTestSet.builder()
                .requestDto(req2)
                .responseDto(new ResponseDto[]{resp2})
                .build();

        RequestDto req3 = RequestDto.builder()
                .text(new String[]{"вКЛЮЧЕНИЕ"})
                .build();

        ResponseDto resp3 = ResponseDto.builder()
                .s(new String[]{"включение"})
                .code(ResponseErrors.CAPITALIZATION.getCode())
                .build();

        SingleTestSet set3 = SingleTestSet.builder()
                .requestDto(req3)
                .responseDto(new ResponseDto[]{resp3})
                .build();

        RequestDto req4 = RequestDto.builder()
                .text(new String[]{"mAGIC bocl"})
                .build();

        ResponseDto resp4a = ResponseDto.builder()
                .s(new String[]{"magic"})
                .code(ResponseErrors.CAPITALIZATION.getCode())
                .build();

        ResponseDto resp4b = ResponseDto.builder()
                .s(new String[]{"bowl"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        SingleTestSet set4 = SingleTestSet.builder()
                .requestDto(req4)
                .responseDto(new ResponseDto[]{resp4a, resp4b})
                .build();

        RequestDto req5 = RequestDto.builder()
                .text(new String[]{"leson"})
                .build();

        ResponseDto resp5 = ResponseDto.builder()
                .s(new String[]{"lesson"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        SingleTestSet set5 = SingleTestSet.builder()
                .requestDto(req5)
                .responseDto(new ResponseDto[]{resp5})
                .build();

        RequestDto req6 = RequestDto.builder()
                .text(new String[]{"canada"})
                .build();

        ResponseDto resp6 = ResponseDto.builder()
                .s(new String[]{"Canada"})
                .code(ResponseErrors.CAPITALIZATION.getCode())
                .build();

        SingleTestSet set6 = SingleTestSet.builder()
                .requestDto(req6)
                .responseDto(new ResponseDto[]{resp6})
                .build();

        return new Object[]{set1, set2, set3, set4, set5, set6};
    }

    @DataProvider(name = "incorrectTextIgnored")
    public static Object[] incorrectTextIgnored() {

        RequestDto req1 = RequestDto.builder()
                .text(new String[]{"бАВИТИСЯ"})
                .language(Languages.UK)
                .options(new Options[]{Options.IGNORE_CAPITALIZATION})
                .build();

        RequestDto req2 = RequestDto.builder()
                .text(new String[]{"ст1ол зАКАЗОВ"})
                .language(Languages.RU)
                .options(new Options[]{Options.IGNORE_DIGITS, Options.IGNORE_CAPITALIZATION})
                .build();

        RequestDto req3 = RequestDto.builder()
                .text(new String[]{"www.ниправильный.url ашип234ка не нАЙДЕНА"})
                .language(Languages.RU)
                .options(new Options[]{Options.IGNORE_URLS, Options.IGNORE_DIGITS})
                .build();

        RequestDto req4 = RequestDto.builder()
                .text(new String[]{"вашкий13"})
                .language(Languages.UK)
                .options(new Options[]{Options.IGNORE_DIGITS})
                .build();

        RequestDto req5 = RequestDto.builder()
                .text(new String[]{"bOOK"})
                .language(Languages.EN)
                .options(new Options[]{Options.IGNORE_CAPITALIZATION})
                .build();

        RequestDto req6 = RequestDto.builder()
                .text(new String[]{"or1der lIST"})
                .language(Languages.EN)
                .options(new Options[]{Options.IGNORE_DIGITS, Options.IGNORE_CAPITALIZATION})
                .build();

        RequestDto req7 = RequestDto.builder()
                .text(new String[]{"www.randam.url"})
                .language(Languages.UK)
                .options(new Options[]{Options.IGNORE_URLS})
                .build();

        return new Object[]{req1, req2, req3, req4, req5, req6, req7};
    }


    @DataProvider(name = "incorrectTextWithOptions")
    public static Object[] incorrectTextWithOptions() {
        RequestDto req1 = RequestDto.builder()
                .text(new String[]{"всиобщий ст1ол зАКАЗОВ"})
                .language(Languages.RU)
                .options(new Options[]{Options.IGNORE_DIGITS})
                .build();

        ResponseDto resp1a = ResponseDto.builder()
                .s(new String[]{"всеобщий"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        ResponseDto resp1b = ResponseDto.builder()
                .s(new String[]{"заказов"})
                .code(ResponseErrors.CAPITALIZATION.getCode())
                .build();

        SingleTestSet set1 = SingleTestSet.builder()
                .requestDto(req1)
                .responseDto(new ResponseDto[]{resp1a, resp1b})
                .build();

        RequestDto req2 = RequestDto.builder()
                .text(new String[]{"www.ниправильный.url ашипка нАЙДЕНА"})
                .language(Languages.RU)
                .options(new Options[]{Options.IGNORE_URLS, Options.IGNORE_CAPITALIZATION})
                .build();

        ResponseDto resp2 = ResponseDto.builder()
                .s(new String[]{"ошибка"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        SingleTestSet set2 = SingleTestSet.builder()
                .requestDto(req2)
                .responseDto(new ResponseDto[]{resp2})
                .build();

        RequestDto req3 = RequestDto.builder()
                .text(new String[]{"sORCErer and his aprentise123"})
                .language(Languages.EN)
                .options(new Options[]{Options.IGNORE_DIGITS})
                .build();

        ResponseDto resp3 = ResponseDto.builder()
                .s(new String[]{"sorcerer"})
                .code(ResponseErrors.CAPITALIZATION.getCode())
                .build();

        SingleTestSet set3 = SingleTestSet.builder()
                .requestDto(req3)
                .responseDto(new ResponseDto[]{resp3})
                .build();

        RequestDto req4 = RequestDto.builder()
                .text(new String[]{"order order lIST"})
                .language(Languages.EN)
                .options(new Options[]{Options.IGNORE_CAPITALIZATION, Options.FIND_REPEATED_WORDS})
                .build();

        ResponseDto resp4 = ResponseDto.builder()
                .s(new String[]{"order"})
                .code(ResponseErrors.REPEATED_WORD.getCode())
                .build();

        SingleTestSet set4 = SingleTestSet.builder()
                .requestDto(req4)
                .responseDto(new ResponseDto[]{resp4})
                .build();

        RequestDto req5 = RequestDto.builder()
                .text(new String[]{"solid solid approach www.randam.url"})
                .language(Languages.EN)
                .options(new Options[]{Options.IGNORE_URLS, Options.FIND_REPEATED_WORDS})
                .build();

        ResponseDto resp5 = ResponseDto.builder()
                .s(new String[]{"solid"})
                .code(ResponseErrors.REPEATED_WORD.getCode())
                .build();

        SingleTestSet set5 = SingleTestSet.builder()
                .requestDto(req5)
                .responseDto(new ResponseDto[]{resp5})
                .build();

        return new Object[]{set1, set2, set3, set4, set5};
    }

    @DataProvider(name = "multipleIncorrectRequest")
    public static Object[] multipleIncorrectRequest() {
        RequestDto req1 = RequestDto.builder()
                .text(new String[]{"всиобщий ст1ол зАКАЗОВ", "валнистый512 папугайчик"})
                .language(Languages.RU)
                .options(new Options[]{Options.IGNORE_DIGITS})
                .build();

        ResponseDto resp1a1 = ResponseDto.builder()
                .s(new String[]{"всеобщий"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        ResponseDto resp1a2 = ResponseDto.builder()
                .s(new String[]{"заказов"})
                .code(ResponseErrors.CAPITALIZATION.getCode())
                .build();

        ResponseDto resp1b = ResponseDto.builder()
                .s(new String[]{"попугайчик"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        MultipleTestSet set1 = MultipleTestSet.builder()
                .requestDto(req1)
                .responseDto(new ResponseDto[][]{{resp1a1, resp1a2}, {resp1b}})
                .build();

        RequestDto req2 = RequestDto.builder()
                .text(new String[]{"www.ниправильный.url ашипка нАЙДЕНА", "Моя дядя дядя самых чстных правил"})
                .language(Languages.RU)
                .options(new Options[]{Options.IGNORE_URLS, Options.IGNORE_CAPITALIZATION, Options.FIND_REPEATED_WORDS})
                .build();

        ResponseDto resp2a = ResponseDto.builder()
                .s(new String[]{"ошибка"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        ResponseDto resp2b1 = ResponseDto.builder()
                .s(new String[]{"дядя"})
                .code(ResponseErrors.REPEATED_WORD.getCode())
                .build();

        ResponseDto resp2b2 = ResponseDto.builder()
                .s(new String[]{"честных"})
                .code(ResponseErrors.UNKNOWN_WORD.getCode())
                .build();

        MultipleTestSet set2 = MultipleTestSet.builder()
                .requestDto(req2)
                .responseDto(new ResponseDto[][]{{resp2a}, {resp2b1, resp2b2}})
                .build();

        return new Object[]{set1, set2};
    }

}


