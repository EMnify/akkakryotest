package akkakryotest;

import akka.routing.MurmurHash;
import com.esotericsoftware.kryo.Kryo;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;

public class AkkaKryoInitializer {
    public void customize(Kryo kryo) throws ClassNotFoundException {
        Config conf = ConfigFactory.load();
        List<String> classes = conf.getStringList("akka.actor.kryo.classes-unordered");
        for (String className : classes) {
            try {
                kryo.register(Class.forName(className), MurmurHash.stringHash(className) >>> 1);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
