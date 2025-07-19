package az.cybernet.internship.dictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("az.cybernet.internship.dictionary.mapper")
public class DictionaryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DictionaryServiceApplication.class, args);
    }
}
