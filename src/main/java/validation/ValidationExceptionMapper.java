package validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {

        ValidationError validationError = new ValidationError("400", "Erro de validação.");
        validationError.addFieldError(exception.getMessage(), exception.getMessage());

        return Response.status(Status.BAD_REQUEST).entity(validationError).build();

    }
}