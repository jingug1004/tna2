package net.balgre.Restful;

import net.balgre.domain.*;
import net.balgre.network.ProductRetroImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;


/**
 * Unit test for simple App.
 */
public class ProductRestTest {

    ProductRetroImpl productRetroImpl;

    @Before
    public void setup() {
        productRetroImpl = new ProductRetroImpl();
    }

    @Test
    public void bestReponseGET() {
        BestResponse response = productRetroImpl.bestResponseGET();

        assertNotNull(response);
        assertNotNull(response.getFirstBanner());
    }

    @Test
    public void boxGET() {
        List<Product> response = productRetroImpl.boxGET();

        assertNotNull(response);
        assertNotNull(response.size());
    }

    @Test
    public void categoryResponseGET() {
        CategoryResponse response = productRetroImpl.categoryResponseGET();

        assertNotNull(response);
        assertNotNull(response.getMessage());
    }

    @Test
    public void categoryResponse2GET() {
        CategoryResponse2 response = productRetroImpl.categoryResponse2GET();

        assertNotNull(response);
        assertNotNull(response.getMessage());
    }

    @Test
    public void detailGET() {

        long productId = 44;

        ProductResponse response = productRetroImpl.detailGET(productId);
        assertNotNull(response);
        assertNotNull(response.getResultCode());
    }

    @Test
    public void listGET() {
        BestResponse response = productRetroImpl.listGET();

        assertNotNull(response);
        assertNotNull(response.getFirstBanner());
    }

    @Test
    public void listPageGET() {

        int page4 = 0;
        long brand_id4 = 14;
        int sort4 = 1;

//        List<Product> response = productRetroImpl.listPageGET(int page4, long brand_id4, int sort4);


        HashMap<String, Object> response = productRetroImpl.listPageGET(page4, brand_id4, sort4);

        assertNotNull(response.get("totalPages"));
//        assertNotNull(response.toString());
    }

    @Test
    public void listParentPageGET() {

        long parent4 = 1;
        int page4 = 1;
        long menu_id4 = 90;
        int sort4 = 1;

//        List<Product> response = productRetroImpl.listPageGET(int page4, long brand_id4, int sort4);


        HashMap<String, Object> response = productRetroImpl.listParentPageGET(parent4, page4, menu_id4, sort4);

        assertNotNull(response.get("totalPages"));
//        assertNotNull(response.toString());
    }

    @Test
    public void searchGET() {

        String search4  = "화장";

        List<Product> response = productRetroImpl.searchGET(search4);

        assertNotNull(response);
        assertNotNull(response.toString());
    }

    @Test
    public void timeGet() {

        List<ProductTimeSale> response = productRetroImpl.timeGET();

        assertNotNull(response);
        assertNotNull(response.toString());

    }


}
