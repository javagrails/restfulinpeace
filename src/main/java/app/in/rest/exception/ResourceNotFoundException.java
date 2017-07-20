package app.in.rest.exception;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName ResourceNotFoundException.java | app.in.rest.exception | ResourceNotFoundException
 * @since Jul 20, 2017 22:18:18 PM
 */

public class ResourceNotFoundException extends RuntimeException {

    private Long resourceId;

    public ResourceNotFoundException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}