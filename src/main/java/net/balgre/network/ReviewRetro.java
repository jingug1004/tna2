/**
 * Created by user on 2017-04-27 오후 2:18
 * Prac / net.balgre.network
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


package net.balgre.network;

import net.balgre.domain.CommonResponse;
import net.balgre.domain.MyReviewResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-04-27 오후 2:18
 * Prac / net.balgre.network
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 개발팀 김진국
 * @since   2017/04/10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/27  김진국          최초 생성
 *  </pre>
 */


public interface ReviewRetro {

    // 비로그인 상태의 리뷰 목록
    @GET("/api/review/list/{page}")
    Call<HashMap<String, Object>> listPageGET(
            @Path("page") int page2,
            @Query("product_id") long product_id2,
            @Query("photo") int photo2,
            @Query("sort") int sort2
    );

    // 아래부터 로그인 상태의 리뷰 CRUD
    @POST("/api/v1/review/add")
    Call<CommonResponse> reviewAddPOST(
            @Header("X-Authorization") String token,
            @Header("Content-Type") String contentType,
            @Query("order_id") long order_id,
            @Query("files") List files,
            @Query("content") String content,
            @Query("star") int star,
            @Query("skin_type") int skin_type
            );

    @DELETE("/api/v1/review/delete")
    Call<CommonResponse> reviewDelDELETE(
            @Header("X-Authorization") String token,
            @Header("Content-Type") String contentType,
            @Query("review_id") long review_id
    );

    @POST("/api/v1/review/like")
    Call<CommonResponse> reviewLikePOST(
            @Header("X-Authorization") String token,
            @Header("Content-Type") String contentType,
            @Query("review_id") long review_id
    );

    @GET("/api/v1/review/list/{page}")
    Call<HashMap<String, Object>> reviewListPageGET(
            @Header("X-Authorization") String token,
            @Header("Content-Type") String contentType,
            @Path("page") int page,
            @Query("product_id") long product_id,
            @Query("photo") int photo,
            @Query("sort") int sort
    );

    @POST("/api/v1/review/myreview")
    Call<MyReviewResponse> reviewMyreviewPOST(
            @Header("X-Authorization") String token,
            @Header("Content-Type") String contentType
    );

}
