package be.gerard.game.service.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * GameRestService
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RestController
@RequestMapping("/games")
public class GameRestService {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "Test" + name;
    }

}
