package hemoterapia.statistics;

public class Statistics {

	private int professionalQty;
	private int professionalWithoutLodgingsOptionOneQty;
	private int professionalWithoutLodgingsOptionTwoQty;
	private int professionalWithLodgingsOptionOneQty;
	private int professionalWithLodgingsOptionTwoQty;
	
	private int technicianQty;
	private int technicianWithoutLodgingsOptionOneQty;
	private int technicianWithoutLodgingsOptionTwoQty;
	private int technicianWithLodgingsOptionOneQty;
	private int technicianWithLodgingsOptionTwoQty;
	
	private int guestQty;
	private int companionsTypeOneQty;
	private int companionsTypeTwoQty;
	private int totalPersons;
	private double totalAmount;
	

	public int getProfessionalQty() {
		return professionalQty;
	}
	public void setProfessionalQty(int professionalQty) {
		this.professionalQty = professionalQty;
	}

	public int getProfessionalWithoutLodgingsOptionOneQty() {
		return professionalWithoutLodgingsOptionOneQty;
	}
	public void setProfessionalWithoutLodgingsOptionOneQty(int professionalWithoutLodgingsOptionOneQty) {
		this.professionalWithoutLodgingsOptionOneQty = professionalWithoutLodgingsOptionOneQty;
	}

	public int getProfessionalWithoutLodgingsOptionTwoQty() {
		return professionalWithoutLodgingsOptionTwoQty;
	}
	public void setProfessionalWithoutLodgingsOptionTwoQty(int professionalWithoutLodgingsOptionTwoQty) {
		this.professionalWithoutLodgingsOptionTwoQty = professionalWithoutLodgingsOptionTwoQty;
	}

	public int getProfessionalWithLodgingsOptionOneQty() {
		return professionalWithLodgingsOptionOneQty;
	}
	public void setProfessionalWithLodgingsOptionOneQty(int professionalWithLodgingsOptionOneQty) {
		this.professionalWithLodgingsOptionOneQty = professionalWithLodgingsOptionOneQty;
	}

	public int getProfessionalWithLodgingsOptionTwoQty() {
		return professionalWithLodgingsOptionTwoQty;
	}
	public void setProfessionalWithLodgingsOptionTwoQty(int professionalWithLodgingsOptionTwoQty) {
		this.professionalWithLodgingsOptionTwoQty = professionalWithLodgingsOptionTwoQty;
	}

	public int getTechnicianQty() {
		return technicianQty;
	}
	public void setTechnicianQty(int technicianQty) {
		this.technicianQty = technicianQty;
	}

	public int getTechnicianWithoutLodgingsOptionOneQty() {
		return technicianWithoutLodgingsOptionOneQty;
	}
	public void setTechnicianWithoutLodgingsOptionOneQty(int technicianWithoutLodgingsOptionOneQty) {
		this.technicianWithoutLodgingsOptionOneQty = technicianWithoutLodgingsOptionOneQty;
	}

	public int getTechnicianWithoutLodgingsOptionTwoQty() {
		return technicianWithoutLodgingsOptionTwoQty;
	}
	public void setTechnicianWithoutLodgingsOptionTwoQty(int technicianWithoutLodgingsOptionTwoQty) {
		this.technicianWithoutLodgingsOptionTwoQty = technicianWithoutLodgingsOptionTwoQty;
	}

	public int getTechnicianWithLodgingsOptionOneQty() {
		return technicianWithLodgingsOptionOneQty;
	}
	public void setTechnicianWithLodgingsOptionOneQty(int technicianWithLodgingsOptionOneQty) {
		this.technicianWithLodgingsOptionOneQty = technicianWithLodgingsOptionOneQty;
	}

	public int getTechnicianWithLodgingsOptionTwoQty() {
		return technicianWithLodgingsOptionTwoQty;
	}
	public void setTechnicianWithLodgingsOptionTwoQty(int technicianWithLodgingsOptionTwoQty) {
		this.technicianWithLodgingsOptionTwoQty = technicianWithLodgingsOptionTwoQty;
	}

	public int getGuestQty() {
		return guestQty;
	}
	public void setGuestQty(int guestQty) {
		this.guestQty = guestQty;
	}

	public int getCompanionsTypeOneQty() {
		return companionsTypeOneQty;
	}
	public void setCompanionsTypeOneQty(int companionsTypeOneQty) {
		this.companionsTypeOneQty = companionsTypeOneQty;
	}

	public int getCompanionsTypeTwoQty() {
		return companionsTypeTwoQty;
	}
	public void setCompanionsTypeTwoQty(int companionsTypeTwoQty) {
		this.companionsTypeTwoQty = companionsTypeTwoQty;
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

	public Statistics(	int professionalQty, int professionalWithoutLodgingsOptionOneQty, int professionalWithoutLodgingsOptionTwoQty, 
						int professionalWithLodgingsOptionOneQty, int professionalWithLodgingsOptionTwoQty,
						int technicianQty, int technicianWithoutLodgingsOptionOneQty, int technicianWithoutLodgingsOptionTwoQty,
						int technicianWithLodgingsOptionOneQty, int technicianWithLodgingsOptionTwoQty, 
						int guestQty, int companionsTypeOneQty, int companionsTypeTwoQty, int totalPersons, double totalAmount) {
		
		this.setProfessionalQty(professionalQty);
		this.setProfessionalWithoutLodgingsOptionOneQty(professionalWithoutLodgingsOptionOneQty);
		this.setProfessionalWithoutLodgingsOptionTwoQty(professionalWithoutLodgingsOptionTwoQty);
		this.setProfessionalWithLodgingsOptionOneQty(professionalWithLodgingsOptionOneQty);
		this.setProfessionalWithLodgingsOptionTwoQty(professionalWithLodgingsOptionTwoQty);
		
		this.setTechnicianQty(technicianQty);
		this.setTechnicianWithoutLodgingsOptionOneQty(technicianWithoutLodgingsOptionOneQty);
		this.setTechnicianWithoutLodgingsOptionTwoQty(technicianWithoutLodgingsOptionTwoQty);
		this.setTechnicianWithLodgingsOptionOneQty(technicianWithLodgingsOptionOneQty);
		this.setTechnicianWithLodgingsOptionTwoQty(technicianWithLodgingsOptionTwoQty);
		
		this.setGuestQty(guestQty);
		this.setCompanionsTypeOneQty(companionsTypeOneQty);
		this.setCompanionsTypeTwoQty(companionsTypeTwoQty);

		this.setTotalPersons(totalPersons);
		this.setTotalAmount(totalAmount);
	}
}
