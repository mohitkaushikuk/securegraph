package org.securegraph.mutation;

import org.securegraph.Element;
import org.securegraph.Property;
import org.securegraph.Visibility;
import org.securegraph.property.MutableProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExistingElementMutationImpl<T extends Element> implements ElementMutation<T>, ExistingElementMutation<T> {
    private final List<Property> properties = new ArrayList<Property>();
    private Visibility newElementVisibility;
    private final List<AlterPropertyVisibility> alterPropertyVisibilities = new ArrayList<AlterPropertyVisibility>();
    private final List<AlterPropertyMetadata> alterPropertyMetadatas = new ArrayList<AlterPropertyMetadata>();
    private final T element;

    public ExistingElementMutationImpl(T element) {
        this.element = element;
    }

    public abstract T save();

    public ElementMutation<T> setProperty(String name, Object value, Visibility visibility) {
        return setProperty(name, value, new HashMap<String, Object>(), visibility);
    }

    public ElementMutation<T> setProperty(String name, Object value, Map<String, Object> metadata, Visibility visibility) {
        return addPropertyValue(DEFAULT_KEY, name, value, metadata, visibility);
    }

    public ElementMutation<T> addPropertyValue(String key, String name, Object value, Visibility visibility) {
        return addPropertyValue(key, name, value, new HashMap<String, Object>(), visibility);
    }

    public ElementMutation<T> addPropertyValue(String key, String name, Object value, Map<String, Object> metadata, Visibility visibility) {
        properties.add(new MutableProperty(key, name, value, metadata, visibility));
        return this;
    }

    public Iterable<Property> getProperties() {
        return properties;
    }

    @Override
    public ExistingElementMutation<T> alterPropertyVisibility(Property property, Visibility visibility) {
        this.alterPropertyVisibilities.add(new AlterPropertyVisibility(property.getKey(), property.getName(), property.getVisibility(), visibility));
        return this;
    }

    @Override
    public ExistingElementMutation<T> alterPropertyVisibility(String name, Visibility visibility) {
        return alterPropertyVisibility(DEFAULT_KEY, name, visibility);
    }

    @Override
    public ExistingElementMutation<T> alterPropertyVisibility(String key, String name, Visibility visibility) {
        this.alterPropertyVisibilities.add(new AlterPropertyVisibility(key, name, null, visibility));
        return this;
    }

    @Override
    public ExistingElementMutation<T> alterElementVisibility(Visibility visibility) {
        this.newElementVisibility = visibility;
        return this;
    }

    @Override
    public ExistingElementMutation<T> alterPropertyMetadata(Property property, String metadataName, Object newValue) {
        this.alterPropertyMetadatas.add(new AlterPropertyMetadata(property.getKey(), property.getName(), property.getVisibility(), metadataName, newValue));
        return this;
    }

    @Override
    public ExistingElementMutation<T> alterPropertyMetadata(String propertyName, String metadataName, Object newValue) {
        return alterPropertyMetadata(DEFAULT_KEY, propertyName, metadataName, newValue);
    }

    @Override
    public ExistingElementMutation<T> alterPropertyMetadata(String propertyKey, String propertyName, String metadataName, Object newValue) {
        this.alterPropertyMetadatas.add(new AlterPropertyMetadata(propertyKey, propertyName, null, metadataName, newValue));
        return this;
    }

    @Override
    public T getElement() {
        return element;
    }

    public Visibility getNewElementVisibility() {
        return newElementVisibility;
    }

    public List<AlterPropertyVisibility> getAlterPropertyVisibilities() {
        return alterPropertyVisibilities;
    }

    public List<AlterPropertyMetadata> getAlterPropertyMetadatas() {
        return alterPropertyMetadatas;
    }
}
