/**
 * Created by user on 2017-04-27 오후 12:49
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


import net.balgre.controller.QnaController;
import net.balgre.domain.CommonResponse;
import net.balgre.domain.User;
import net.balgre.domain.UserResponse;
import net.balgre.network.UserJoinRetroImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserJoinServiceImpl implements UserJoinService {

    // UserJoinService 참조하는 UserJoinService 구현체

    private static final Logger logger = LoggerFactory.getLogger(QnaController.class);

    @Override
    // 재정의
    public User userJoin1(User user) {
        // 해당 메서드는 service interface의 재정의 메서드이며 파라미터로 user값을 받는다.

        UserJoinRetroImpl userJRI = new UserJoinRetroImpl();
        // retroImpl 인스턴스 생성

        UserResponse res = userJRI.userJoin2(user);
        // 생성한 인스턴스의 userJoint2 메서드를 호출하며 user값을 파라미터로 넘겨준다.
        // 그리고 리턴 받은 값을 UserResponse res 에 대입한다.

        logger.info("response body 오셨습니까? : " + res);

        if(res == null) {
            return null;
        }

        if( res.getResultCode().equals("200")) {
            System.out.println("성공 : " + res.getMessage() );
            System.out.println(res.getUser() );
            return res.getUser();
        } else {
            System.out.println("실패: " + res.getMessage() );
            return null;
        }
    }

    @Override
    public CommonResponse phone_check(String phone) {
        UserJoinRetroImpl userJRI = new UserJoinRetroImpl();
        CommonResponse com = userJRI.phone_check(phone);

        return com;
    }

    @Override
    public CommonResponse phone_check2(String phone, String cert) {
        UserJoinRetroImpl userJRI = new UserJoinRetroImpl();
        CommonResponse com2 = userJRI.phone_check2(phone, cert);

        return com2;
    }




}