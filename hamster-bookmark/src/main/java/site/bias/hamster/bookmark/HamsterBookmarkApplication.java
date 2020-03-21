package site.bias.hamster.bookmark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@EnableSwagger2
@MapperScan("site.bias.hamster.bookmark.mapper")
@SpringBootApplication
public class HamsterBookmarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamsterBookmarkApplication.class, args);
	}

}
