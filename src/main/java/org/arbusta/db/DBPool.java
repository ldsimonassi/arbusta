package org.arbusta.db;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;

public class DBPool {
    private static ObjectPool pool = null;

    private static ObjectPool createConnectionPool(String host, String port, String schema, String user, String password) {
        PoolableObjectFactory mySqlPoolableObjectFactory = new MySqlPoolableObjectFactory(host,
             Integer.parseInt(port), schema, user, password);
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxActive = 10;
        config.testOnBorrow = true;
        config.testWhileIdle = true;
        config.timeBetweenEvictionRunsMillis = 10000;
        config.minEvictableIdleTimeMillis = 60000;

        GenericObjectPoolFactory genericObjectPoolFactory = new GenericObjectPoolFactory(mySqlPoolableObjectFactory, config);
        ObjectPool pool = genericObjectPoolFactory.createPool();
        return pool;
   }
    
	public static ObjectPool getPool() {
		if(pool == null) 
			pool = createConnectionPool("localhost", "3306", "arbusta", "root", "");
		return pool;
	}
}
