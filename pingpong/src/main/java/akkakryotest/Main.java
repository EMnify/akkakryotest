package akkakryotest;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ClusterSystem");
        system.actorOf(Props.create(PingActor.class), "ping");
        system.actorOf(Props.create(PongActor.class), "pong");
    }
}
