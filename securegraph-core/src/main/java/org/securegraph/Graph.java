package org.securegraph;

import org.securegraph.id.IdGenerator;
import org.securegraph.query.GraphQuery;

import java.util.List;
import java.util.Set;

public interface Graph {
    /**
     * Adds a vertex to the graph. The id of the new vertex will be generated using an org.securegraph.id.IdGenerator.
     *
     * @param visibility     The visibility to assign to the new vertex.
     * @param authorizations The authorizations required to add and retrieve the new vertex.
     * @return The newly added vertex.
     */
    Vertex addVertex(Visibility visibility, Authorizations authorizations);

    /**
     * Adds a vertex to the graph.
     *
     * @param vertexId       The id to assign the new vertex.
     * @param visibility     The visibility to assign to the new vertex.
     * @param authorizations The authorizations required to add and retrieve the new vertex.
     * @return The newly added vertex.
     */
    Vertex addVertex(Object vertexId, Visibility visibility, Authorizations authorizations);

    /**
     * Prepare a vertex to be added to the graph. This method provides a way to build up a vertex with it's properties to be inserted
     * with a single operation. The id of the new vertex will be generated using an org.securegraph.id.IdGenerator.
     *
     * @param visibility     The visibility to assign to the new vertex.
     * @param authorizations The authorizations required to add and retrieve the new vertex.
     * @return The vertex builder.
     */
    VertexBuilder prepareVertex(Visibility visibility, Authorizations authorizations);

    /**
     * Prepare a vertex to be added to the graph. This method provides a way to build up a vertex with it's properties to be inserted
     * with a single operation.
     *
     * @param vertexId       The id to assign the new vertex.
     * @param visibility     The visibility to assign to the new vertex.
     * @param authorizations The authorizations required to add and retrieve the new vertex.
     * @return The vertex builder.
     */
    VertexBuilder prepareVertex(Object vertexId, Visibility visibility, Authorizations authorizations);

    /**
     * Get a vertex from the graph.
     *
     * @param vertexId       The vertex id to retrieve from the graph.
     * @param authorizations The authorizations required to load the vertex.
     * @return The vertex if successful. null if the vertex is not found or the required authorizations were not provided.
     */
    Vertex getVertex(Object vertexId, Authorizations authorizations);

    /**
     * Gets all vertices on the graph.
     *
     * @param authorizations The authorizations required to load the vertex.
     * @return An iterable of all the vertices.
     */
    Iterable<Vertex> getVertices(Authorizations authorizations);

    /**
     * Gets all vertices matching the given ids on the graph. The order of
     * the returned vertices is not guaranteed {@link org.securegraph.Graph#getVerticesInOrder(Iterable, Authorizations)}.
     * Vertices are not kept in memory during the iteration.
     *
     * @param ids            The ids of the vertices to get.
     * @param authorizations The authorizations required to load the vertex.
     * @return An iterable of all the vertices.
     */
    Iterable<Vertex> getVertices(Iterable<Object> ids, Authorizations authorizations);

    /**
     * Gets all vertices matching the given ids on the graph. This method is similar to
     * {@link org.securegraph.Graph#getVertices(Iterable, Authorizations)}
     * but returns the vertices in the order that you passed in the ids. This requires loading
     * all the vertices in memory to sort them.
     *
     * @param ids            The ids of the vertices to get.
     * @param authorizations The authorizations required to load the vertex.
     * @return An iterable of all the vertices.
     */
    List<Vertex> getVerticesInOrder(Iterable<Object> ids, Authorizations authorizations);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex         The vertex to remove.
     * @param authorizations The authorizations required to remove the vertex.
     */
    void removeVertex(Vertex vertex, Authorizations authorizations);

    /**
     * Adds an edge between two vertices. The id of the new vertex will be generated using an org.securegraph.id.IdGenerator.
     *
     * @param outVertex      The source vertex. The "out" side of the edge.
     * @param inVertex       The destination vertex. The "in" side of the edge.
     * @param label          The label to assign to the edge. eg knows, works at, etc.
     * @param visibility     The visibility to assign to the new edge.
     * @param authorizations The authorizations required to add and retrieve the new edge.
     * @return The newly created edge.
     */
    Edge addEdge(Vertex outVertex, Vertex inVertex, String label, Visibility visibility, Authorizations authorizations);

    /**
     * Adds an edge between two vertices.
     *
     * @param edgeId         The id to assign the new edge.
     * @param outVertex      The source vertex. The "out" side of the edge.
     * @param inVertex       The destination vertex. The "in" side of the edge.
     * @param label          The label to assign to the edge. eg knows, works at, etc.
     * @param visibility     The visibility to assign to the new edge.
     * @param authorizations The authorizations required to add and retrieve the new edge.
     * @return The newly created edge.
     */
    Edge addEdge(Object edgeId, Vertex outVertex, Vertex inVertex, String label, Visibility visibility, Authorizations authorizations);

