package Util;

public class Logger {
    private String className;
    public Logger(Class<?> clazz){
        this.className = clazz.getSimpleName();
    }
    public void print(String message){
        String logMessage = String.format(
                "[%s] : %s",
                this.className,
                message
        );
        System.out.println(logMessage);
    }
}
