package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.domain.mysql.FeeTransport;
import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;
import vn.needy.ecommerce.repository.FeeTransportRepository;

import java.util.ArrayList;
import java.util.List;

@Repository("feeTransportRepository")
public class FeeTransportRepositoryImpl implements FeeTransportRepository {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<FeeTransport> getListByProductId(long productId) {
        List<FeeTransport> feeTransports = new ArrayList<>();

        SqlRowSet rs = jdbc.queryForRowSet("select * " +
                "from fee_transport " +
                "where product_id = ?", productId);
        while (rs.next()) {
            FeeTransport ft = new FeeTransport();
            ft.setId(rs.getLong("id"));
            ft.setFeeType(rs.getShort("fee_type"));
            ft.setFrom(rs.getFloat("from"));
            ft.setTo(rs.getFloat("to"));
            ft.setFee(rs.getFloat("fee"));
            ft.setLastUpdatedTime(rs.getDate("last_updated_time"));
            ft.setLastUpdatedBy(rs.getLong("last_updated_by"));
            feeTransports.add(ft);
        }
        return feeTransports;
    }

    @Override
    public void updateFeeTransport(long productId, long userId, List<FeeTransportWrapper> feeTransports) {
        for (FeeTransportWrapper ft : feeTransports) {
            jdbc.update("insert into fee_transport(id, product_id, fee_type, `from`, `to`, fee, last_updated_by) values (?, ?, ?, ?, ?, ?, ?) " +
                    "on duplicate key update fee_type = ?, `from` = ?, `to` = ?, fee = ?, last_updated_by = ?",
                    ft.getId(),
                    productId,
                    ft.getFeeType(),
                    ft.getFrom(),
                    ft.getTo(),
                    ft.getFee(),
                    userId,
                    ft.getFeeType(),
                    ft.getFrom(),
                    ft.getTo(),
                    ft.getFee(),
                    userId);
        }
    }

    @Override
    public void removeFeeTransport(long productId, List<Long> ids) {
        for (long id : ids) {
            jdbc.update("delete from fee_transport where id = ? and product_id = ?",
                    id, productId);
        }
    }
}
