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
                {"or1der lIST", Languages.RU, Options.IGNORE_DIGITS, Options.IGNORE_CAPITALIZATION},
                {"www.randam.url", Languages.EN, Options.IGNORE_URLS},
                {"www.randam.url ашип234ка не нАЙДЕНА", Languages.RU, Options.IGNORE_URLS, Options.IGNORE_DIGITS,
                        Options.IGNORE_CAPITALIZATION}};
    }


    @DataProvider(name = "incorrectTextWithOptions")
    public static Object[][] incorrectTextWithOptions() {
        return new Object[][]{{"кНИЛА", "книга", ResponseErrors.UNKNOWN_WORD, Languages.RU, Options.IGNORE_CAPITALIZATION},
                {"ст1ол зАКАЗОВ", "заказов", ResponseErrors.CAPITALIZATION, Languages.RU, Options.IGNORE_DIGITS},
                {"www.ниправильный.url ашипка нАЙДЕНА", "ошибка", ResponseErrors.UNKNOWN_WORD, Languages.RU, Options.IGNORE_URLS,
                        Options.IGNORE_CAPITALIZATION},
                {"bOOK", "book", ResponseErrors.CAPITALIZATION, Languages.EN},
                {"order order lIST", "order", ResponseErrors.REPEATED_WORD, Languages.RU, Options.IGNORE_CAPITALIZATION, Options.FIND_REPEATED_WORDS},
                {"www.randam.url solid solid approach", "solid", ResponseErrors.REPEATED_WORD, Languages.EN, Options.IGNORE_URLS, Options.FIND_REPEATED_WORDS}};
    }
}

//IGNORE_DIGITS	2	Пропускать слова с цифрами, например, "авп17х4534".
//        IGNORE_URLS	4	Пропускать интернет-адреса, почтовые адреса и имена файлов.
//        FIND_REPEA_WORDS	8	Подсвечивать повторы слов, идущие подряд. Например, "я полетел на на Кипр".
//        IGNORE_CAPITALIZATION	512


