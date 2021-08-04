package hu.webuni.hr.rita.model;

public class PositionAvgSalary {
	private Position position;
    private Double avgSalary;
	public PositionAvgSalary(Position position, Double avgSalary) {
		super();
		this.position = position;
		this.avgSalary = avgSalary;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Double getAvgSalary() {
		return avgSalary;
	}
	public void setAvgSalary(Double avgSalary) {
		this.avgSalary = avgSalary;
	}

    

}
