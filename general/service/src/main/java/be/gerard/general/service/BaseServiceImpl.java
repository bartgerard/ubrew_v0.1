package be.gerard.general.service;

import java.time.LocalDateTime;

/**
 * BaseService
 *
 * @author bartgerard
 * @version 0.0.1
 */
public abstract class BaseServiceImpl {

    public String ping() {
        //"You have reached the voicemail of uBrew. Please leave a message after the beep. *Beep* Hi %s here! I forgot to thank you for your wonderful beer! Keep up the good work!"
        return String.format("pong() %s", LocalDateTime.now());
    }

}
