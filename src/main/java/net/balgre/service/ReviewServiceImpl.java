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

import net.balgre.domain.CommonResponse;
import net.balgre.domain.MyReviewResponse;
import net.balgre.network.ReviewRetroImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-04-27 오후 3:27
 * Prac / net.balgre.service
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see
 * @since 2017/04/10
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

    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Override
    public HashMap<String, Object> listPageGET(int page, long product_id, int photo, int sort) throws Exception {

        ReviewRetroImpl client = new ReviewRetroImpl();

        HashMap<String, Object> response = client.listPageGET(page, product_id, photo, sort);

        return response;

    }

    @Override
    public CommonResponse reviewAddPOST(String token, long order_id, List files, String content, int star, int skin_type) throws Exception {

        ReviewRetroImpl reviewRetroImpl = new ReviewRetroImpl();

        CommonResponse response = reviewRetroImpl.reviewAddPOST("Bearer " + token, order_id, files, content, star, skin_type);

        if (response == null) {
            return null;
        }

        if (response.getResultCode().equals("200")) {
            logger.info("lll~~~ reviewAddPOST 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return response;
        } else {
            logger.info("lll~~~ reviewAddPOST 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return null;
        }
    }

    @Override
    public CommonResponse reviewDelDELETE(String token, long review_id) throws Exception {

        ReviewRetroImpl reviewRetroImpl = new ReviewRetroImpl();

        CommonResponse response = reviewRetroImpl.reviewDelDELETE("Bearer " + token, review_id);

        if (response == null) {
            return null;
        }

        if (response.getResultCode().equals("200")) {
            logger.info("lll~~~ reviewDelDELETE 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return response;
        } else {
            logger.info("lll~~~ reviewDelDELETE 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return null;
        }
    }

    @Override
    public CommonResponse reviewLikePOST(String token, long review_id) throws Exception {

        ReviewRetroImpl reviewRetroImpl = new ReviewRetroImpl();

        CommonResponse response = reviewRetroImpl.reviewLikePOST("Bearer " + token, review_id);

        if (response == null) {
            return null;
        }

        if (response.getResultCode().equals("200")) {
            logger.info("lll~~~ reviewLikePOST 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return response;
        } else {
            logger.info("lll~~~ reviewLikePOST 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return null;
        }
    }

    @Override
    public HashMap<String, Object> reviewListPageGET(String token, int page, long product_id, int photo, int sort) throws Exception {

        ReviewRetroImpl reviewRetroImpl = new ReviewRetroImpl();

        HashMap<String, Object> response = reviewRetroImpl.reviewListPageGET("Bearer " + token, page, product_id, photo, sort);

        return response;

//        if (response.getResultCode().equals("200")) {
//            logger.info("lll~~~ reviewListPageGET 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
//            return response;
//        } else {
//            logger.info("lll~~~ reviewListPageGET 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
//            return null;
//        }
    }

    @Override
    public MyReviewResponse reviewMyreviewPOST(String token) throws Exception {
        ReviewRetroImpl reviewRetroImpl = new ReviewRetroImpl();

        MyReviewResponse response = reviewRetroImpl.reviewMyreviewPOST("Bearer " + token);

        if (response == null) {
            return null;
        }

        if (response.getResultCode().equals("200")) {
            logger.info("lll~~~ reviewLikePOST 성공: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return response;
        } else {
            logger.info("lll~~~ reviewLikePOST 실패: " + response.getMessage() + " and " + response.getResultCode() + " lll~~~");
            return null;
        }
    }
}