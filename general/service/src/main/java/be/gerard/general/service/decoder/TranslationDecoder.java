package be.gerard.general.service.decoder;

import be.gerard.common.decoder.Decoder;
import be.gerard.general.interface_v1.model.Translation;
import be.gerard.general.service.model.TranslationRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 */
@Component
public class TranslationDecoder implements Decoder<TranslationRecord, Translation>{

    @Override
    public TranslationRecord decode(final Translation translation) {
        Assert.notNull(translation, "translation is invalid [NULL]");
        
        TranslationRecord translationRecord = new TranslationRecord();

        translationRecord.setId(translation.getId());
        translationRecord.setApplication(translation.getApplication());
        translationRecord.setLanguage(translation.getLanguage());
        translationRecord.setKey(translation.getKey());
        translationRecord.setValue(translation.getValue());

        return translationRecord;
    }

    @Override
    public Translation encode(final TranslationRecord translationRecord) {
        if (translationRecord == null) {
            return null;
        }
        
        Translation translation = new Translation();

        translation.setId(translationRecord.getId());
        translation.setApplication(translationRecord.getApplication());
        translation.setLanguage(translationRecord.getLanguage());
        translation.setKey(translationRecord.getKey());
        translation.setValue(translationRecord.getValue());

        return translation;
    }

}
