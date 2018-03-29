package vn.needy.ecommerce.common.utils;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;
    
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT = "hh:mm:ss";
    
    public Date now() {
        return new Date();
    }
    
    public String formatDate(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}
    
    public Date parseTime(String time) {
    	SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
    	try {
			return sdf.parse(time);
		} catch (ParseException e) {
			return new Date();
		}
    }
}
