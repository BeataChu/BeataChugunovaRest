import entities.RequestOptions;

public class Utils {

    public static int getOptionsSum(RequestOptions.Options[] options) {
        int optionsSum = 0;
        for (int i = 0; i < options.length; i++) {
            optionsSum += options[i].getCode();
        }
        return optionsSum;
    }
}
