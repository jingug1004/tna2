/**
 * Created by user on 2017-04-17 오후 12:42
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

import net.balgre.domain.User;
import net.balgre.dto.LoginDTO;
import net.balgre.dto.LoginDTO02;
import net.balgre.network.LoginRetroImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by user on 2017-04-17 오후 12:42
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
public class LoginServiceImpl implements LoginService {


    @Override
    public LoginDTO02 login(LoginDTO adto) throws Exception {
        LoginRetroImpl loginRetroImpl = new LoginRetroImpl();


        LoginDTO02 loginDTO02 = loginRetroImpl.getUserDetail(adto);

        return loginDTO02;
    }

    @Override
    public void keepLogin(String uid, String sessionId, Date next) throws Exception {

    }

    @Override
    public User checkLoginBefore(String value) {
        return null;
    }
}