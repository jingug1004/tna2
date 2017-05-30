package net.balgre.controller;

import net.balgre.domain.CouponUser;
import net.balgre.domain.CouponUserResponse;
import net.balgre.dto.LoginDTO02;
import net.balgre.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CouponController {

    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;


    /*coupon list*/
    @RequestMapping(value = "/my/coupon")
    public String myCouponList(Model model, HttpSession session, String token) throws Exception {

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");

        CouponUserResponse res = couponService.myCouponList2(login.getToken());

        List<CouponUser> couponUser = res.getCouponUserList();
        logger.info("[Controller] 서비스에서 받은 CouponUserResponse 값 : " + couponUser);

        model.addAttribute("CouponUser", couponUser);

        return "myPage/myCoupon/myCouponList";
    }


    /*coupon insert*/
    @RequestMapping(value = "/my/couponInsert", method = RequestMethod.POST)
    public String myCouponInsert(Model model, HttpSession session, String token, String c_id) throws Exception {

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");

        CouponUserResponse res = couponService.myCouponInsert2(login.getToken(), c_id);

        logger.info("[Controller] 서비스에서 받은 CouponResponse 값 : " + res);

        return "myPage/myCoupon/myCouponList";

    }

}
