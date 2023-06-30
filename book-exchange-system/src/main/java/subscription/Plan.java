package subscription;

import lombok.Data;

@Data
public class Plan {
    private String planId;
    private double MonthlyCost;

    public Plan(String planId, double monthlyCost) {
        this.planId = planId;
        this.MonthlyCost = monthlyCost;
    }
}
