import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {

    private Instant timestamp;
    private Integer count;

    public Request(Instant timestamp, Integer count) {
        this.timestamp = timestamp;
        this.count = count;
    }
}