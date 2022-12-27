package br.com.bb.Errors;

public class NotInDatabaseException extends RuntimeException{
    
    public NotInDatabaseException(String msg)
    {
        super(msg);
    }

}
