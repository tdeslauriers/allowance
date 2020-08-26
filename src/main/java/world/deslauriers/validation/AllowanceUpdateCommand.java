package world.deslauriers.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AllowanceUpdateCommand {
	
	@NotBlank
	@Size(max = 10, min = 2)
	private String firstname;

	public AllowanceUpdateCommand() {
	}

	public AllowanceUpdateCommand(@NotBlank @Size(max = 10, min = 2) String firstname) {
	
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
}
