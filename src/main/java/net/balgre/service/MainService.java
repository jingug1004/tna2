/**
 * Created by user on 2017-04-11 오전 10:13
 * Prac / net.balgre.service
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author ex) 공통서비스 개발팀 김진국
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/21  홍길동          최초 생성
 *  2017/05/27  이몽룡          인증이 필요없는 URL을 패스하는 로직 추가
 *  </pre>
 * @since ex) 2017/04/10
 */


package net.balgre.service;

import net.balgre.domain.MainResponse;
import net.balgre.domain.Product;

/**
 * Created by user on 2017-04-11 오전 10:13
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

public interface MainService {

    public MainResponse showMain() throws Exception;

//    public String login(UserService user) throws Exception;

    public void id(Product product) throws Exception;

}
