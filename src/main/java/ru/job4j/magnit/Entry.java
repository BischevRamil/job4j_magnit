package ru.job4j.magnit;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "entry")
@XmlAccessorType (XmlAccessType.FIELD)
public class Entry {
    private int entry;

    public Entry() {
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }
}
