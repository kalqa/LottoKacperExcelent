package pl.lotto.numberreceiver;

enum ValidationResult {
    INPUT_ERROR("asdasd"),
    NOT_SIX_NUMBERS_GIVEN("YOU SHOULD GIVE NUMBERS FORM 1 TO 99"),
    NOT_IN_RANGE("asdasdasdasd"),
    INPUT_SUCCESS("asdasdasd");

    String info;

    ValidationResult(String info) {
        this.info = info;
    }

    boolean isError(){
        if(this.equals(NOT_SIX_NUMBERS_GIVEN) || this.equals(NOT_IN_RANGE)){
            return true;
        }
        return false;
    }
}
