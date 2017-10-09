package akkakryotest;

import akka.routing.MurmurHash;
import com.esotericsoftware.kryo.Kryo;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.lang.reflect.Field;
import java.util.List;

public class AkkaKryoInitializer {
    private int hash(String s) {
        return MurmurHash.stringHash(s) >>> 1;
    }

    private void registerClassDeep(Kryo kryo, Class<?> type) {
        try {
            kryo.getRegistration(type);
        } catch (IllegalArgumentException iae) {
            kryo.register(type, hash(type.getName()));
        }
        for (Field f : type.getDeclaredFields()) {
            registerClassDeep(kryo, f.getType());
        }
    }

    public void customize(Kryo kryo) throws ClassNotFoundException {
        Config conf = ConfigFactory.load();
        List<String> classes = conf.getStringList("akka.actor.kryo.classes-unordered");
        for (String className : classes) {
            try {
                registerClassDeep(kryo, Class.forName(className));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}