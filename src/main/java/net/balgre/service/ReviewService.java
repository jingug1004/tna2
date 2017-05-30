/**
 * Created by user on 2017-04-27 오후 3:00
 * Prac / net.balgre.service
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 김진국
 * @version 1.0
 * @see
 * @since 2017/04/11
 * <p>
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/21  김진국          최초 생성
 *  </pre>
 */


package net.balgre.service;

import net.balgre.domain.CommonResponse;
import net.balgre.domain.MyReviewResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-04-27 오후 3:00
 * Prac / net.balgre.service
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/27  김진국          최초 생성
 *</pre>
 * @since 2017/04/10
 */


public interface ReviewService {

    public HashMap<String, Object> listPageGET(int page, long product_id, int photo, int sort) throws Exception;

    public CommonResponse reviewAddPOST(String token, long order_id, List files, String content, int star, int skin_type) throws Exception;

    public CommonResponse reviewDelDELETE(String token, long review_id) throws Exception;

    public CommonResponse reviewLikePOST(String token, long review_id) throws Exception;

    public HashMap<String, Object> reviewListPageGET(String token, int page, long product_id, int photo, int sort) throws Exception;

    public MyReviewResponse reviewMyreviewPOST(String token) throws Exception;

}





