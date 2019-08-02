import entities.ResponseErrors;
import org.testng.annotations.DataProvider;

import static entities.RequestOptions.Languages;
import static entities.RequestOptions.Options;


public class DataProviders {

    @DataProvider(name = "plain Russian correct text")
    public static Object[][] plainCorrectRussianData() {
        return new Object[][]{{"марсоход"}, {"учебник"}, {"Зеленая трава у дома"}, {"Ах, если бы сбылась моя мечта"},
                {"Не надо печалиться"}, {"Куда, куда, куда вы удалились, весны моей златые годы"}};
    }

    @DataProvider(name = "plain English correct text")
    public static Object[][] plainCorrectEnglishData() {
        return new Object[][]{{"astronaut"}, {"reference book"}, {"Tiger, tiger in the wood"},
                {"London is the capital of Great Britain"}};
    }


    @DataProvider(name = "plain incorrect text")
    public static Object[][] plainIncorrectData() {
        return new Object[][]{{"книла", "книга"}, {"Ридиска", "Редиска"}, {"вкличилось", "включилось"},
                {"bocl", "bowl"}, {"leson", "lesson"}};
    }

    @DataProvider(name = "correctTextWithOptions")
    public static Object[][] correctTextWithOptions() {
        return new Object[][]{{"кНИГА", Languages.RU, Options.IGNORE_CAPITALIZATION},
                {"ст1ол зАКАЗОВ", Languages.RU, Options.IGNORE_DIGITS, Options.IGNORE_CAPITALIZATION},
                {"www.неправильныйУРЛ.com", Languages.RU, Options.IGNORE_URLS},
                {"www.ниправильный.url ашип234ка не нАЙДЕНА", Languages.RU, Options.IGNORE_URLS, Options.IGNORE_DIGITS,
                        Options.IGNORE_CAPITALIZATION},
                {"bOOK", Languages.EN, Options.IGNORE_CAPITALIZATION},
                {"or1der lIST", Languages.EN, Options.IGNORE_DIGITS, Options.IGNORE_CAPITALIZATION},
                {"www.randam.url", Languages.EN, Options.IGNORE_URLS},
                {"www.randam.url ашип234ка не нАЙДЕНА", Languages.RU, Options.IGNORE_URLS, Options.IGNORE_DIGITS,
                        Options.IGNORE_CAPITALIZATION}
        };
    }

    @DataProvider(name = "incorrectTextIgnored")
    public static Object[][] incorrectTextIgnored() {
        return new Object[][]{
                {"усл123овие", Languages.RU, Options.IGNORE_DIGITS},
                {"C:/MyDocuments/пробовать.docx", Languages.RU, Options.IGNORE_URLS},
                {"www.птица.ру", Languages.RU, Options.IGNORE_URLS},
                {"дРАГОЦЕНный", Languages.RU, Options.IGNORE_CAPITALIZATION},
                {"fab7ric", Languages.EN, Options.IGNORE_DIGITS},
                {"embark.docx", Languages.EN, Options.IGNORE_URLS},
                {"www.superficial.gov", Languages.EN, Options.IGNORE_URLS},
                {"fASCINATE", Languages.EN, Options.IGNORE_CAPITALIZATION}
        };
    }

    @DataProvider(name = "incorrectTextWithOptions")
    public static Object[][] incorrectTextWithOptions() {
        return new Object[][]{
                {"кНИЛА", "книга", ResponseErrors.UNKNOWN_WORD, Languages.RU, Options.IGNORE_CAPITALIZATION},
                {"ст1ол зАКАЗОВ", "заказов", ResponseErrors.CAPITALIZATION, Languages.RU, Options.IGNORE_DIGITS},
                {"www.ниправильный.url ашипка нАЙДЕНА", "ошибка", ResponseErrors.UNKNOWN_WORD, Languages.RU,
                        Options.IGNORE_URLS, Options.IGNORE_CAPITALIZATION},
                {"bOOk", "book", ResponseErrors.CAPITALIZATION, Languages.EN, Options.IGNORE_URLS},
                {"seLFIsh", "selfish", ResponseErrors.CAPITALIZATION, Languages.EN, Options.IGNORE_DIGITS},
                {"order order lIST", "order", ResponseErrors.REPEATED_WORD, Languages.EN,
                        Options.FIND_REPEATED_WORDS, Options.IGNORE_CAPITALIZATION,},
                {"solid solid approach www.randam.url ", "solid", ResponseErrors.REPEATED_WORD, Languages.EN,
                        Options.IGNORE_URLS, Options.FIND_REPEATED_WORDS}
        };
    }
}


