package net.balgre.Restful;

import net.balgre.dto.LoginDTO;
import net.balgre.dto.LoginDTO02;
import net.balgre.network.LoginRetroImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


/**
 * Unit test for simple App.
 */
public class LoginRestTest {

    LoginRetroImpl loginRetroImpl;

    @Before
    public void setup() {
        loginRetroImpl = new LoginRetroImpl();
    }

//    @Test
//    public void getUserResponse() {
//
//        UserResponse response = loginRetroImpl.getUserResponse();
//
//        assertNotNull(response);
//        assertNotNull(response.getResultCode());
////        assertNull(response);
//
//
//
//    }

    @Test
    public void getUserLogin() {

        LoginDTO userId = new LoginDTO();
        userId.setUsername("12345");
        userId.setPassword("N");

//        LoginDTO02 loginDTO02 = new LoginDTO02();

//        User response = loginRetroImpl.getUserDetail(userId);

        LoginDTO02 loginDTO02 = loginRetroImpl.getUserDetail(userId);

        assertNotNull(loginDTO02);
    }

//    @Test
//    public void getProduct() {
//        Long productId = 1L;
//        Product response = mainRetroImpl.getProduct(productId);
//        assertNotNull(response);
//
//    }

}
