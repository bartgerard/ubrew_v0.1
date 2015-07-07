package be.gerard.evenizer.web.controller;

import be.gerard.core.interface_v1.model.Evening;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EveningBean
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Scope("session")
@Named
public class EveningBean implements Serializable {

    private Evening evening = new Evening();

    private List<String> themes = Arrays.asList("Piraten", "Cowboys", "Romeinen");

    public Evening getEvening() {
        return evening;
    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();

        for (String theme : themes) {
            if (theme.contains(query)) {
                results.add(theme);
            }
        }

        return results;
    }

}
