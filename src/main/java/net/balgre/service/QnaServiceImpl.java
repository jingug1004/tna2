package net.balgre.service;

import net.balgre.domain.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.balgre.controller.QnaController;
import net.balgre.domain.CommonResponse;
import net.balgre.domain.Qna;
import net.balgre.domain.QnaListResponse;
import net.balgre.network.QnaRetroImpl;

@Service
public class QnaServiceImpl implements QnaService {

    private static final Logger logger = LoggerFactory.getLogger(QnaController.class);


    @Override
    public CommonResponse qnaInsert1(Qna qna, String token) {
        // TODO Auto-generated method stub
        logger.info("잘 전달 되었니 qna? : " + qna);
        logger.info("잘 전달 되었니 token? : " + token);

        QnaRetroImpl QRI = new QnaRetroImpl();
        // 3. RetroImpl 인스턴스 생성, 이 부분에서 QnaRetroImpl의 생성자가 호출(실행)된다.

        // 4. 생성한 인스턴스의 qnaInsert2 메서드를 호출하면서 파라미터 전달
        // * token = KEY:X-Authorization VALUE:"Bearer" + token
        CommonResponse res = QRI.qnaInsert2("Bearer " + token, qna);

        logger.info("response body 왔니? : " + res);

        if (res == null) {
            return null;
        }


        if (res.getResultCode().equals("200")) {
            System.out.println("성공 : " + res.getMessage());
            System.out.println(res.getTimestamp());

            return res;
        } else {
            System.out.println("실패 : " + res.getMessage());
            return null;
        }
    }

    @Override
    public QnaListResponse qnaListResponse(String token) {
        logger.info("ServiceImpl 드루왔니? : " + token);

        QnaRetroImpl QRI = new QnaRetroImpl();

        QnaListResponse res = QRI.qnaList2("Bearer " + token);

        logger.info("response body 왔니? : " + res);

        if (res == null) {
            return null;
        }

        if (res.getResultCode().equals("200")) {
            System.out.println("성공 : " + res.getMessage());
            System.out.println(res.getTimestamp());

            return res;
        } else {
            System.out.println("실패 : " + res.getMessage());
            return null;
        }
    }

    @Override
    public UserResponse getUserInfo(String token) {
        return null;
    }

    /*
    @Override
    public QnaListResponse qnaDetail(String token) {
        // TODO Auto-generated method stub

        logger.info("ServiceImpl Token : " + token);

        QnaRetroImpl QRI = new QnaRetroImpl();

        QnaListResponse res = QRI.qnaDetail2("Bearer " + token);

        logger.info("response body 왔니? : " + res);

        if (res == null) {
            return null;
        }
        if (res.getResultCode().equals("200")) {
            logger.info("성공 : " + res.getMessage());
            logger.info(res.getTimestamp());

            return res;
        } else {
            logger.info("실패 : " + res.getMessage());
            return null;
        }
    }
    */


}
