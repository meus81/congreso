package hemoterapia.statistics;

public class Statistics {

	private int professionalQTy;
	private int technicianQTy;
	private int guestQTy;
	private int companionsQty;
	private int personsWithLodgingsQty;
	private int totalPersons;
	private double totalAmount;
	
	public int getProfessionalQTy() {
		return professionalQTy;
	}
	public void setProfessionalQTy(int professionalQTy) {
		this.professionalQTy = professionalQTy;
	}

	public int getTechnicianQTy() {
		return technicianQTy;
	}
	public void setTechnicianQTy(int technicianQTy) {
		this.technicianQTy = technicianQTy;
	}

	public int getGuestQTy() {
		return guestQTy;
	}
	public void setGuestQTy(int guestQTy) {
		this.guestQTy = guestQTy;
	}

	public int getCompanionsQty() {
		return companionsQty;
	}
	public void setCompanionsQty(int companionsQty) {
		this.companionsQty = companionsQty;
	}

	public int getPersonsWithLodgingsQty() {
		return personsWithLodgingsQty;
	}
	public void setPersonsWithLodgingsQty(int personsWithLodgingsQty) {
		this.personsWithLodgingsQty = personsWithLodgingsQty;
	}

	public int getTotalPersons() {
		return totalPersons;
	}
	public void setTotalPersons(int totalPersons) {
		this.totalPersons = totalPersons;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Statistics(int professionalQTy, int technicianQTy, int guestQTy, int companionsQty,
			int personsWithLodgingsQty, int totalPersons, double totalAmount) {
		this.setProfessionalQTy(professionalQTy);
		this.setTechnicianQTy(technicianQTy);
		this.setGuestQTy(guestQTy);
		this.setCompanionsQty(companionsQty);
		this.setPersonsWithLodgingsQty(personsWithLodgingsQty);
		this.setTotalPersons(totalPersons);
		this.setTotalAmount(totalAmount);
	}

}
