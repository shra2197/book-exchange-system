package subscription;

public class Plan {
    private String planId;
    private double MonthlyCost;

    public Plan(String planId, double monthlyCost) {
        this.planId = planId;
        MonthlyCost = monthlyCost;
    }
}
