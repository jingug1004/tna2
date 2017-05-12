/**
 * Created by user on 2017-04-27 오후 3:27
 * Prac / net.balgre.service
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

package net.balgre.service;

import net.balgre.network.ReviewRetroImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by user on 2017-04-27 오후 3:27
 * Prac / net.balgre.service
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see
 * @since   2017/04/10
 * <p>
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/27  김진국          최초 생성
 *  </pre>
 */

@Service
public class ReviewServiceImpl implements ReviewService {


    @Override
    public HashMap<String, Object> listPageGET(int page, long product_id, int photo, int sort) throws Exception {

        ReviewRetroImpl client = new ReviewRetroImpl();

        HashMap<String, Object> response = client.listPageGET(page, product_id, photo, sort);

        return response;

    }

    @Override
    public int count(int likeCount) throws Exception {
        return 0;
    }

}