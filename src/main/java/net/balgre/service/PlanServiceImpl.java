package net.balgre.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.balgre.domain.PagePlan;
import net.balgre.domain.Plan;
import net.balgre.network.PlanRetroImpl;

@Service
public class PlanServiceImpl implements PlanService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

	/*plan list by minho*/
	@Override
	public PagePlan planList2(int page) {
		// TODO Auto-generated method stub
		
		logger.info("[PlanServiceImpl] 컨트롤러에서 받은 page : " + page);
		
		PlanRetroImpl PRI = new PlanRetroImpl();
		
		PagePlan res = PRI.planList2(page);
		
		return res;

   }

	@Override
	public Plan planDetail2(long pid) {
		// TODO Auto-generated method stub
		
		logger.info("[PlanServiceImpl] 컨트롤러에서 받은 pid : " + pid);
		
		PlanRetroImpl PRI = new PlanRetroImpl();
		
		Plan res = PRI.planDetail2(pid);
		
		logger.info("[PlanServiceImpl] 레트로에서 받은 res : " + res);
		
		return res;
	}
}