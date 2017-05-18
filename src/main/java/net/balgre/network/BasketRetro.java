/**
 * Created by user on 2017-04-30 오후 5:51
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

import net.balgre.domain.BasketResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 2017-04-30 오후 5:51
 * Prac / net.balgre.network
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/30  김진국          최초 생성
 *  </pre>
 * @since 2017/04/10
 */


public interface BasketRetro {

    @GET("/api/v1/basket/list")
    Call<BasketResponse> basketGET(
            @Header("X-Authorization") String token,
            @Header("Content-Type") String contentType
    );

    @POST("/api/v1/basket/add")
    Call<BasketResponse> basketAddPOST(
            @Header("X-Authorization") String token,
            @Header("Content-Type") String contentType,
            @Query("item_id") int item_id2,
            @Query("item_count") long item_count2

    );

}
