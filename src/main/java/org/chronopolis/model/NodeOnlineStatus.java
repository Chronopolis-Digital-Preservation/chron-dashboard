package org.chronopolis.model;

public class NodeOnlineStatus {

    private String node;
    private String aceStatus;
    private String ingestStatus;
    private String url;
    private String ingestUrl;
    private String message;


    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getAceStatus() {
        return aceStatus;
    }

    public void setAceStatus(String aceStatus) {
        this.aceStatus = aceStatus;
    }

    public String getIngestStatus() {
        return ingestStatus;
    }

    public void setIngestStatus(String ingestStatus) {
        this.ingestStatus = ingestStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIngestUrl() {
        return ingestUrl;
    }

    public void setIngestUrl(String ingestUrl) {
        this.ingestUrl = ingestUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
