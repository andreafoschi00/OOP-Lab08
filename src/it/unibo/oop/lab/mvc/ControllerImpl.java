package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ControllerImpl implements Controller {

    private final List<String> history = new LinkedList<>();
    private String newString;
    /**
     * 
     */
    @Override
    public void setNextStringToPrint(final String string) {
        this.newString = Objects.requireNonNull(string, "String expected..");
    }
    /**
     * 
     */
    @Override
    public String getNextStringToPrint() {
        return this.newString;
    }
    /**
     * 
     */
    @Override
    public List<String> getHistory() {
        return this.history;
    }
    /**
     * 
     */
    @Override
    public void printCurrentString() {
       if (this.newString == null) {
           throw new IllegalStateException("String not defined!");
       }
       history.add(this.newString);
       System.out.println(this.newString);
    }

}
