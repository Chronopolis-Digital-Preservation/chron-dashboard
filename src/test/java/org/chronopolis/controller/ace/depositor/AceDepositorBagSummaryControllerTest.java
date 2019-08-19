package org.chronopolis.controller.ace.depositor;

import org.chronopolis.model.AceDepositorBagSummary;
import org.chronopolis.model.AceDepositorSummary;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.remote.node.impl.DefaultRemoteNodeStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AceDepositorBagSummaryControllerTest {

    private AceDepositorBagSummaryController controller;

    @Before
    public void setup() {

        this.controller = new AceDepositorBagSummaryController();
    }

    @Test
    public void testGetDepositorsIsNotNull() {

        RemoteNode remoteNodeStub = new DepositorsRemoteNodeStub();

        AceDepositorSummary aceDepositorSummary = this.controller.getDepositors(remoteNodeStub);

        assertThat(aceDepositorSummary, is(notNullValue()));
    }

    private class DepositorsRemoteNodeStub extends DefaultRemoteNodeStub {

        public AceDepositorSummary getAceDepositorSummaryStatus() {

            return new AceDepositorSummary();
        }
    }

    @Test
    public void testGetDepositorBagsIsNotNull() {

        DepositorBagsRemoteNodeStub remoteNodeStub = new DepositorBagsRemoteNodeStub();

        AceDepositorBagSummary aceDepositorBagSummary = this.controller.getDepositorBags(remoteNodeStub, "ACADIS");

        assertThat(aceDepositorBagSummary, is(notNullValue()));
    }

    @Test
    public void testGetDepositorBagsIsCalledWithCorrectDepositorName() {

        DepositorBagsRemoteNodeStub remoteNodeStub = new DepositorBagsRemoteNodeStub();

        this.controller.getDepositorBags(remoteNodeStub, "ACADIS");

        assertThat(remoteNodeStub.getRemoteNodeName(), is("ACADIS"));
    }

    private class DepositorBagsRemoteNodeStub extends DefaultRemoteNodeStub {

        private String remoteNodeName;

        public AceDepositorBagSummary getAceDepositorBagSummaryStatus(String remoteNodeName) {

            this.remoteNodeName = remoteNodeName;

            return new AceDepositorBagSummary();
        }

        public String getRemoteNodeName() {

            return this.remoteNodeName;
        }
    }
}
