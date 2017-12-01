package vn.needy.ecommerce.api.v1.order.request;

import java.io.Serializable;
import java.util.Date;

public class OrderRequest implements Serializable {
    private static final long serialVersionUID = 1453535344L;

    private long productId;
    private Date fromTime;
    private Date toTime;
    private float toLat;
    private float toLng;
    private boolean getTax;
    private String note;
    private Date receiveFrom;
    private Date receiveTo;
    private float fee;

    public OrderRequest() {
        super();
    }

}
