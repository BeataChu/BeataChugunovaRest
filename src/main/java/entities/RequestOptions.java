package entities;

public class RequestOptions {

    public static final String SPELLER_URI = "https://speller.yandex.net/services/spellservice.json/checkText";
    public static final String PARAM_TEXT = "text";
    public static final String PARAM_OPTIONS = "options";
    public static final String PARAM_LANG = "lang";

    public enum Languages {
        RU("ru"),
        UK("uk"),
        EN("en");
        String languageCode;

        Languages(String lang) {
            this.languageCode = lang;
        }

        public String toString(){
            return languageCode;
        }
    }

    public enum Options {
        IGNORE_DIGITS(2),
        IGNORE_URLS(4),
        FIND_REPEATED_WORDS(8),
        IGNORE_CAPITALIZATION(512);
        int optionCode;

        Options(int optionCode) {
            this.optionCode = optionCode;
        }

        public int getCode() {
            return optionCode;
        }
    }
}
