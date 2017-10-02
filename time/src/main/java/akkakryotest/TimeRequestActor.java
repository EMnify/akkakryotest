package akkakryotest;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.cluster.routing.ClusterRouterGroup;
import akka.cluster.routing.ClusterRouterGroupSettings;
import akka.routing.ConsistentHashingGroup;
import scala.concurrent.duration.Duration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class TimeRequestActor extends AbstractActor {
    @Override
    public void preStart() throws Exception {
        Iterable<String> paths = Collections.singletonList(self().path().parent().child("timeresponse").toStringWithoutAddress());
        ActorRef pongRouter = context()
                .actorOf(
                        new ClusterRouterGroup(
                                new ConsistentHashingGroup(paths),
                                new ClusterRouterGroupSettings(5, paths, false, "time")
                        ).props(),
                        "timerouter"
                );

        context().system().scheduler().schedule(
                Duration.create(1000, TimeUnit.MILLISECONDS),
                Duration.create(1000, TimeUnit.MILLISECONDS),
                () -> {
                    pongRouter.tell(new TimeRequest(), self());
                },
                context().dispatcher()
        );
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TimeResponse.class, timeResponse -> {
                    System.out.println("Received " + timeResponse + " from " + sender());
                })
                .build();
    }
}
