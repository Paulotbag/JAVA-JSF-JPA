/********************************************************************************************************2*4*w*
 * File:  EmailValidator.java Course Materials CST8277
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
 * Class to validate email field in the xhtml page
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator<String> {

	// Really really (!) simple email pattern:  at-least-1-letter, '@', at-least-1-letter
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");

	/**
	 * @param context FacesContext for the request we are processing
	 * @param component UIComponent we are checking for correctness
	 * @param value the value to validate
	 * @throws ValidatorException
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

		if (value == null) {
			FacesMessage msg = new FacesMessage("Email should not be empty", "Invalid email format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		if (!EMAIL_PATTERN.matcher(value).matches()) {
            FacesMessage msg = new FacesMessage("Invalid email", "Please enter a valid email address.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
	}

}
