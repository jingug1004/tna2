/**
 * Created by user on 2017-04-27 오후 2:31
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

import net.balgre.network.ReviewRetroImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 2017-04-27 오후 2:31
 * Prac / net.balgre.Restful
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @since 2017/04/10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/27  김진국          최초 생성
 *  </pre>
 */


public class ReviewRestTest {

    ReviewRetroImpl reviewRetroImpl;

    @Before
    public void setup() {
        reviewRetroImpl = new ReviewRetroImpl();
    }


    @Test
    public void listPageGET() {

        int page4 = 0;
        long product_id4 = 104;
        int photo4 = 0;
        int sort4 = 1;

        HashMap<String, Object> response = reviewRetroImpl.listPageGET(page4, product_id4, photo4, sort4);

        assertNotNull(response.get("totalPages"));
//        assertNotNull(response.toString());
    }


}