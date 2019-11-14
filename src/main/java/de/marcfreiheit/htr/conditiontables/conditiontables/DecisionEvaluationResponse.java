package de.marcfreiheit.htr.conditiontables.conditiontables;

public class DecisionEvaluationResponse {

    private double price;
    private String error;

    public DecisionEvaluationResponse() { }
    public DecisionEvaluationResponse(double price, String error) {
        this.price = price;
        this.error = error;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
