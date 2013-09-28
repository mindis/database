package com.bigdata.rdf.graph.impl.bd;

import java.util.Properties;

import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;

import com.bigdata.rdf.graph.IGraphAccessor;
import com.bigdata.rdf.graph.impl.bd.BigdataGASEngine.BigdataGraphAccessor;
import com.bigdata.rdf.graph.util.AbstractGraphFixture;
import com.bigdata.rdf.sail.BigdataSail;

public class BigdataGraphFixture extends AbstractGraphFixture {

    private final BigdataSail sail;
    
    public BigdataGraphFixture(final Properties properties)
            throws SailException {

        sail = new BigdataSail();

        sail.initialize();

    }
    
    @Override
    public BigdataSail getSail() {

        return sail;
        
    }

    @Override
    public void destroy() throws Exception {

        if (sail.isOpen()) {

            sail.shutDown();

        }

        if (sail instanceof BigdataSail) {

            ((BigdataSail) sail).__tearDownUnitTest();

        }

    }

    @Override
    public BigdataGASEngine newGASEngine(final int nthreads) {

        return new BigdataGASEngine(sail, nthreads);
        
    }

    @Override
    public IGraphAccessor newGraphAccessor(final SailConnection ignored) {

        return new BigdataGraphAccessor(sail.getDatabase()
                .getIndexManager());

    }

}