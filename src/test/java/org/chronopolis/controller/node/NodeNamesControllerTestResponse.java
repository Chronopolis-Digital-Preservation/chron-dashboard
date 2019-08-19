package org.chronopolis.controller.node;

import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.remote.node.impl.DefaultRemoteNodeStub;
import org.chronopolis.repository.RemoteNodeRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NodeNamesControllerTestResponse {


    @Test
    public void given_environment__when_getting_nodes_for_environment__then_repository_called() {

        RemoteNodeRepository repositoryStub = new RemoteNodeRepositoryStub();

        NodeNameController controller = new NodeNameController(repositoryStub);

        Collection<NodeNameResponse> nodeNameResponses = controller.getNodeNamesByEnvironment("production");

        assertThat(nodeNameResponses, contains(hasProperty("node", is("NCAR")), hasProperty("node", is("UCSD"))));
    }

    public class RemoteNodeRepositoryStub implements RemoteNodeRepository {

        @Override
        public RemoteNode getByName(String remoteNodeName) {
            return null;
        }

        @Override
        public List<RemoteNode> getIngestNodesByEnvironment(String environment) {
            return null;
        }

        public List<RemoteNode> getNodesByEnvironment(String environment) {
            List<RemoteNode> remoteNodeList = new ArrayList<>();

            remoteNodeList.add(new RemoteNodeStub("NCAR"));
            remoteNodeList.add(new RemoteNodeStub("UCSD"));

            return remoteNodeList;
        }

        public Map<String, RemoteNode> getNodesMapByEnvironment(String environment) {
            return null;
        }
    }

    public class RemoteNodeStub extends DefaultRemoteNodeStub {

        private String nodeName;

        public RemoteNodeStub(String nodeName) {

            this.nodeName = nodeName;
        }

        @Override
        public String getNodeName() {
            return this.nodeName;
        }

    }
}
