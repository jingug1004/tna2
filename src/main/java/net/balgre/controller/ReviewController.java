/**
 * Created by user on 2017-04-27 오후 1:03
 * Prac / net.balgre.controller
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

package net.balgre.controller;

import net.balgre.domain.CommonResponse;
import net.balgre.dto.LoginDTO02;
import net.balgre.service.ReviewService;
import net.balgre.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017-04-27 오후 1:03
 * Prac / net.balgre.controller
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see
 * @since 2017/04/10
 * <p>
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/27  김진국          최초 생성
 *  </pre>
 */


@RestController
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/review/list/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listPage(@PathVariable("page") int page,
                                                        @RequestParam("product_id") long product_id,
                                                        @RequestParam("photo") int photo,
                                                        @RequestParam("sort") int sort) throws Exception {

        ResponseEntity<Map<String, Object>> entity = null;

        try {
//            Criteria cri = new Criteria();
//            cri.setPage(page);

            Page pageMaker = new Page();
            pageMaker.setPage(page);
            pageMaker.setCri(page, 10);

            Map<String, Object> map = new HashMap<String, Object>();
//            Map<String, Object> list = reviewService.listPageGET(page, product_id, photo, sort);

            Map<String, Object> list = reviewService.listPageGET(page, product_id, photo, sort);

            map.put("list", list);

//            int replyCount = reviewService.count();
//            pageMaker.setTotalCount(replyCount);

            map.put("pageMaker", pageMaker);

            entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

            logger.info("lll~~~ " + list.toString() + " lll~~~");

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
        }

        return entity;

    }

    @RequestMapping(value = "/reviewAddPOST", method = RequestMethod.POST)
    @ResponseBody
    public String reviewAddPOST(HttpSession session,
                                HttpServletRequest request,
                                Model model,
                                CommonResponse commonResponse) throws Exception {

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");

        Map<String, String[]> param = request.getParameterMap();
        String[] addPostVm = request.getParameterValues("reviewAdd[]");

        return "reviewAddPOSTsucc";
    }

//    @RequestMapping(value = "reviewDelDELETE", method = RequestMethod.POST)
//    @ResponseBody
//    public String reviewDelDELETE(HttpSession session,
//                                  HttpServletRequest request,
//                                  Model model,
//                                  CommonResponse commonResponse,
//                                  @RequestParam("review_id") long review_id) throws Exception {
//
//        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");
//
//        CommonResponse response =reviewService.reviewDelDELETE(login.getToken(), review_id);
//
//        return "reviewDelDELETEsucc";
//
//    }
//
//    @RequestMapping(value = "reviewLikePOST", method = RequestMethod.POST)
//    @ResponseBody
//    public String reviewLikePOST(HttpSession session,
//                                 HttpServletRequest request,
//                                 Model model,
//                                 CommonResponse commonResponse,
//                                 @RequestParam("review_id") long review_id) throws Exception {
//
//        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");
//
//        CommonResponse response = reviewService.reviewLikePOST(login.getToken(), review_id);
//
//
//        return "reviewLikePOSTsucc";
//
//    }
//
//    @RequestMapping(value = "/review/list/{page}", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> reviewListPageGET(HttpSession session,
//                                                 HttpServletRequest request,
//                                                 Model model,
//                                                 CommonResponse commonResponse,
//                                                 @PathVariable("page") int page,
//                                                 @RequestParam("product_id") long product_id,
//                                                 @RequestParam("photo") int photo,
//                                                 @RequestParam("sort") int sort) throws Exception {
//
//        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");
//
//        try {
//            Map<String, Object> map = new HashMap<>();
//
//            Map<String, Object> list = reviewService.reviewListPageGET(login.getToken(), page, product_id, photo, sort);
//
//            map.put("reviewList", list);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        return "reviewListPageGETsucc";
//        return null;
//
//    }
//
//    @RequestMapping(value = "reviewMyreviewPOST", method = RequestMethod.POST)
//    @ResponseBody
//    public String reviewMyreviewPOST(HttpSession session,
//                                     HttpServletRequest request,
//                                     Model model,
//                                     CommonResponse commonResponse) throws Exception {
//
//        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");
//
//        try {
//            Map<String, String[]> param = request.getParameterMap();
//            String[] myreviewPostVm = request.getParameterValues("myReview[]");
//
//            reviewService.reviewMyreviewPOST(login.getToken());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "reviewMyreviewPOSTsucc";
//
//    }

}