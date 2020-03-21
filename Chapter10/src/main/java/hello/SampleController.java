package hello;


import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SampleController {
    @RequestMapping("/get")
    @ResponseBody
    String home(){
        return "test.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class,args);
    }

}
