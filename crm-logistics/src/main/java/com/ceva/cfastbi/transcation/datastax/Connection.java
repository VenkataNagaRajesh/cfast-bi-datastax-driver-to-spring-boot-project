package com.ceva.cfastbi.transcation.datastax;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.mapping.MappingManager;

/**
 * Connection class to buid the data stax connection.
 * 
 * @author rajesh
 *
 */

public class Connection {
  private Cluster cluster;
  private Session session;
  private MappingManager manager;
  @Value("${cassandra.username")
  private String username;
  @Value("${cassandra.password")
  private String password;
  private String contactPoints;
  @Value("${cassandra.keyspace-name}")
  private String keySpace;


  public Cluster getCluster() {
    return cluster;
  }

  public void setCluster(Cluster cluster) {
    this.cluster = cluster;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public MappingManager getManager() {
    return manager;
  }

  public void setManager(MappingManager manager) {
    this.manager = manager;
  }


  /**
   * intialion.
   */
  @PostConstruct
  private void init() {
    // AuthProvider authprovider = new PlainTextAuthProvider(username, password);
    SocketOptions socketOptions =
        new SocketOptions().setConnectTimeoutMillis(500000000).setReadTimeoutMillis(10000000);
    cluster = Cluster.builder().withClusterName("Test Cluster").addContactPoints("127.0.0.1")
        .withSocketOptions(socketOptions).withoutJMXReporting().build();
    session = cluster.connect("test1");
    MappingManager manager = new MappingManager(session);
    this.setManager(manager);
  }
}
