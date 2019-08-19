package org.chronopolis.model;

import java.util.ArrayList;
import java.util.List;

public class AceDepositorBagSummary {

    private String node = "";

    private String depositor = "";

    private List<DepositorBagSummary> bagSummaries = new ArrayList<>();

    private String message = "";

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public List<DepositorBagSummary> getBagSummaries() {
        return bagSummaries;
    }

    public void setBagSummaries(List<DepositorBagSummary> bagSummaries) {
        this.bagSummaries = bagSummaries;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
