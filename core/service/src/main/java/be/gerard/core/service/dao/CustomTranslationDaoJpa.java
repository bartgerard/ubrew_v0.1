package be.gerard.core.service.dao;

import be.gerard.core.service.model.QApplicationRecord;
import be.gerard.core.service.model.QTranslationGroupMetaRecord;
import be.gerard.core.service.model.QTranslationGroupRecord;
import be.gerard.core.service.model.QTranslationRecord;
import be.gerard.core.service.model.TranslationRecord;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * CustomTranslationDaoJpa
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Repository
public class CustomTranslationDaoJpa implements CustomTranslationDao {

    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager entityManager;

    protected JPAQuery<TranslationRecord> query() {
        return new JPAQuery<>(entityManager);
    }

    public Optional<String> findByAppAndPrefixAndKeyAndLanguage(
            final String app,
            final String prefix,
            final String key,
            final String language
    ) {

        QTranslationRecord translation = QTranslationRecord.translationRecord;
        QTranslationGroupRecord translationGroup = QTranslationGroupRecord.translationGroupRecord;
        QTranslationGroupMetaRecord tgm1 = QTranslationGroupMetaRecord.translationGroupMetaRecord;
        QApplicationRecord application = QApplicationRecord.applicationRecord;

        BooleanExpression filter = translation.key.like(key)
                .and(translation.language.like(language))
                .and(prefix != null ? translation.prefix.like(prefix) : translation.prefix.isNull());

        // greatest-n-per-group
        // Solution A : Separate SubQuery
        return Optional.ofNullable(
                query()
                        .select(translation.value)
                        .from(tgm1)
                        .innerJoin(tgm1.group, translationGroup)
                        .innerJoin(translationGroup.translations, translation)
                        .where(
                                filter,
                                tgm1.priority.in(
                                        JPAExpressions
                                                .select(tgm1.priority.min())
                                                .from(application)
                                                .innerJoin(application.translationGroups, tgm1)
                                                .innerJoin(tgm1.group, translationGroup)
                                                .innerJoin(translationGroup.translations, translation)
                                                .where(
                                                        application.key.like(app),
                                                        filter
                                                )
                                                .groupBy(translation.key)
                                )
                        )
                        .fetchOne()
        );

        // Solution B : Join Instead of SubQuery

        // Solution C : Left Joining With Self
    }

}
