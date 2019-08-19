package org.chronopolis.controller.ace.bagsummary;

import org.chronopolis.model.AceBagSummary;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.remote.node.impl.DefaultRemoteNodeStub;
import org.chronopolis.repository.RemoteNodeRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AceBagSummaryControllerTest {

    @Test
    public void testGetSummaryStatusReturnedCorrectly() {

        RemoteNodeRepository repositoryStub = new RemoteNodeRepositoryStub();

        AceBagSummaryController controller = new AceBagSummaryController(repositoryStub);

        Collection<AceBagSummary> aceBagSummaryStatuses = controller.getSummaryStatuses(this.createAceBagSummaryRequestList());

        assertThat(aceBagSummaryStatuses, is(notNullValue()));
    }

    private List<AceBagSummaryRequest> createAceBagSummaryRequestList() {

        List<AceBagSummaryRequest> aceBagSummaryRequestList = new ArrayList<>();

        AceBagSummaryRequest aceBagSummaryRequest = new AceBagSummaryRequest();
        aceBagSummaryRequest.setNode("UCSD");
        aceBagSummaryRequestList.add(aceBagSummaryRequest);

        return aceBagSummaryRequestList;
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

        public Map<String, RemoteNode> getNodesMapByEnvironment(String environment) {
            return null;
        }
    }

    private class RemoteNodeStub extends DefaultRemoteNodeStub {

        @Override
        public AceBagSummary getAceSummaryStatus() {

            return new AceBagSummary();

        }

        public String getEnvironment() {
            return "production";
        }
    }
}
