package net.balgre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.balgre.domain.CommonResponse;
import net.balgre.domain.User;
import net.balgre.service.UserJoinService;

@Controller
public class UserJoinController {

    @Autowired
    // 의존 주입 설정
    private UserJoinService userJoinService;

    // regist move controller
    @RequestMapping("/regist")
    public String regist() {
        return "/userJoin/userJoinForm2";
    }

    // regist form post controller
    @RequestMapping(value = "/form_auth", method = RequestMethod.POST)
    @ResponseBody
    // ResponseBody가 없을 경우에는 위 매핑인 /joinGo이 url만을 매핑하는 게 아니라 view단의 join.? 파일을 찾게 된다.
    public String userJoin(User user) {

        User resUser = userJoinService.userJoin1(user);
        // service의 userJoin1 메서드 호출하면서 파라미터 전달
        System.out.println("CTRL : " + resUser);

        return "회원가입이 완료 되었습니다.";
    }


    /*send sms*/
    @RequestMapping(value="phone_check_data", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse phone_check(@RequestParam(value="phone") String phone) throws Exception {

        System.out.println(phone);

        return userJoinService.phone_check(phone);
    }

    /*sms cert*/
    @RequestMapping(value="phone_check_data2", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse phone_check2(@RequestParam(value="phone") String phone, @RequestParam(value="cert") String cert) throws Exception {

        System.out.println(phone);
        System.out.println(cert);

        return userJoinService.phone_check2(phone, cert);
    }

}
