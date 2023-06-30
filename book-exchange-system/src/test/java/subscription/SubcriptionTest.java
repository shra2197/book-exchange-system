package subscription;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class SubcriptionTest {
    SubscriptionService subscriptionService;
    @Before
    public void setUp(){
        subscriptionService=new SubscriptionService();
        subscriptionService.addCustomer( new Customer("c11","C1"));
        final Subscription subscription = new Subscription(SubscriptionPlanConstant.getPlan("BASIC"), LocalDate.now().minusMonths(12));
        subscriptionService.addProduct("C1", new Product("Jira", subscription));
    }
    @Test
    public void getTest(){
        System.out.println(subscriptionService.monthlyCostList("C1","Jira"));
    }


}
