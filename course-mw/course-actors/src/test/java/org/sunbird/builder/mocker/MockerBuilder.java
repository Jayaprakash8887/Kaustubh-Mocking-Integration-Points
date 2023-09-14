package org.sunbird.builder.mocker;

import org.powermock.api.mockito.PowerMockito;
import org.sunbird.cassandra.CassandraOperation;
import org.sunbird.common.inf.ElasticSearchService;
import org.sunbird.userorg.UserOrgService;

public class MockerBuilder {

  public static MockersGroup getFreshMockerGroup() {
    return new MockersGroup();
  }

  public static class MockersGroup {
    Mocker<CassandraOperation> cassandraMocker;
    Mocker<ElasticSearchService> esMocker;
    Mocker<UserOrgService> userOrgMocker;

    public MockersGroup withCassandraMock(Mocker<CassandraOperation> mocker) {
      cassandraMocker = mocker;
      return this;
    }

    public Mocker<CassandraOperation> getCassandraMocker() {
      return cassandraMocker;
    }

    public CassandraOperation getCassandraMockerService() {
      return cassandraMocker.getServiceMock();
    }

    public MockersGroup withESMock(Mocker<ElasticSearchService> mocker) {
      esMocker = mocker;
      return this;
    }

    public Mocker<ElasticSearchService> getESMocker() {
      return esMocker;
    }

    public ElasticSearchService getESMockerService() {
      return esMocker.getServiceMock();
    }

    public MockersGroup withUserOrgMock(Mocker<UserOrgService> mocker) {
      userOrgMocker = mocker;
      return this;
    }

    public MockersGroup andStaticMock(Class clazz) {
      PowerMockito.mockStatic(clazz);
      return this;
    }

    public Mocker<UserOrgService> getUserOrgMocker() {
      return userOrgMocker;
    }

    public UserOrgService getUserOrgMockerService() {
      return userOrgMocker.getServiceMock();
    }
  }
}
