package Model;

import java.util.regex.Pattern;
import javax.ejb.ApplicationException;

/**
 * Thrown when submitted application contain invalid data
 * Exceptiom handling class
 */
@ApplicationException(rollback=true)
public class SubmissionException extends RuntimeException  
{
    private static final long serialVersionUID = 16247164402L;
    
    public SubmissionException(String msg) {
       super(msg);
    }

    public SubmissionException() {
    }
}