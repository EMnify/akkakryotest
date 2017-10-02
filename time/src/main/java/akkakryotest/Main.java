package akkakryotest;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ClusterSystem");
        system.actorOf(Props.create(TimeRequestActor.class), "timerequest");
        system.actorOf(Props.create(TimeResponseActor.class), "timeresponse");

        system.actorOf(Props.create(PongActor.class), "pong");
    }
}
