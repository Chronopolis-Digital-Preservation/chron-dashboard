package org.chronopolis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties()
public class NodeConfiguration {

    // Magic name matches yaml
    private List<Node> nodes = new ArrayList<>();

    public List<Node> getNodes() {
        return this.nodes;
    }
}
