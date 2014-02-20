/**
 * Thrown when submitted application contain invalid data
 */
public class SubmissionException extends Exception 
{
    private static final long serialVersionUID = 16247164402L;
    
    public SubmissionException(String msg) {
       super(msg);
    }
}