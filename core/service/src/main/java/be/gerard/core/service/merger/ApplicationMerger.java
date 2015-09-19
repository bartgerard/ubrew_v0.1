package be.gerard.core.service.merger;

import be.gerard.common.merger.MergeContext;
import be.gerard.common.merger.Merger;
import be.gerard.core.interface_v1.model.Application;
import be.gerard.core.service.model.ApplicationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

/**
 * UserMerger
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Component
public class ApplicationMerger implements Merger<ApplicationRecord, Application, MergeContext> {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PropertyMerger propertyMerger;

    @Override
    public void merge(ApplicationRecord applicationRecord, Application application, MergeContext context) {
        //propertyMerger.mergeCollection(applicationRecord.getProperties(), application.getProperties(), context);
    }

}
