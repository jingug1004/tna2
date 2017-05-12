package net.balgre.Restful;

import net.balgre.domain.MainResponse;
import net.balgre.domain.Product;
import net.balgre.network.MainRetroImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


/**
 * Unit test for simple App.
 */
public class MainRestTest {

    MainRetroImpl mainRetroImpl;

    @Before
    public void setup() {
        mainRetroImpl = new MainRetroImpl();
    }

//    @Test
//    public void getProduct() {
//        MainResponse response = mainRetroImpl.getMain();
//        assertNotNull(response);
//        assertNotNull(response.getMessage());
//    }

    @Test
    public void getProduct() {
        Long productId = 1L;
        Product response = mainRetroImpl.getProduct(productId);
        assertNotNull(response);

   }



}
