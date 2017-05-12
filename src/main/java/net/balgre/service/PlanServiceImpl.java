/**
 * Created by user on 2017-04-14 오후 8:55
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

import net.balgre.domain.Plan;
import net.balgre.network.PlanRetroImpl;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017-04-14 오후 8:55
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
 *  2017/04/14  김진국          최초 생성
 *  </pre>
 */

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    public Plan showPlan() throws Exception {

        PlanRetroImpl showPlan = new PlanRetroImpl();

        Integer pid = 7;

        Plan response = showPlan.getPlan(pid);

        return response;

    }

}