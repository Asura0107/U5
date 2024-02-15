package u5w2d3.u5w2d3.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class ErrorListPayload extends ErrorsPayload{

    private List<String> errors;

    public ErrorListPayload(String message, LocalDateTime timestamp, List<String> errors) {
        super(message, timestamp);
        this.errors = errors;
    }
}
