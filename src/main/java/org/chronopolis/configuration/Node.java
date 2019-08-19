package org.chronopolis.configuration;

import org.springframework.util.StringUtils;

public class Node {

    private String name;
    private String aceendpoint;
    private String acehost;
    private Integer aceport;
    private String aceusername;
    private String acepassword;
    private String ingestendpoint;
    private String ingesthost;
    private Integer ingestport;
    private String ingestusername;
    private String ingestpassword;
    private String environment;

    public String getAceendpoint() {
        return aceendpoint;
    }

    public void setAceendpoint(String aceendpoint) {
        this.aceendpoint = aceendpoint;
    }

    public Integer getAceport() {
        return aceport;
    }

    public void setAceport(Integer aceport) {
        this.aceport = aceport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcehost() {
        return acehost;
    }

    public void setAcehost(String acehost) {
        this.acehost = acehost;
    }

    public Boolean isIngestNode() {
        return StringUtils.hasText(this.ingestendpoint);
    }

    public String getIngestendpoint() {
        return ingestendpoint;
    }

    public void setIngestendpoint(String ingestendpoint) {
        this.ingestendpoint = ingestendpoint;
    }

    public String getIngesthost() {
        return ingesthost;
    }

    public void setIngesthost(String ingesthost) {
        this.ingesthost = ingesthost;
    }

    public Integer getIngestport() {
        return ingestport;
    }

    public void setIngestport(Integer ingestport) {
        this.ingestport = ingestport;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getAceusername() {
        return aceusername;
    }

    public void setAceusername(String aceusername) {
        this.aceusername = aceusername;
    }

    public String getAcepassword() {
        return acepassword;
    }

    public void setAcepassword(String acepassword) {
        this.acepassword = acepassword;
    }

    public String getIngestusername() {
        return ingestusername;
    }

    public void setIngestusername(String ingestusername) {
        this.ingestusername = ingestusername;
    }

    public String getIngestpassword() {
        return ingestpassword;
    }

    public void setIngestpassword(String ingestpassword) {
        this.ingestpassword = ingestpassword;
    }
}
