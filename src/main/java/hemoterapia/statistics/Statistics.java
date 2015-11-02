package hemoterapia.statistics;

public class Statistics {

	private int professionalQTy;
	private int professionalWithLodgingsQty;
	private int professionalWithoutLodgingsQty;
	
	private int technicianQTy;
	private int technicianWithLodgingsQty;
	private int technicianWithoutLodgingsQty;
	
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
	
	public int getProfessionalWithLodgingsQty() {
		return professionalWithLodgingsQty;
	}
	public void setProfessionalWithLodgingsQty(int professionalsWithLodgingsQty) {
		this.professionalWithLodgingsQty = professionalsWithLodgingsQty;
	}

	public int getProfessionalWithoutLodgingsQty() {
		return professionalWithoutLodgingsQty;
	}
	public void setProfessionalWithoutLodgingsQty(int professionalWithoutLodgingssQty) {
		this.professionalWithoutLodgingsQty = professionalWithoutLodgingssQty;
	}

	public int getTechnicianQTy() {
		return technicianQTy;
	}
	public void setTechnicianQTy(int technicianQTy) {
		this.technicianQTy = technicianQTy;
	}

	public int getTechnicianWithLodgingsQty() {
		return technicianWithLodgingsQty;
	}
	public void setTechnicianWithLodgingsQty(int technicianWithLodgingsQty) {
		this.technicianWithLodgingsQty = technicianWithLodgingsQty;
	}

	public int getTechnicianWithoutLodgingsQty() {
		return technicianWithoutLodgingsQty;
	}
	public void setTechnicianWithoutLodgingsQty(int technicianWithoutLodgingssQty) {
		this.technicianWithoutLodgingsQty = technicianWithoutLodgingssQty;
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


	public Statistics(int professionalQTy, int professionalWithLodgingsQty, int professionalWithoutLodgingsQty,
					  int technicianQTy, int technicianWithLodgingsQty, int technicianWithoutLodgingsQty, 
					  int guestQTy, int companionsQty, int personsWithLodgingsQty, int totalPersons, double totalAmount) {
		
		this.setProfessionalQTy(professionalQTy);
		this.setProfessionalWithLodgingsQty(professionalWithLodgingsQty);
		this.setProfessionalWithoutLodgingsQty(professionalWithoutLodgingsQty);
		
		this.setTechnicianQTy(technicianQTy);
		this.setTechnicianWithLodgingsQty(technicianWithLodgingsQty);
		this.setTechnicianWithoutLodgingsQty(technicianWithoutLodgingsQty);
		
		this.setGuestQTy(guestQTy);
		this.setCompanionsQty(companionsQty);
		this.setPersonsWithLodgingsQty(personsWithLodgingsQty);
		this.setTotalPersons(totalPersons);
		this.setTotalAmount(totalAmount);
	}
}
