package net.balgre.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.balgre.controller.QnaController;
import net.balgre.domain.CommonResponse;
import net.balgre.domain.Qna;
import net.balgre.domain.QnaListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class QnaRetroImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	private static final String API_URL = "http://digiserver.cafe24.com:10000";
	
	// 5. QnaRetro 인터페이스 객체화
	private QnaRetro qnaRetro = null;
	
	// 3. Constructor 생성자 호출됨.(serviceImpl)
	public QnaRetroImpl() {
		this.qnaRetro = this.create();
	}
	
	// serviceImpl Call
	public CommonResponse qnaInsert2(String token, Qna qna) {
		
		logger.info("RetroImpl 잘 전달 되었니 ? token : " + token);
		logger.info("왔어? subject : " + qna.getSubject());
		logger.info("왔어? qnaClass1, 2 : " + qna.getQnaClass1() + ", " + qna.getQnaClass2());
		logger.info("왔어? content : " + qna.getContent());
		logger.info("왔어? files : " + qna.getQnaPhoto());
		logger.info("왔어? orderId : " + qna.getOrderId());
		qna.setQnaClass2("2");
		
		// 6. QnaRetro 객체의 qnaInsert 메서드 호출하면서 파라미터값 전달
		Call<CommonResponse> call = this.qnaRetro.qnaInsert(qna.getSubject(), qna.getQnaClass1(),
				qna.getQnaClass2(), qna.getContent(), null, qna.getOrderId(), token, 
				"application/x-www-form-urlencoded");
		
		logger.info("response 받았어? : " + token);
		logger.info("response 받았어? : " + qna.getSubject());
		logger.info("response 받았어? : " + qna.getQnaPhoto());
		logger.info("response 받았어? : " + qna.getOrderId());
		
		try {
			Response<CommonResponse> response = call.execute();
			logger.info("try response " + response);
			if (response.isSuccessful()) {
				System.out.println(response.body());
				return response.body();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*qna list*/
	public QnaListResponse qnaList2(String token) {
		logger.info("retroImpl Token : " + token);
		
		Call<QnaListResponse> call = this.qnaRetro.qnaList(token, 
				"application/x-www-form-urlencoded");
		
		logger.info("response 받았어? : " + token);
		
		try {
			Response<QnaListResponse> response = call.execute();
			logger.info("try response " + response);
			if (response.isSuccessful()) {
				System.out.println(response.body());
				return response.body();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*qna delete*/
	public QnaListResponse qnaDelete2(String token, int id) {
		logger.info("qnaDelete retroImpl Token : " + token);
		logger.info("parameter qnaId : " + id);
		
		Call<QnaListResponse> call = this.qnaRetro.qnaDelete(token, 
				"application/x-www-form-urlencoded", id);
		
		logger.info("qnaId : " + id);
		logger.info("token response 받았어? : " + token);
		
		try {
			Response<QnaListResponse> response = call.execute();
			logger.info("try response : " + response);
			if (response.isSuccessful()) {
				logger.info("response.body() : " + response.body());
				return response.body();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*qna detail*/
	/*public Qna qnaDetail2(String token) {
		logger.info("retroImpl Token : " + token);
		
		Call<Qna> call = this.qnaRetro.qnaDetail(token, 
				"application/x-www.form-urlencoded");
		
		logger.info("parameter 받았어? : " + token);
		
		try {
			Response<Qna> response = call.execute();
			logger.info("try response : " + response.body());
			if (response.isSuccessful()) {
				System.out.println(response.body());
				return response.body();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
    protected QnaRetro create() {
    	
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(QnaRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

}
