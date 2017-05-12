/**
 * Created by user on 2017-04-21 오전 9:32
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
import net.balgre.network.ProductRetroImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017-04-21 오전 9:32
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

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public BestResponse bestResponseGET() throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        BestResponse bestResponse = client.bestResponseGET();

        return bestResponse;

    }

    @Override
    public List<Product> boxGET() throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        List<Product> box = client.boxGET();

        return box;
    }

    @Override
    public CategoryResponse categoryResponseGET() throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        CategoryResponse categoryResponse = client.categoryResponseGET();

        return categoryResponse;

    }

    @Override
    public CategoryResponse2 categoryResponse2GET() throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        CategoryResponse2 categoryResponse2 = client.categoryResponse2GET();

        return categoryResponse2;
    }

    @Override
    public ProductResponse productDetailGET(long product_id1) throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        ProductResponse response = client.detailGET(product_id1);

        return response;
    }

}