package be.gerard.core.service.builder;

import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.service.model.TranslationRecord;

/**
 * TranslationBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class TranslationBuilder extends ChildBuilder<TranslationRecord, TranslationGroupBuilder> {

    public TranslationBuilder(TranslationRecord record, BuilderContext builderContext, TranslationGroupBuilder parent) {
        super(record, builderContext, parent);
    }

    public TranslationBuilder value(String value) {
        getRecord().setValue(value);
        return this;
    }

    public TranslationBuilder type(TranslationType type) {
        getRecord().setType(type);
        return this;
    }

    @Override
    public void save() {

    }

}