    /**
     * Prepare an edge to be added to the graph. This method provides a way to build up an edge with it's properties to be inserted
     * with a single operation. The id of the new edge will be generated using an org.securegraph.id.IdGenerator.
     *
     * @param outVertex      The source vertex. The "out" side of the edge.
     * @param inVertex       The destination vertex. The "in" side of the edge.
     * @param label          The label to assign to the edge. eg knows, works at, etc.
     * @param visibility     The visibility to assign to the new edge.
     * @param authorizations The authorizations required to add and retrieve the new edge.
     * @return The edge builder.
     */
    EdgeBuilder prepareEdge(Vertex outVertex, Vertex inVertex, String label, Visibility visibility, Authorizations authorizations);

    /**
     * Prepare an edge to be added to the graph. This method provides a way to build up an edge with it's properties to be inserted
     * with a single operation.
     *
     * @param edgeId         The id to assign the new edge.
     * @param outVertex      The source vertex. The "out" side of the edge.
     * @param inVertex       The destination vertex. The "in" side of the edge.
     * @param label          The label to assign to the edge. eg knows, works at, etc.
     * @param visibility     The visibility to assign to the new edge.
     * @param authorizations The authorizations required to add and retrieve the new edge.
     * @return The edge builder.
     */
    EdgeBuilder prepareEdge(Object edgeId, Vertex outVertex, Vertex inVertex, String label, Visibility visibility, Authorizations authorizations);

    /**
     * Get an edge from the graph.
     *
     * @param edgeId         The edge id to retrieve from the graph.
     * @param authorizations The authorizations required to load the edge.
     * @return The edge if successful. null if the edge is not found or the required authorizations were not provided.
     */
    Edge getEdge(Object edgeId, Authorizations authorizations);

    /**
     * Gets all edges on the graph.
     *
     * @param authorizations The authorizations required to load the edge.
     * @return An iterable of all the edges.
     */
    Iterable<Edge> getEdges(Authorizations authorizations);

    /**
     * Gets all edges on the graph matching the given ids.
     *
     * @param ids            The ids of the edges to get.
     * @param authorizations The authorizations required to load the edge.
     * @return An iterable of all the edges.
     */
    Iterable<Edge> getEdges(Iterable<Object> ids, Authorizations authorizations);

    /**
     * Given a list of vertex ids, find all the edge ids that connect them.
     *
     * @param vertexIds      The list of vertex ids.
     * @param authorizations The authorizations required to load the edges.
     * @return An iterable of all the edge ids between any two vertices.
     */
    Iterable<Object> findRelatedEdges(Iterable<Object> vertexIds, Authorizations authorizations);

    /**
     * Removes an edge from the graph.
     *
     * @param edge           The edge to remove.
     * @param authorizations The authorizations required to remove the edge.
     */
    void removeEdge(Edge edge, Authorizations authorizations);

    /**
     * Removes an edge from the graph.
     *
     * @param edgeId         The edge id of the vertex to remove from the graph.
     * @param authorizations The authorizations required to remove the edge.
     */
    void removeEdge(String edgeId, Authorizations authorizations);

    /**
     * Creates a query builder object used to query the graph.
     *
     * @param queryString    The string to search for in the text of an element. This will search all fields for the given text.
     * @param authorizations The authorizations required to load the elements.
     * @return A query builder object.
     */
    GraphQuery query(String queryString, Authorizations authorizations);

    /**
     * Creates a query builder object used to query the graph.
     *
     * @param authorizations The authorizations required to load the elements.
     * @return A query builder object.
     */
    GraphQuery query(Authorizations authorizations);

    /**
     * Flushes any pending mutations to the graph.
     */
    void flush();

    /**
     * Cleans up or disconnects from the underlying storage.
     */
    void shutdown();

    /**
     * Finds all paths between two vertices.
     *
     * @param sourceVertex   The source vertex to start the search from.
     * @param destVertex     The destination vertex to get to.
     * @param maxHops        The maximum number of hops to make before giving up.
     * @param authorizations The authorizations required to load all edges and vertices.
     * @return An Iterable of lists of paths.
     */
    Iterable<Path> findPaths(Vertex sourceVertex, Vertex destVertex, int maxHops, Authorizations authorizations);

    /**
     * Gets the id generator used by this graph to create ids.
     *
     * @return the id generator.
     */
    IdGenerator getIdGenerator();

    /**
     * Given an authorization is the visibility object valid.
     *
     * @param visibility     The visibility you want to check.
     * @param authorizations The given authorizations.
     * @return true if the visibility is valid given an authorization, else return false.
     */
    boolean isVisibilityValid(Visibility visibility, Authorizations authorizations);

    /**
     * Reindex all vertices and edges.
     *
     * @param authorizations authorizations used to query for the data to reindex.
     */
    void reindex(Authorizations authorizations);

    /**
     * Creates a defines property builder. This is typically used by the indexer to give it hints on how it should index a property.
     *
     * @param propertyName The name of the property to define.
     */
    DefinePropertyBuilder defineProperty(String propertyName);

    /**
     * Determine if field boost is support.
     */
    boolean isFieldBoostSupported();
}
