package snowboardingsys.paydesk;

import java.time.LocalDate;
import java.util.Date;

public class Card {
	private String user;
	private LocalDate dateOfIssue;
	
	Card(String user, LocalDate dateOfIssue) {
		this.user = user;
		this.dateOfIssue = dateOfIssue;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	

}
