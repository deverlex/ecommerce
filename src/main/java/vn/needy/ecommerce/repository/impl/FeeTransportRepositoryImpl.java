package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.domain.mysql.FeeTransport;
import vn.needy.ecommerce.repository.FeeTransportRepository;

import java.util.ArrayList;
import java.util.List;

@Repository("feeTransportRepository")
public class FeeTransportRepositoryImpl implements FeeTransportRepository {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<FeeTransport> getListByCompanyId(long companyId) {
        List<FeeTransport> feeTransports = new ArrayList<>();

        SqlRowSet rs = jdbc.queryForRowSet("select * " +
                "from fee_transport " +
                "where company_id = ?", companyId);
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
}
