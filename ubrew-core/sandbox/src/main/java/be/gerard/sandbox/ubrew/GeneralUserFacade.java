package be.gerard.sandbox.ubrew;

import be.gerard.sandbox.ubrew.model.GeneralUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bartgerard
 */
@Stateless
public class GeneralUserFacade extends AbstractFacade<GeneralUser> {
    @PersistenceContext(unitName = "be.gerard_sandbox_war_0.0.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeneralUserFacade() {
        super(GeneralUser.class);
    }

}
