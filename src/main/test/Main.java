import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by nibnait on 2020-01-08
 */
public class Main extends TestCase {

    @Test
    public void testCase() {
        GeoLocation source = new GeoLocation();
        source.setLatitude(31.36413108);
        source.setLongitude(120.91521);

        GeoLocation target = new GeoLocation();
        target.setLatitude(31.381805);
        target.setLongitude(120.927071);

        System.out.println(getDistanceInMeter(source, target).intValue());
    }

    private final double EARTH_RADIUS = 6378.137;//地球半径
    private static final double WALK_DISTANCE_FACTOR = 1.4;

    public Double getDistanceInMeter(GeoLocation source, GeoLocation target) {
        return WALK_DISTANCE_FACTOR * getDistanceInMeter(source.getLatitude(), source.getLongitude(), target.getLatitude(), target.getLongitude());
    }

    public class GeoLocation {
        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    private Double getDistanceInMeter(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS * 1000;
        return s;
    }

    private Double rad(double d) {
        return d * Math.PI / 180.0;
    }

}