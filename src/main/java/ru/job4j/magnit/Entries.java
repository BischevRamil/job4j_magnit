package ru.job4j.magnit;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType (XmlAccessType.FIELD)
class Entries {

    @XmlElement(name = "entries")
    private List<Entry> entries;

    public Entries() {
    }

    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}

