package entities;

public enum ResponseErrors {
    UNKNOWN_WORD(1),
    REPEATED_WORD(2),
    CAPITALIZATION(3),
    TOO_MANY_ERRORS(4);
    int errorCode;

    private ResponseErrors(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getCode() {
        return errorCode;
    }
}
