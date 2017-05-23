package net.balgre.network;


import net.balgre.domain.Plan;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlanRetro {
	
	/*plan list by minho*/
    @GET("/api/plan/{page}")
    Call<Plan> planList (
        @Path("page") int page
    );

}
