package org.chronopolis.controller.ace.depositor;

import org.chronopolis.model.AceDepositorBagSummary;
import org.chronopolis.model.AceDepositorSummary;
import org.chronopolis.remote.node.RemoteNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AceDepositorBagSummaryController {

    @RequestMapping(value = "/api/v1/node/{node}/ace/depositor.json")
    @ResponseBody
    public AceDepositorSummary getDepositors(@PathVariable("node") RemoteNode remoteNode) {

        return remoteNode.getAceDepositorSummaryStatus();
    }

    @RequestMapping(value = "/api/v1/node/{node}/ace/depositor/{depositor}.json")
    @ResponseBody
    public AceDepositorBagSummary getDepositorBags(@PathVariable("node") RemoteNode remoteNode, @PathVariable("depositor") String depositor) {

        return remoteNode.getAceDepositorBagSummaryStatus(depositor);
    }
}
