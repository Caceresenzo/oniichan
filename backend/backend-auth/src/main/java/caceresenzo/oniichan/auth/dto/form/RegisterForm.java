package caceresenzo.oniichan.auth.dto.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import caceresenzo.oniichan.auth.dto.form.base.IAuthenticationForm;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterForm implements IAuthenticationForm {
	
	@NotBlank
	@NotNull
	private String name;
	
	@Email
	@NotBlank
	@NotNull
	private String email;
	
	@NotBlank
	@NotNull
	private String password;
	
}