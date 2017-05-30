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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public BestResponse bestResponseGET() throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        BestResponse bestResponse = client.bestResponseGET();

        return bestResponse;

    }

    @Override
    public CategoryResponse categoryResponseGET() throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        CategoryResponse categoryResponse = client.categoryResponseGET();

        return categoryResponse;
    }

    @Override
    public ProductResponse productDetailGET(long product_id1) throws Exception {

        ProductRetroImpl client = new ProductRetroImpl();

        ProductResponse response = client.detailGET(product_id1);

        return response;
    }

    
    /*time sale list by minho*/
	@Override
	public List<ProductTimeSale> timeSaleList2() {
		// TODO Auto-generated method stub
		
		ProductRetroImpl PRI = new ProductRetroImpl();
		
		List<ProductTimeSale> res = PRI.timeSaleList();
		
		logger.info("[ProductServiceImpl] 레트로에서 받은 res : " + res);
		
		return res;
	}

	
	/*balgeure box by minho*/
	@Override
	public List<Product> balgeureBox2() {
		// TODO Auto-generated method stub
		
		ProductRetroImpl PRI = new ProductRetroImpl();
		
		List<Product> res = PRI.balgeureBox();
		
		logger.info("[ProductServiceImpl] 레트로에서 받은 res : " + res);
		
		return res;
	}

	
	/*new product by minho*/
	@Override
	public BestResponse newProduct2() {
		// TODO Auto-generated method stub
		
		ProductRetroImpl PRI = new ProductRetroImpl();
		
		BestResponse res = PRI.newProduct();
		
		logger.info("[ProductServiceImpl] 레트로에서 받은 res : " + res);
		
		return res;
	}

	
	/*category by minho*/
	@Override
	public CategoryResponse categoryList2() {
		// TODO Auto-generated method stub
		
		ProductRetroImpl PRI = new ProductRetroImpl();
		
		CategoryResponse res = PRI.categoryList();
		
		logger.info("[ProductServiceImpl] 레트로에서 받은 res : " + res);
		
		return res;
	}

	
	/*sub category by minho*/
	@Override
	public CategoryResponse subCategory2(long menu_id) {
		// TODO Auto-generated method stub
		
		ProductRetroImpl PRI = new ProductRetroImpl();
		
		CategoryResponse res = PRI.subCategory(menu_id);
		
		logger.info("[ProductServiceImpl] 레트로에서 받은 res : " + res);
		
		return res;
	}

	
	/*category list by minho*/
	@Override
	public PageProduct categoryList2(long parent, int page, long menu_id, int sort) {
		// TODO Auto-generated method stub
		
		ProductRetroImpl PRI = new ProductRetroImpl();
		
		PageProduct res = PRI.categoryList2(parent, page, menu_id, sort);
		
		logger.info("[ProductServiceImpl] 레트로에서 받은 res : " + res);
		
		return res;
	}
	
}