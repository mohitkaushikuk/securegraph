package org.securegraph.performancetest;

import org.securegraph.Authorizations;
import org.securegraph.Graph;
import org.securegraph.accumulo.AccumuloAuthorizations;

public class SearchAndRetrievePerformance extends PerformanceTestBase {
    public static void main(String[] args) throws InterruptedException {
        new SearchAndRetrievePerformance().testSearch();
    }

    public void testSearch() {
        time("total", new Runnable() {
            @Override
            public void run() {
                final Authorizations authorizations = new AccumuloAuthorizations();
                final Graph graph = createGraph();
                time("insert", new Runnable() {
                    @Override
                    public void run() {
                        createVertices(graph, 3000, authorizations);
                    }
                });

                time("query", new Runnable() {
                    @Override
                    public void run() {
                        count(graph.query(authorizations)
                                .has("title", "night")
                                .vertices());
                        count(graph.query(authorizations)
                                .has("title", "city")
                                .vertices());
                        count(graph.query(authorizations)
                                .has("title", "wild")
                                .vertices());
                    }
                });
            }
        });
    }
}
