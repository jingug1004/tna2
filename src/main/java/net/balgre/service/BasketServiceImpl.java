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
 * @version 1.0
 * @see
 * @since   2017/04/10
 *
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/05/01  김진국          최초 생성
 * </pre>
 */

@Service
public class BasketServiceImpl implements BasketService {

    private static final Logger logger = LoggerFactory.getLogger(BasketServiceImpl.class);

    @Override
    public BasketResponse basketListGET(String token) throws Exception {

        BasketRetroImpl basketRetroImpl = new BasketRetroImpl();

        BasketResponse response = basketRetroImpl.basketListGET("Bearer " + token);

        if (response == null) {
            return null;
        }

        if (response.getResultCode().equals("200")) {
//        if (response.getResultCode() == "200") {
            logger.info("lll~~~ ListGET 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");

            return response;

        } else {
            logger.info("lll~~~ ListGET 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return null;
        }

    }


    @Override
    public BasketResponse basketAddPOST(String token, long itemId, int itemCount) throws Exception {

        BasketRetroImpl basketRetroImpl = new BasketRetroImpl();

        BasketResponse response = basketRetroImpl.basketAddPOST("Bearer " + token, itemId, itemCount);

        if (response == null) {
            return null;
        }

        if (response.getResultCode().equals("200")) {
//        if (response.getResultCode() == "200") {
            logger.info("lll~~~ AddPOST 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");

            return response;

        } else {
            logger.info("lll~~~ AddPOST 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return null;
        }

    }

    @Override
    public BasketResponse basketDeleteDELETE(String token, long basket_id) throws Exception {

        BasketRetroImpl basketRetroImpl = new BasketRetroImpl();

        BasketResponse response = basketRetroImpl.basketDeleteDELETE("Bearer " + token, basket_id);

        if (response == null) {

            return null;

        }

        if (response.getResultCode().equals("200")) {
            logger.info("lll~~~ Basket Delete 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");

            return response;

        } else {
            logger.info("lll~~~ Basket Delete 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");

            return null;

        }

    }

    @Override
    public BasketResponse basketUpdatePUT(String token, long basket_id, int item_count) throws Exception {

        BasketRetroImpl basketRetroImpl = new BasketRetroImpl();

        BasketResponse response = basketRetroImpl.basketUpdatePUT("Bearer " + token, basket_id, item_count);

        if (response == null) {

            return null;

        }

        if (response.getResultCode().equals("200")) {
            logger.info("lll~~~ Basket Update 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");

            return response;

        } else {
            logger.info("lll~~~ Basket Update 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");

            return null;

        }

    }

}