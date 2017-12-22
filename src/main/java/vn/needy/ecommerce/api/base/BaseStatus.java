package vn.needy.ecommerce.api.base;

public enum BaseStatus {

    OK("ok"),
    ERROR("error");

    private String status;

    BaseStatus(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return status;
    }

    public String getName() {
        return this.getName();
    }
}
