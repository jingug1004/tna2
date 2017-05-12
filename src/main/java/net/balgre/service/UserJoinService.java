/**
 * Created by user on 2017-04-27 오후 12:48
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
import net.balgre.domain.User;

public interface UserJoinService {


    public User userJoin1(User user);

    public CommonResponse phone_check(String phone);

    public CommonResponse phone_check2(String phone, String cert);


}
