package akkakryotest;

import akka.actor.AbstractActor;

import java.util.Date;

public class TimeResponseActor extends AbstractActor {
    public Receive createReceive() {
        return receiveBuilder()
                .match(TimeRequest.class, timeRequest -> {
                    System.out.println("Received " + timeRequest + " from " + sender());
                    sender().tell(new TimeResponse(new Date()), self());
                })
                .build();
    }
}
