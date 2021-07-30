package hu.webuni.hr.rita.model;

public class PositionAvgSalary {
	private String position;
    private Double avgSalary;
	public PositionAvgSalary(String position, Double avgSalary) {
		super();
		this.position = position;
		this.avgSalary = avgSalary;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Double getAvgSalary() {
		return avgSalary;
	}
	public void setAvgSalary(Double avgSalary) {
		this.avgSalary = avgSalary;
	}

    

}
