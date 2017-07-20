package app.in.rest.utils;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName CustomResponseEntity.java | app.in.rest.utils | CustomResponseEntity
 * @since Jul 20, 2017 23:53:53 PM
 */

public class CustomResponseEntity {
    private String type;
    private String message;

    public CustomResponseEntity(String message) {
        this.type = "CUSTOM_RESPONSE";
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
