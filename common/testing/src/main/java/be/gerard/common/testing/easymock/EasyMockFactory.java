package be.gerard.common.testing.easymock;

import org.easymock.EasyMock;
import org.springframework.beans.factory.FactoryBean;

/**
 * http://narkisr.com/blog/2008/2647754885089732945
 *
 * @author bartgerard
 */
public class EasyMockFactory implements FactoryBean<Object> {

    private Class<?> type;

    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public Object getObject() throws Exception {
        return EasyMock.createMock(type);
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
