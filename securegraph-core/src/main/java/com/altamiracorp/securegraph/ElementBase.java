package com.altamiracorp.securegraph;

import com.altamiracorp.securegraph.property.PropertyValue;
import com.altamiracorp.securegraph.util.ConvertingIterable;
import com.altamiracorp.securegraph.util.FilterIterable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class ElementBase implements Element {
    private final Graph graph;
    private final Object id;
    private final Visibility visibility;

    // The key to this map is the property id + property name
    private final Map<Object, Property> properties;

    protected ElementBase(Graph graph, Object id, Visibility visibility, Property[] properties) {
        this.graph = graph;
        this.id = id;
        this.visibility = visibility;
        this.properties = new HashMap<Object, Property>();
        setPropertiesInternal(properties);
    }

    @Override
    public Iterable<Object> getPropertyValues(final String name) {
        return new ConvertingIterable<Property, Object>(getProperties(name)) {
            @Override
            protected Object convert(Property p) {
                return p.getValue();
            }
        };
    }

    @Override
    public Object getPropertyValue(String name, int index) {
        Iterator<Object> values = getPropertyValues(name).iterator();
        while (values.hasNext() && index >= 0) {
            if (index == 0) {
                return values.next();
            }
            index--;
        }
        return null;
    }

    @Override
    public Object getId() {
        return this.id;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public Iterable<Property> getProperties() {
        return this.properties.values();
    }

    @Override
    public Iterable<Property> getProperties(final String name) {
        return new FilterIterable<Property>(getProperties()) {
            @Override
            protected boolean isIncluded(Property src, Property dest) {
                return dest.getName().equals(name);
            }
        };
    }

    @Override
    public abstract void setProperties(Property... properties);

    // this method differs setProperties in that it only updates the in memory representation of the properties
    protected void setPropertiesInternal(Property[] properties) {
        for (Property property : properties) {
            if (property.getId() == null) {
                throw new IllegalArgumentException("id is required for property");
            }
            Object propertyValue = property.getValue();
            if (propertyValue instanceof PropertyValue && !((PropertyValue) propertyValue).isStore()) {
                continue;
            }
            this.properties.put(property.getId() + property.getName(), property);
        }
    }

    protected Property removePropertyInternal(Object propertyId, String name) {
        String key = propertyId + name;
        Property property = this.properties.get(key);
        this.properties.remove(key);
        return property;
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        if (this instanceof Edge) {
            return getId() + ":[" + ((Edge) this).getVertexId(Direction.OUT) + "->" + ((Edge) this).getVertexId(Direction.IN) + "]";
        }
        return getId().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Element) {
            Element objElem = (Element) obj;
            return getId().equals(objElem.getId());
        }
        return super.equals(obj);
    }
}
