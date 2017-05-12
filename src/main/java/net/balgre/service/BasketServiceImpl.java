/**
 * Created by user on 2017-05-01 오후 8:38
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

import net.balgre.domain.BasketResponse;
import net.balgre.network.BasketRetroImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017-05-01 오후 8:38
 * Prac / net.balgre.service
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 개발팀 김진국
 * @since   2017/04/10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/05/01  김진국          최초 생성
 *  </pre>
 */

@Service
public class BasketServiceImpl implements BasketService {

    private static final Logger logger = LoggerFactory.getLogger(BasketServiceImpl.class);


    @Override
    public BasketResponse basketPOST(String token, int itemCount, long itemId) throws Exception {

        BasketRetroImpl basketRetroImpl = new BasketRetroImpl();

        BasketResponse response = basketRetroImpl.basketPOST("Bearer " + token, itemCount, itemId);

        if (response == null) {
            return null;
        }

        if (response.getResultCode().equals("200")) {
            logger.info("lll~~~ 성공: " + response.getMessage() + "and " + response.getTimestamp() + " lll~~~");

            return response;
        } else {
            logger.info("lll~~~ 실패: " + response.getMessage());
            return null;
        }

    }

}