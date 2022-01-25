package primitives;

public class Material {
	public double kD = 0d;
	public double kS = 0d;
	public double kT= 0d;
	public double kR= 0d;
	public int nShininess = 0;
	
	public Material setkD(Double kD) {
		this.kD = kD;
		return this;
	}
	
	public Material setkS(Double kS) {
		this.kS = kS;
		return this;
	}
	
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	
	public Double getkD() {
		return kD;
	}

	public Double getkS() {
		return kS;
	}

	public int getnShininess() {
		return nShininess;
	}

	public double getkT() {
		return kT;
	}

	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}

	public double getkR() {
		return kR;
	}

	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}

	

}
