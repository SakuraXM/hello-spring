package geektime.spring.hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/** 
* @Description: HelloSpringApplication启动类
* @Author: SakuraM
* @Date: 2019/5/12 
*/
@SpringBootApplication
@RestController
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}


	/**
	 * @Description: hello方法
	 * @param:
	 * @return: String
	 * @auther: SakuraM
	 * @date: 2019/5/12 21:32
	 */
	@RequestMapping("/hello")
	public String hello(){
		return "Hello Spring";
	}

}
