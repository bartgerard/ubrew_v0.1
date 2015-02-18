package be.gerard.common.web;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bartgerard
 * @param <T>
 */
public abstract class GeneralListBean<T extends Serializable> extends GeneralBean<T> implements Serializable {

    private List<T> items = null;

    private List<T> selection = null;

    public void create() {
        persist(PersistAction.CREATE, getI18n().get("Suc6"));// ResourceBundle.getBundle("/Bundle").getString("GeneralUserDeleted")
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, getI18n().get("Suc6"));
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, getI18n().get("Suc6"));
        if (!isValidationFailed()) {
            if (selection != null && !selection.isEmpty()) {
                selection.remove(getSelected());
            }
            setSelected(null); // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public List<T> getItems() {
        if (items == null) {
            items = reloadItems();
        }

        return items;
    }
    
    protected void clearItems() {
        this.items = null;
    }

    public List<T> getSelection() {
        return selection;
    }

    public void setSelection(List<T> selection) {
        this.selection = selection;
    }

    protected abstract List<T> reloadItems();

    public abstract List<T> getItemsAvailableSelectMany();

    public abstract List<T> getItemsAvailableSelectOne();

}
