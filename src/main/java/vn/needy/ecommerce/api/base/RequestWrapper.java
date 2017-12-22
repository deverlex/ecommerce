package vn.needy.ecommerce.api.base;

import vn.needy.ecommerce.model.BaseModel;

public final class RequestWrapper<T> extends BaseModel {

    private T data;

    public T getData() {
        return data;
    }

    public RequestWrapper<T> setData(T data) {
        this.data = data;
        return this;
    }
}
