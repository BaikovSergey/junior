package ru.job4j.magnit;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="Entry")
@XmlAccessorType(XmlAccessType.FIELD)
    public class Entry {

    int value;

    public Entry() {

    }

    public Entry(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
