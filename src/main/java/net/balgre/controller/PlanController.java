package net.balgre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.balgre.domain.PagePlan;
import net.balgre.service.PlanService;


@Controller
public class PlanController {

    private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;
    
 
    /*plan detail by minho*/
    @RequestMapping(value = "/plan/detail", method = RequestMethod.GET)
    public String planDetail(Model model, @RequestParam(required=false) String pid) {
    	                                  // 필수값 pid
    	int page = 0;
    	
    	PagePlan pp = planService.planList2(page);
    	if (StringUtils.isEmpty(pid)) {
    		// null check class StringUtils.isEmpty
    		pid = String.valueOf(pp.getContent().get(0).getPid());
    		// parameter가 null이면 (...)을 pid에 대입
    	}
    	model.addAttribute("Plan", pp);
    	model.addAttribute("Plan2", planService.planDetail2(Long.parseLong(pid)));

    	return "/product/planDetail";
    }

}