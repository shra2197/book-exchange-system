package subscription;

import java.util.Date;

public class Subscription {
    private Plan plan;
    private Date startDate;

    public Plan getPlan() {
        return plan;
    }



    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Subscription(Plan plan, Date startDate) {
        this.plan = plan;
        this.startDate = startDate;
    }
}
