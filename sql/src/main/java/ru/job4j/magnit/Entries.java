package ru.job4j.magnit;
import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "Values")
@XmlAccessorType (XmlAccessType.FIELD)
public class Entries {
    @XmlElement(name = "Value")
    private List<Entry> entries = new LinkedList<>();

    public Entries() {

    }

    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getList() {
        return entries;
    }

    public void setList(List<Entry> list) {
        this.entries = list;
    }
}
