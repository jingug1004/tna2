package net.balgre.Restful;

import net.balgre.domain.Plan;
import net.balgre.network.PlanRetroImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


/**
 * Unit test for simple App.
 */
public class PlanRestTest {

    PlanRetroImpl planRetroImpl;

    @Before
    public void setup() {
        planRetroImpl = new PlanRetroImpl();
    }

    @Test
    public void getPlan() {

        Integer planId = 9;

        Plan response = planRetroImpl.getPlan(planId);

        assertNotNull(response);
        assertNotNull(response.getStopPlan());
//        assertNull(response);



    }

//    @Test
//    public void getPlanDetail() {
//        Long productId = 1L;
//        Plan response = planRetroImpl.;
//        assertNotNull(response);
//    }

}
