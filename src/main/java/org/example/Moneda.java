package org.example;

public class Moneda {

    private String base_code;
    private String target_code;
    private double conversion_result;

    // Getters y Setters
    public String getBase_code() {
        return base_code;
    }

    public String getTarget_code() {
        return target_code;
    }

    public double getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(double conversion_result) {
        this.conversion_result = conversion_result;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "base_code='" + base_code + '\'' +
                ", target_code='" + target_code + '\'' +
                ", conversion_result=" + conversion_result +
                '}';
    }
}
