package Task_5_ExceptionHandling_RobustFileProcessing;

public class InvalidProductDataException extends Exception{
    public InvalidProductDataException(String message) {
        super(message);
    }
}
