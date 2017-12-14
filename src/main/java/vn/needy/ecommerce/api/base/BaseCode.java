package vn.needy.ecommerce.api.base;

public enum BaseCode {

    OK(200),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    BAD_REQUEST(400),
    CONFLICT(409),
    SERVER_ERROR(500);

    private int code;

    BaseCode(final int code) {
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
