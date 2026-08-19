class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = (message == null) ? "" : message;
        this.data = success ? data : null;
    }

    public boolean isSuccess() { 
        return success; 
    }
    
    public String getMessage() { 
        return message; 
    }
    
    public T getData() { 
        return data; 
    }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        Result<String> stringResult = new Result<>(true, "Data fetched successfully", "Hello Java Generic");
        System.out.println("Success: " + stringResult.isSuccess());
        System.out.println("Message: " + stringResult.getMessage());
        System.out.println("Data: " + stringResult.getData());

        System.out.println("-------------------------");

        Result<Integer> intResult = new Result<>(false, "ID not found in database", null);
        System.out.println("Success: " + intResult.isSuccess());
        System.out.println("Message: " + intResult.getMessage());
        System.out.println("Data: " + intResult.getData());
    }
}