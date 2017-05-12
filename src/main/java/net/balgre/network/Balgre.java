package net.balgre.network;


import net.balgre.domain.MainResponse;
import net.balgre.domain.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Balgre {
	@GET("/api/main")
    Call<MainResponse> getMain();
	
	@GET("/api/product/detail")
    Call<Product> getProductDetail(
        @Query("product_id") Long productId
    );
	
}
