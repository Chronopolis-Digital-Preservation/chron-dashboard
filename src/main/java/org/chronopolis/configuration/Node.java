package org.chronopolis.configuration;

import org.springframework.util.StringUtils;

public class Node {

    private String name;
    private String aceEndpoint;
    private String aceHost;
    private Integer acePort;
    private String aceUsername;
    private String acePassword;
    private String ingestEndpoint;
    private String ingestHost;
    private Integer ingestPort;
    private String ingestUsername;
    private String ingestPassword;
    private String environment;

    public String getAceEndpoint() {
        return aceEndpoint;
    }

    public void setAceEndpoint(String aceEndpoint) {
        this.aceEndpoint = aceEndpoint;
    }

    public Integer getAcePort() {
        return acePort;
    }

    public void setAcePort(Integer acePort) {
        this.acePort = acePort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAceHost() {
        return aceHost;
    }

    public void setAceHost(String aceHost) {
        this.aceHost = aceHost;
    }

    public Boolean isIngestNode() {
        return StringUtils.hasText(this.ingestEndpoint);
    }

    public String getIngestEndpoint() {
        return ingestEndpoint;
    }

    public void setIngestEndpoint(String ingestEndpoint) {
        this.ingestEndpoint = ingestEndpoint;
    }

    public String getIngestHost() {
        return ingestHost;
    }

    public void setIngestHost(String ingestHost) {
        this.ingestHost = ingestHost;
    }

    public Integer getIngestPort() {
        return ingestPort;
    }

    public void setIngestPort(Integer ingestPort) {
        this.ingestPort = ingestPort;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getAceUsername() {
        return aceUsername;
    }

    public void setAceUsername(String aceUsername) {
        this.aceUsername = aceUsername;
    }

    public String getAcePassword() {
        return acePassword;
    }

    public void setAcePassword(String acePassword) {
        this.acePassword = acePassword;
    }

    public String getIngestUsername() {
        return ingestUsername;
    }

    public void setIngestUsername(String ingestUsername) {
        this.ingestUsername = ingestUsername;
    }

    public String getIngestPassword() {
        return ingestPassword;
    }

    public void setIngestPassword(String ingestPassword) {
        this.ingestPassword = ingestPassword;
    }
}
