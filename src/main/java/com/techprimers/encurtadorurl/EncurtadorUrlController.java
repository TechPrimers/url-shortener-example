package com.techprimers.encurtadorurl;
import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;



@RequestMapping("/rest/url")
@RestController
public class EncurtadorUrlController {


    @Autowired
    StringRedisTemplate redisTemplate;


        @GetMapping("/{id}")
        public String getUrl(HttpServletResponse httpServletResponse, @PathVariable String id) {
            String url = redisTemplate.opsForValue().get(id);
            System.out.println("URL Retrieved: " + url);
            httpServletResponse.setHeader("Location", url);
            httpServletResponse.setStatus(302);
            if (url == null) {
                throw new RuntimeException("Não existe URL encurtada para este  : " + id);
            }
            return url;
        }

        @GetMapping("short/statics")
        public Integer getStatics(@RequestBody String id){
            return EncurtadorUrlService.Statics.my_Static(id);

        }


        @PostMapping
        public String create(@RequestBody String url) {
            UrlValidator urlValidator = new UrlValidator(
                    new String[]{"http", "https"}
            );
            if (urlValidator.isValid(url)) {
                String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
                System.out.println("ID da URL gerado com sucesso!: "+ id);
                redisTemplate.opsForValue().set(id, url);
                return ("Esta é sua URL encurtada :"+ " "+"http://localhost:8081/rest/url/" + id);
            }
            throw new RuntimeException("URL Invalida: " + url);
        }


}
