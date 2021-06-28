package hu.webuni.hr.rita.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {
	private Default def;
	private Smart smart;
	
	public Default getDef() {
		return def;
	}

	public void setDef(Default def) {
		this.def = def;
	}

	public Smart getSmart() {
		return smart;
	}

	public void setSmart(Smart smart) {
		this.smart = smart;
	}

	public static class Default{
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
		
	}
	
	public static class Smart{
		private float limit1;
		private float limit2;
		private float limit3;
		
		private int percent1;
		private int percent2;
		private int percent3;
		private int percent4;
		
		/////////////////////
		private float limits[];
		private int percents[];
		private int percent;
		
		public float getLimit1() {
			return limit1;
		}
		public void setLimit1(float limit1) {
			this.limit1 = limit1;
		}
		public float getLimit2() {
			return limit2;
		}
		public void setLimit2(float limit2) {
			this.limit2 = limit2;
		}
		public float getLimit3() {
			return limit3;
		}
		public void setLimit3(float limit3) {
			this.limit3 = limit3;
		}
		public int getPercent1() {
			return percent1;
		}
		public void setPercent1(int percent1) {
			this.percent1 = percent1;
		}
		public int getPercent2() {
			return percent2;
		}
		public void setPercent2(int percent2) {
			this.percent2 = percent2;
		}
		public int getPercent3() {
			return percent3;
		}
		public void setPercent3(int percent3) {
			this.percent3 = percent3;
		}
		public int getPercent4() {
			return percent4;
		}
		public void setPercent4(int percent4) {
			this.percent4 = percent4;
		}
		////////////////////////////
		public float[] getLimits() {
			return limits;
		}
		public void setLimits(float[] limits) {
			this.limits = limits;
		}
		public int[] getPercents() {
			return percents;
		}
		public void setPercents(int[] percents) {
			this.percents = percents;
		}
		public int getPercent() {
			return percent;
		}
		public void setPercent(int percent) {
			this.percent = percent;
		}
		
		
	}
}
