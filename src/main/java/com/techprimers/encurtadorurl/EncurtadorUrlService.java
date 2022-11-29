package com.techprimers.encurtadorurl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Hashtable;


@Service
public class EncurtadorUrlService {

    @Autowired
    static
    StringRedisTemplate redisTemplate;
    public static class  Statics {
        public static Hashtable<String, Integer> my_static = new Hashtable<>();
        public static Integer my_Static(String id) {
            my_static.merge(id, 1, Integer::sum);
            return my_static.get(id);

        }

    }

}
