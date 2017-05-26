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

import java.util.List;

import net.balgre.domain.BestResponse;
import net.balgre.domain.CategoryResponse;
import net.balgre.domain.PageProduct;
import net.balgre.domain.Product;
import net.balgre.domain.ProductResponse;
import net.balgre.domain.ProductTimeSale;

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

    public CategoryResponse categoryResponseGET() throws Exception;

    public ProductResponse productDetailGET(long product_id1) throws Exception;
    
    
    /*time sale list by minho*/
    public List<ProductTimeSale> timeSaleList2();
    
    /*balgeure box by minho*/
    public List<Product> balgeureBox2();
    
    /*new product by minho*/
    public BestResponse newProduct2();
    
    /*category by minho*/
    public CategoryResponse category2();
    
    /*sub category by minho*/
    public CategoryResponse subCategory2(long menu_id);
    
    /*category list by minho*/
    public PageProduct categoryList2(long parent, int page, long menu_id, int sort);

}
