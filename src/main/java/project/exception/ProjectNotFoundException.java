package project.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String msg) {
        super(msg);
    }
}
