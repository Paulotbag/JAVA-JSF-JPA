/********************************************************************************************************2*4*w*
 * File:  PhoneValidator.java Course Materials CST8277
 *
 * @author Teddy Yap
 * @author Shariar (Shawn) Emami
 * @author (original) Mike Norman
 * Implemented by Paulo Ricardo Gomes Granjeiro 041118057
 * @version java 21.0.1 2023-10-17 LTS
 */
package databank.jsf;

import java.util.regex.Pattern;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 * Class to validate phone field in the xhtml page
 */
@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator<String> {

	// North American phone number pattern
	private static final Pattern PHONE_PATTERN = Pattern
			.compile("^(\\+\\d( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

	/**
	 * @param context FacesContext for the request we are processing
	 * @param component UIComponent we are checking for correctness
	 * @param value the value to validate
	 * @throws ValidatorException
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

		if (value == null) {
			FacesMessage msg = new FacesMessage("Phone number should not be empty", "Invalid phone number format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		if (!PHONE_PATTERN.matcher(value).matches()) {
            FacesMessage msg = new FacesMessage("Invalid phone number", "Please enter a valid North American phone number.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
		}
	}

}
