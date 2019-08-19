package org.chronopolis.controller.ace.bagsummary;

import org.chronopolis.model.AceBagSummary;
import org.chronopolis.remote.node.RemoteNode;
import org.chronopolis.repository.RemoteNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AceBagSummaryController {

    private RemoteNodeRepository remoteNodeRepository;

    @Autowired
    public AceBagSummaryController(RemoteNodeRepository remoteNodeRepository) {

        this.remoteNodeRepository = remoteNodeRepository;
    }

    @RequestMapping(value = "/api/v1/getAceBagSummary.json")
    @ResponseBody
    public List<AceBagSummary> getSummaryStatuses(@RequestBody List<AceBagSummaryRequest> aceBagSummaryRequests) {

        List<AceBagSummary> aceBagSummaryStatuses = new ArrayList<>();

        for (AceBagSummaryRequest aceBagSummaryRequest : aceBagSummaryRequests) {

            RemoteNode remoteNode = this.remoteNodeRepository.getByName(aceBagSummaryRequest.getNode());
            aceBagSummaryStatuses.add(remoteNode.getAceSummaryStatus());
        }

        return aceBagSummaryStatuses;
    }
}
