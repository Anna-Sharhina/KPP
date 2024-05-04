package mybeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class DataSheetGraphInfo extends SimpleBeanInfo {

    private PropertyDescriptor[] propertyDescriptors;
    public DataSheetGraphInfo() {
        try {
            propertyDescriptors = new PropertyDescriptor[]{
                    new PropertyDescriptor("color",DataSheetGraph.class),
                    new PropertyDescriptor("filled",DataSheetGraph.class),
                    new PropertyDescriptor("deltaX",DataSheetGraph.class),
                    new PropertyDescriptor("deltaY",DataSheetGraph.class)
            };
        } catch (IntrospectionException e) {}
    }
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return propertyDescriptors;
    }



}