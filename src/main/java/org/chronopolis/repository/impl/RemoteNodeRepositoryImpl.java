package org.chronopolis.repository.impl;

import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.IdentifierNotFoundException;
import org.chronopolis.repository.RemoteNodeRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoteNodeRepositoryImpl implements RemoteNodeRepository {

    private Map<String, RemoteNode> remoteNodeMap;

    public RemoteNodeRepositoryImpl(Map<String, RemoteNode> remoteNodeMap) {
        this.remoteNodeMap = remoteNodeMap;
    }

    @Override
    public RemoteNode getByName(String remoteNodeName) {

        RemoteNode remoteNode;

        if (this.remoteNodeMap.containsKey(remoteNodeName)) {

            remoteNode = remoteNodeMap.get(remoteNodeName);

        } else {

            throw new IdentifierNotFoundException(remoteNodeName);
        }

        return remoteNode;
    }

    @Override
    public List<RemoteNode> getNodesByEnvironment(String environment) {

        Map<String, RemoteNode> nodesMap = getNodesMapByEnvironment(environment);

        return nodesMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private Map<String, RemoteNode> getNodesMapByEnvironment(String environment) {

        return remoteNodeMap.entrySet().stream()
                .filter(map -> map.getValue().getEnvironment().equals(environment))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Collection<RemoteNode> getIngestNodesByEnvironment(String environment) {

        Map<String, RemoteNode> nodesMap = getNodesMapByEnvironment(environment);

        return nodesMap.entrySet().stream()
                .filter(map -> map.getValue().isIngestNode())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
