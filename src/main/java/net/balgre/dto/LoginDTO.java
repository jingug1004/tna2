/**
 * Created by user on 2017-04-11 오후 4:43
 * Prac / net.balgre.dto
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

package net.balgre.dto;

import lombok.Data;

/**
 * Created by user on 2017-04-11 오후 4:43
 * Prac / net.balgre.dto
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 공통서비스 개발팀 김진국
 * @since 2017/04/10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/21  홍길동          최초 생성 
 *  2017/05/27  이몽룡          인증이 필요없는 URL을 패스하는 로직 추가
 *  </pre>
 */

@Data
public class LoginDTO {

    private String username;
    private String password;

//    private boolean useCookie;

}