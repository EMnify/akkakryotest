package akkakryotest;

import akka.actor.AbstractActor;

public class PongActor extends AbstractActor {
    public Receive createReceive() {
        return receiveBuilder()
                .match(Ping.class, ping -> {
                    System.out.println("Received " + ping + " from " + sender());
                    sender().tell(new Pong(ping.getId()), self());
                })
                .build();
    }
}
