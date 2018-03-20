package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	/**
	 * EndPoints
	 * 
	 * =====
	 * @RestController annotation, which marks the class as a controller where every method returns a domain object instead of a view.
	 * It’s shorthand for @Controller and @ResponseBody rolled together
	 */

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //@RequestMapping("/greeting")
    @RequestMapping(value = "greeting", method = RequestMethod.GET)
    public @ResponseBody Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}