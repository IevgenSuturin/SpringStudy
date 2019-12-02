package lab2.practice;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PropertyRepository {
    private final static Map<String, String> map = new HashMap<String, String>() {
        {
            put("user", "tolik");
            put("password", "*****");
            put("url", "www.github.com/login");
        }
    };

    public static String get(String key) {
        return map.get(key);
    }
}
