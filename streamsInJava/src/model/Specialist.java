package model;

import java.math.BigDecimal;

public class Specialist {
    private String name;
    private BigDecimal salary;
    private Specialty specialty;

    @Override
    public String toString() {
        return "\nSpecialist{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", specialty=" + specialty +
                "}";
    }

    public Specialist(String name, BigDecimal salary, Specialty specialty) {
        this.name = name;
        this.salary = salary;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
