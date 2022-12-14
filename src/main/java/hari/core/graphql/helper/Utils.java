package hari.core.graphql.helper;

import org.springframework.core.env.Environment;

import java.net.UnknownHostException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utils {

    public String generateRandomString(Integer stringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = stringLength;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public String baseUrl(Environment environment) throws UnknownHostException {
//        return "http://"+InetAddress.getLocalHost().getHostAddress()+":"+environment.getProperty("local.server.port");
        return "https://www.usu.ac.id/api";
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
