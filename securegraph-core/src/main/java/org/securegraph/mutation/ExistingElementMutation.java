package org.securegraph.mutation;

import org.securegraph.Element;
import org.securegraph.Property;
import org.securegraph.Visibility;

public interface ExistingElementMutation<T extends Element> extends ElementMutation<T> {
    /**
     * Alters the visibility of a property.
     *
     * @param property   The property to mutate.
     * @param visibility The new visibility.
     */
    ExistingElementMutation<T> alterPropertyVisibility(Property property, Visibility visibility);

    /**
     * Alters the visibility of a property.
     *
     * @param key        The key of a multivalued property.
     * @param name       The name of the property to alter the visibility of.
     * @param visibility The new visibility.
     */
    ExistingElementMutation<T> alterPropertyVisibility(String key, String name, Visibility visibility);

    /**
     * Alters the visibility of a property.
     *
     * @param name       The name of the property to alter the visibility of.
     * @param visibility The new visibility.
     */
    ExistingElementMutation<T> alterPropertyVisibility(String name, Visibility visibility);

    /**
     * Alters the visibility of the element (vertex or edge).
     *
     * @param visibility The new visibility.
     */
    ExistingElementMutation<T> alterElementVisibility(Visibility visibility);

    /**
     * Alters a property metadata value on a property.
     *
     * @param property     The property to mutate.
     * @param metadataName The name of the metadata.
     * @param newValue     The new value.
     */
    ExistingElementMutation<T> alterPropertyMetadata(Property property, String metadataName, Object newValue);

    /**
     * Alters a property metadata value on a property.
     *
     * @param propertyKey  The key of a multivalued property.
     * @param propertyName The name of the property.
     * @param metadataName The name of the metadata.
     * @param newValue     The new value.
     */
    ExistingElementMutation<T> alterPropertyMetadata(String propertyKey, String propertyName, String metadataName, Object newValue);

    /**
     * Alters a property metadata value on a property.
     *
     * @param propertyName The name of the property.
     * @param metadataName The name of the metadata.
     * @param newValue     The new value.
     */
    ExistingElementMutation<T> alterPropertyMetadata(String propertyName, String metadataName, Object newValue);

    /**
     * Gets the element this mutation is affecting.
     *
     * @return The element.
     */
    T getElement();
}
