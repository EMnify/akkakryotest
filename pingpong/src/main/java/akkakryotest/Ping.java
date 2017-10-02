package akkakryotest;

import akka.routing.ConsistentHashingRouter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ping implements ConsistentHashingRouter.ConsistentHashable {
    private long id;

    @Override
    public Object consistentHashKey() {
        return hashCode();
    }
}
