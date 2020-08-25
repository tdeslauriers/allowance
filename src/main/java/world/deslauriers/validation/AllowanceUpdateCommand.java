package world.deslauriers.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AllowanceUpdateCommand {
	
	@NotNull
	private Double amount;
	
	@NotBlank
	@Size(max = 10, min = 2)
	private String firstname;

	public AllowanceUpdateCommand() {
	}

	public AllowanceUpdateCommand(@NotNull Double amount, @NotBlank @Size(max = 10, min = 2) String firstname) {
		this.amount = amount;
		this.firstname = firstname;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
}
