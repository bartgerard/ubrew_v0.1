package be.gerard.core.service.specs;

import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.ApplicationRecord_;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.PropertyGroupRecord_;
import be.gerard.core.service.model.PropertyRecord;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
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
            final String key
    ) {
        return new Specification<PropertyRecord>() {

            @Override
            public Predicate toPredicate(Root<PropertyRecord> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Subquery<PropertyRecord> sq = cq.subquery(PropertyRecord.class);
                Root<ApplicationRecord> application = sq.from(ApplicationRecord.class);
                Join<ApplicationRecord, PropertyGroupRecord> propertyGroup = application.join(ApplicationRecord_.propertyGroups, JoinType.INNER);
                Join<PropertyGroupRecord, PropertyRecord> property = propertyGroup.join(PropertyGroupRecord_.properties, JoinType.INNER);

                //sq.groupBy(property.get(PropertyRecord_.key));

                sq.select(property);

                //cq.groupBy(joinProperty.get(PropertyRecord_.key));

                return cb.in(root).value(sq);
            }

        };
    }

}
