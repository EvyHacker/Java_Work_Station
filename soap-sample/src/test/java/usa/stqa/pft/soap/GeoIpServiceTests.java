package usa.stqa.pft.soap;


import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GeoIpServiceTests {

    @Test
    public void testMyLocation() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("173.66.108.162");
        assertTrue(ipLocation.contains("<Country>US</Country>"));

    }
}
