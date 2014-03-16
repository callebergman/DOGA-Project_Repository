
package Model;

import javax.ejb.ApplicationException;

/**
 * Thrown when submitted application contain invalid data
 * exception class
 */
@ApplicationException(rollback=true)
public class RetrievalException extends RuntimeException  
{
    private static final long serialVersionUID = 16247164402L;
    
    public RetrievalException(String msg) {
       super(msg);
    }

    public RetrievalException() {
    }
}