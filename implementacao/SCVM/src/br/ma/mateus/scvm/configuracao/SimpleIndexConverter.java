package br.ma.mateus.scvm.configuracao;

import java.util.*;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

public class SimpleIndexConverter implements Converter {  
  
    private int index = -1;  
  
    /* (non-Javadoc) 
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String) 
     */  
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
  
        Object selectedItem = this.getSelectedItemByIndex(component, Integer.parseInt(value));  
        if (selectedItem != null)  
            return selectedItem;  
  
        return null;
    }
  
    /* (non-Javadoc) 
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object) 
     */  
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
        index++;  
        return String.valueOf(index);  
    }  
  
    /** 
     * Obtem o SelecItem de acordo com a opção selecionada pelo usuário 
     */  
    protected Object getSelectedItemByIndex(UIComponent component, int index) {  
  
        List items = this.getSelectItems(component);  
        int size = items.size();  
  
        if (index > -1  
                && size > index) {  
            return items.get(index);  
        }  
  
        return null;  
    }  
  
    protected List<Object> getSelectItems(UIComponent component) {  
  
        List<Object> items = new ArrayList<Object>();  
  
        int childCount = component.getChildCount();  
        if (childCount == 0)  
          return items;  
  
        List<UIComponent> children = component.getChildren();  
        for (UIComponent child : children) {  
            if (child instanceof UISelectItem) {  
                this.addSelectItem((UISelectItem) child, items);  
            } else if (child instanceof UISelectItems) {  
                this.addSelectItems((UISelectItems) child, items);  
            }  
        }  
  
        return items;  
    }  
  
    protected void addSelectItem(UISelectItem uiItem, List<Object> items) {  
  
        boolean isRendered = uiItem.isRendered();  
        if (!isRendered) {  
            items.add(null);  
            return;  
        }  
  
        Object value = uiItem.getValue();  
        Object item;  
  
        if (value instanceof SelectItem) {  
            item = ((SelectItem)value).getValue();  
        } else {  
            item = uiItem.getItemValue();  
/*            String itemLabel = uiItem.getItemLabel();  
            // JSF throws a null pointer exception for null values and labels,  
            // which is a serious problem at design-time.  
            item = new SelectItem(itemValue == null ? "" : itemValue,  
                    itemLabel == null ? "" : itemLabel, uiItem  
                            .getItemDescription(), uiItem.isItemDisabled());  
*/        
        }  
  
        items.add(item);  
    }  
  
    @SuppressWarnings("unchecked")  
    protected void addSelectItems(UISelectItems uiItems, List<Object> items) {  
  
        boolean isRendered = uiItems.isRendered();  
        if (!isRendered) {  
            items.add(null);  
            return;  
        }  
  
        Object value = uiItems.getValue();  
        if (value instanceof SelectItem) {  
            items.add(((SelectItem) value).getValue());  
        } else if (value instanceof Object[]) {  
            Object[] array = (Object[]) value;  
            for (int i = 0; i < array.length; i++) {  
                // TODO test - this section is untested  
                if (array[i] instanceof SelectItemGroup) {  
                    resolveAndAddItems((SelectItemGroup) array[i], items);  
                } else {  
                    items.add(((SelectItem) array[i]).getValue());  
                }  
            }  
        } else if (value instanceof Collection) {  
            Iterator<Object> iter = ((Collection<Object>) value)  
                    .iterator();  
            Object item;  
            while (iter.hasNext()) {  
                item = iter.next();  
                if (item instanceof SelectItemGroup) {  
                    resolveAndAddItems((SelectItemGroup) item, items);  
                } else {  
                    items.add(item);  
                }  
            }  
        } else if (value instanceof Map) {  
            for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) value).entrySet()) {  
                Object label = entry.getKey();  
                SelectItem item = new SelectItem(entry.getValue(),  
                        label == null ? (String) null : label.toString());  
                // TODO test - this section is untested  
                if (item instanceof SelectItemGroup) {  
                    resolveAndAddItems((SelectItemGroup) item, items);  
                } else {  
                    items.add(item);  
                }  
            }  
        }  
    }  
  
    protected void resolveAndAddItems(SelectItemGroup group, List items) {  
        for (SelectItem item : group.getSelectItems()) {  
            if (item instanceof SelectItemGroup) {  
                resolveAndAddItems((SelectItemGroup) item, items);  
            } else {  
                items.add(item);  
            }  
        }  
    }

  
} 
