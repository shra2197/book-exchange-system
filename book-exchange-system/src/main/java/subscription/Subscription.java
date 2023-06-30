package subscription;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Subscription {
    private Plan plan;
    private LocalDate startDate;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Subscription(Plan plan, LocalDate startDate) {
        this.plan = plan;
        this.startDate = startDate;
    }
}
