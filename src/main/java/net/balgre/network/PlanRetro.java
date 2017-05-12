package net.balgre.network;


import net.balgre.domain.Plan;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlanRetro {

    @GET("/api/plan")
    Call<Plan> getPlan();

    @GET("/api/plan")
    Call<Plan> getPlanDetail();

    @GET("/api/plan")
    Call<Plan> getPlan(
            @Query("pid") int pid1
    );


}
