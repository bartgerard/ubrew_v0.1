package be.gerard.core.interface_v1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Evening
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class Evening {

    private LocalDate date;

    private final List<Entry> entries = new ArrayList<>();

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Entry> getEntries() {
        return entries;
    }

}
