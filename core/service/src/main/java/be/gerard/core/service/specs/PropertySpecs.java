package be.gerard.core.service.specs;

import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.ApplicationRecord_;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.PropertyGroupRecord_;
import be.gerard.core.service.model.PropertyRecord;
import be.gerard.core.service.model.PropertyRecord_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * PropertySpecs
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class PropertySpecs {

    private PropertySpecs() {
    }

    public static Specification<PropertyRecord> findByApplication(
            final String appKey
    ) {
        return (root, cq, cb) -> {
            Subquery<PropertyRecord> sq = cq.subquery(PropertyRecord.class);
            Root<ApplicationRecord> application = sq.from(ApplicationRecord.class);
            Join<ApplicationRecord, PropertyGroupRecord> propertyGroup = application.join(ApplicationRecord_.propertyGroups, JoinType.INNER);
            Join<PropertyGroupRecord, PropertyRecord> property = propertyGroup.join(PropertyGroupRecord_.properties, JoinType.INNER);

            sq.select(property).where(
                    cb.and(
                            cb.equal(application.get(ApplicationRecord_.key), appKey)
                    )
            );

            // TODO

            sq.groupBy(property.get(PropertyRecord_.key));

            return cb.in(root).value(sq);
        };
    }

}
