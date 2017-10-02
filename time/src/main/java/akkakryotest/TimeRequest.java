package akkakryotest;

import akka.routing.ConsistentHashingRouter;
import lombok.Data;

@Data
public class TimeRequest implements ConsistentHashingRouter.ConsistentHashable {
    @Override
    public Object consistentHashKey() {
        return hashCode();
    }
}
