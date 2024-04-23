package org.example.network;

import org.example.model.LabWork;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private String command;
    private String argument;
    private LabWork labWork;
    public Request (String command){
        this.command = command;
    }
    public Request (String command, String argument){
        this.command = command;
        this.argument = argument;
    }
    public Request (String command, String argument, LabWork labWork){
        this.command = command;
        this.argument = argument;
        this.labWork = labWork;
    }
    public String getCommand(){ return command;}
    public String getArgument(){return argument;}
    public LabWork getLabWork(){return labWork;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request response = (Request) o;
        return Objects.equals(command, response.command);
    }
}
