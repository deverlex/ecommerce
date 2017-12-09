package vn.needy.ecommerce.api.base;

public enum  ResponseCode {

    OK(200),
    ERROR(400),
    NO_CONTENT(204);


    private int code;

    private ResponseCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }

    public String getName() {
        return this.getName();
    }
}
