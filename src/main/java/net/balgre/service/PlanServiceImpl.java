package net.balgre.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.balgre.domain.Plan;
import net.balgre.network.PlanRetroImpl;

@Service
public class PlanServiceImpl implements PlanService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

	/*plan list by minho*/
	@Override
	public Plan planList2(int page) {
		// TODO Auto-generated method stub
		
		logger.info("[PlanServiceImpl] 컨트롤러에서 받은 page : " + page);
		
		PlanRetroImpl PRI = new PlanRetroImpl();
		
		Plan res = PRI.planList2(page);
		
		logger.info("[PlanServiceImpl] 레트로에서 받은 res : " + res);
		
		if (res == null) {
			
			return null;
		} else {
			
			return res;
		}

   }
}