package exception;

import lombok.Getter;

@Getter
public class ProjectDataException extends Exception {
    public ProjectDataException(final String msg) {
        super(msg);
    }
}
