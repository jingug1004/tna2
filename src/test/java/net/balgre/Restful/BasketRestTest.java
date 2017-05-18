/**
 * Created by user on 2017-04-30 오후 6:18
 * Prac / net.balgre.Restful
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 김진국
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/21  김진국          최초 생성
 *  </pre>
 * @since 2017/04/11
 */

package net.balgre.Restful;

import net.balgre.domain.BasketResponse;
import net.balgre.network.BasketRetroImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 2017-04-30 오후 6:18
 * Prac / net.balgre.Restful
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/30  김진국          최초 생성
 *  </pre>
 * @since 2017/04/10
 */


public class BasketRestTest {

    BasketRetroImpl basketRetroImpl;

    @Before
    public void setup() {
        basketRetroImpl = new BasketRetroImpl();
    }

    @Test
    public void basketGET() {

//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMTAxMTExMTExMSIsInNjb3BlcyI6WyJST0xFX01FTUJFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0IiwiaWF0IjoxNDkzNTQ0MTQ0LCJleHAiOjE0OTM2MzA1NDR9.li52vR0dyWNYE7iI6pTl0D7yddkaHtrX-a8PtYBQYxYC5j9Lquc7ppq69yhK-eVvOqjSPZmlgHD29A3VZY4WYQ";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMTAxMTExMTExMSIsInNjb3BlcyI6WyJST0xFX1JFRlJFU0hfVE9LRU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdCIsImp0aSI6IjkwMGVhYWU5LTA0YjctNDc5ZC1hNDdmLWY5OTNlN2U5YjRkZiIsImlhdCI6MTQ5MzU0NDE0NCwiZXhwIjoxNDkzNjMwNTQ0fQ.SlRPjx0NsyiEvDYXJxd5YzCK2EPGDJ4O3MrlAt2EHg1uA01xusPzq3hFyPGrD7LaFR35KadEL2S2RU3F-mnWYA";

        BasketResponse response = basketRetroImpl.basketListGET(token);

        assertNotNull(response);
        assertNotNull(response.toString());
    }

    @Test
    public void basketPOST() {

        long item_id = 1;
        int item_count = 1;

//        BasketResponse response = basketRetroImpl.basket(item_id, item_count);

//        assertNotNull(response);
//        assertNotNull(response.toString());
    }


}