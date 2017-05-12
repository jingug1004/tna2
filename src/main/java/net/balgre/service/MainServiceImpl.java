/**
 * Created by user on 2017-04-11 오전 11:24
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

import net.balgre.domain.MainResponse;
import net.balgre.domain.Product;
import net.balgre.network.MainRetroImpl;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017-04-11 오전 11:24
 * Prac / net.balgre.service
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 김진국
 * @since   2017/04/11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/11  김진국          최초 생성
 *  </pre>
 */

@Service
public class MainServiceImpl implements MainService {

//    @Autowired
//    private Balgre client;

//    BalgreClient client;

//    @Before
//    public void setup() {
//        client = new BalgreClient();
//    }

    @Override
    public MainResponse showMain() throws Exception {

        MainRetroImpl client = new MainRetroImpl();

        MainResponse mainResponse = client.getMain();

        return mainResponse;
    }

    @Override
    public void id(Product product) throws Exception {
        Long productId = 1L;
//        Product response = client.getProductDetail(productId);
    }

}