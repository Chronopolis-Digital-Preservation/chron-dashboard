package org.chronopolis.model;

import java.util.ArrayList;
import java.util.List;

public class AceDepositorSummary {

    private String node = "";

    private List<DepositorSummary> depositors = new ArrayList<>();

    private String message = "";

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public List<DepositorSummary> getDepositors() {
        return depositors;
    }

    public void setDepositors(List<DepositorSummary> depositors) {
        this.depositors = depositors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
