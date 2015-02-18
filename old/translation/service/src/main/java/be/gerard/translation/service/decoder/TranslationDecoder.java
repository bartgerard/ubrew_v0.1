package be.gerard.translation.service.decoder;

import be.gerard.translation.service.model.TranslationRecord;
import be.gerard.translation.interface_v1.model.Translation;
import org.springframework.stereotype.Component;

/**
 *
 * @author bartgerard
 */
@Component
public class TranslationDecoder {

    public TranslationRecord decode(final Translation translation) {
        TranslationRecord translationRecord = new TranslationRecord();
        
        translationRecord.setId(translation.getId());
        translationRecord.setApplication(translation.getApplication());
        translationRecord.setLanguage(translation.getLanguage());
        translationRecord.setKey(translation.getKey());
        translationRecord.setValue(translation.getValue());
        translationRecord.setChangedBy(translation.getChangedBy());
        
        return translationRecord;
    }

    public Translation encode(final TranslationRecord translationRecord) {
        Translation translation = new Translation();
        
        translation.setId(translationRecord.getId());
        translation.setApplication(translationRecord.getApplication());
        translation.setLanguage(translationRecord.getLanguage());
        translation.setKey(translationRecord.getKey());
        translation.setValue(translationRecord.getValue());
        translation.setChangedBy(translationRecord.getChangedBy());
        
        return translation;
    }
    
}
