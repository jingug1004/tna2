package net.balgre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.balgre.domain.Plan;
import net.balgre.service.PlanService;


@Controller
public class PlanController {

    private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;

    /*plan list by minho*/
    @RequestMapping(value = "/plan")
    public String planGET(Model model, @RequestParam(required=false) int page) {

        logger.info("page : " + page);
        
        Plan res = planService.planList2(page);
        logger.info("res : " + res);
        model.addAttribute("Plan", res);

        return "plan/plan";

    }

}