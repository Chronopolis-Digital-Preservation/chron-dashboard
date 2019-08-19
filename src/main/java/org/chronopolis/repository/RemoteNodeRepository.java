package org.chronopolis.repository;

import org.chronopolis.remote.node.RemoteNode;

import java.util.Collection;
import java.util.List;

public interface RemoteNodeRepository {

    RemoteNode getByName(String remoteNodeName);

    List<RemoteNode> getNodesByEnvironment(String environment);

    Collection<RemoteNode> getIngestNodesByEnvironment(String environment);
}
