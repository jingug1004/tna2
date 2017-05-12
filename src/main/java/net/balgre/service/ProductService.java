/**
 * Created by user on 2017-04-21 오전 9:30
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

import net.balgre.domain.*;

import java.util.List;

/**
 * Created by user on 2017-04-21 오전 9:30
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
 *  2017/04/21  김진국          최초 생성 
 *  </pre>
 */


public interface ProductService {

    public BestResponse bestResponseGET() throws Exception;

    public List<Product> boxGET() throws Exception;

    public CategoryResponse categoryResponseGET() throws Exception;

    public CategoryResponse2 categoryResponse2GET() throws Exception;

    public ProductResponse productDetailGET(long product_id1) throws Exception;


}
