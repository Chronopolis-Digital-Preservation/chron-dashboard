package org.chronopolis.controller.onlinestatus;

import org.chronopolis.controller.ace.onlinestatus.AceOnlineStatusController;
import org.chronopolis.controller.ace.onlinestatus.AceOnlineStatusRequest;
import org.chronopolis.model.NodeOnlineStatus;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.remote.node.impl.DefaultRemoteNodeStub;
import org.chronopolis.repository.RemoteNodeRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AceAceOnlineStatusControllerTest {

    @Test
    public void testGetOnlineStatusReturnedCorrectly() {

        RemoteNodeRepository repositoryStub = new RemoteNodeRepositoryStub();

        AceOnlineStatusController controller = new AceOnlineStatusController(repositoryStub);

        List<AceOnlineStatusRequest> aceOnlineStatusRequestList = new ArrayList<>();
        AceOnlineStatusRequest aceOnlineStatusRequest = new AceOnlineStatusRequest();
        aceOnlineStatusRequest.setNode("NCAR");
        aceOnlineStatusRequestList.add(aceOnlineStatusRequest);

        Collection<NodeOnlineStatus> aceOnlineStatuses = controller.getOnlineStatuses(aceOnlineStatusRequestList);

        assertThat(aceOnlineStatuses, contains(hasProperty("node", is("NCAR"))));
        assertThat(aceOnlineStatuses, contains(hasProperty("aceStatus", is("NcarStatus"))));
        assertThat(aceOnlineStatuses, contains(hasProperty("message", is("NcarMessage"))));
    }

    private class RemoteNodeRepositoryStub implements RemoteNodeRepository {

        @Override
        public RemoteNode getByName(String remoteNodeName) {
            return new RemoteNodeStub();
        }

        @Override
        public List<RemoteNode> getNodesByEnvironment(String environment) {
            return null;
        }

        @Override
        public List<RemoteNode> getIngestNodesByEnvironment(String environment) {
            return null;
        }
    }

    private class RemoteNodeStub extends DefaultRemoteNodeStub {

        @Override
        public NodeOnlineStatus getNodeOnlineStatus() {

            NodeOnlineStatus nodeOnlineStatus = new NodeOnlineStatus();
            nodeOnlineStatus.setNode("NCAR");
            nodeOnlineStatus.setAceStatus("NcarStatus");
            nodeOnlineStatus.setUrl("NcarUrl");
            nodeOnlineStatus.setMessage("NcarMessage");

            return nodeOnlineStatus;
        }
    }
}
