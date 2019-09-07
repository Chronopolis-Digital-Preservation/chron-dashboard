package org.chronopolis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "chron")
public class NodeConfiguration {

    // Magic name matches chron.nodes from environment variables
    // Example: CHRON_NODES_0_NAME=UCSD
    private List<Node> nodes = new ArrayList<>();

    public List<Node> getNodes() {
        return this.nodes;
    }
}
