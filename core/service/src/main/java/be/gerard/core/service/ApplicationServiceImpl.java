package be.gerard.core.service;

import be.gerard.core.interface_v1.ApplicationService;
import be.gerard.core.interface_v1.model.Application;
import be.gerard.core.service.dao.ApplicationDao;
import be.gerard.core.service.dao.ApplicationInstanceDao;
import be.gerard.core.service.merger.ApplicationMerger;
import be.gerard.core.service.model.ApplicationInstanceRecord;
import be.gerard.core.service.model.ApplicationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

/**
 * ApplicationServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 16:56
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ApplicationMerger applicationMerger;

    @Override
    public Application save(@Validated Application application) {
        // Retrieve applicationRecord
        ApplicationRecord applicationRecord = applicationDao.findByKey(application.getKey());

        if (applicationRecord == null) {
            applicationRecord = new ApplicationRecord(application.getKey());
        }

        applicationMerger.merge(applicationRecord, application);

        return conversionService.convert(applicationRecord, Application.class);
    }

    @Override
    public void instantiate(String appKey, String reference, String password, Collection<String> ips, Collection<String> macs) {
        ApplicationInstanceRecord applicationInstanceRecord = applicationInstanceDao.findByApplicationAndReference(appKey, reference);

        if (applicationInstanceRecord == null) {
            ApplicationRecord applicationRecord = applicationDao.findByKey(appKey);

            Assert.notNull(applicationRecord, String.format("application does not exist [appKey=%s]", appKey));

            // TODO Validate reference + appId does not exist

            applicationInstanceRecord = new ApplicationInstanceRecord(applicationRecord, reference, password);
        }

        applicationInstanceRecord.clearAllowedIps();
        applicationInstanceRecord.clearAllowedMacs();

        for (String ip : ips) {
            applicationInstanceRecord.addIp(ip);
        }

        for (String mac : macs) {
            applicationInstanceRecord.addMac(mac);
        }

        applicationInstanceDao.saveAndFlush(applicationInstanceRecord);

        //return conversionService.convert(applicationInstanceRecord.getApplication(), Application.class);
    }

    @Override
    public void delete(String appKey, String reference) {
        ApplicationInstanceRecord applicationInstanceRecord = applicationInstanceDao.findByApplicationAndReference(appKey, reference);

        // TODO VALIDATION

        applicationInstanceDao.delete(applicationInstanceRecord);
    }

}
