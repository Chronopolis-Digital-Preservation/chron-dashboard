package org.chronopolis.controller.ingest.bagstatus;

import org.chronopolis.model.IngestBagStatusType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class IngestBagStatusTypeController {

    @RequestMapping(value = "/api/v1/getIngestBagStatusList.json")
    @ResponseBody
    public Collection<IngestBagStatusTypeResponse> getBagStatusList() {

        Collection<IngestBagStatusTypeResponse> bagStatuseTypeResponseList = new ArrayList<>();

        for (IngestBagStatusType ingestBagStatusType : IngestBagStatusType.values()) {

            IngestBagStatusTypeResponse ingestBagStatusTypeResponse = new IngestBagStatusTypeResponse();
            ingestBagStatusTypeResponse.setStatus(ingestBagStatusType.name());

            bagStatuseTypeResponseList.add(ingestBagStatusTypeResponse);
        }

        return bagStatuseTypeResponseList;
    }
}
