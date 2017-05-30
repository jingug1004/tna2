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

import net.balgre.service.ReviewService;
import net.balgre.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ReviewService service;

    @RequestMapping(value = "/review/list/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listPage(@PathVariable("page") int page,
                                                        @RequestParam("product_id") long product_id,
                                                        @RequestParam("photo") int photo,
                                                        @RequestParam("sort") int sort) throws Exception{

        ResponseEntity<Map<String, Object>> entity = null;

        try {
//            Criteria cri = new Criteria();
//            cri.setPage(page);

            Page pageMaker = new Page();
            pageMaker.setPage(page);
            pageMaker.setCri(page, 10);

            Map<String, Object> map = new HashMap<String, Object>();
//            Map<String, Object> list = service.listPageGET(page, product_id, photo, sort);

            Map<String, Object> list = service.listPageGET(page, product_id, photo, sort);

            map.put("list", list);

//            int replyCount = service.count();
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

//    @RequestMapping(value = "/review/list", method = RequestMethod.GET)
//    public ResponseEntity<Map<String, Object>> listPage(
//            @Path("page") int page,
//            @Query("product_id") long product_id,
//            @Query("photo") int photo,
//            @Query("sort") int sort) {
//
//        logger.info("lll~~~ 1st review enter " + page + " lll~~~");
//
//        ResponseEntity<Map<String, Object>> entity = null;
//
//        try {
//
//            entity = new ResponseEntity<Map<String, Object>>(
//
//                    service.listPageGET(page, product_id, photo, sort), HttpStatus.OK);
//
//            logger.info("lll~~~ 2nd review enter " + page + " lll~~~");
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
//
//        }
//
//        logger.info("lll~~~ 3rd review enter " + page + " lll~~~");
//
//
//        return entity;
////        return "review/review";
//
//    }

//    @RequestMapping(value = "/review/list", method = RequestMethod.GET)
//    public String listGET() throws Exception{
//
//        return "/review/list.vm";
//    }

}