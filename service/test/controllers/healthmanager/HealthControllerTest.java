package controllers.healthmanager;

import actors.DummyActor;
import controllers.BaseApplicationTest;
import actors.DummyHealthActor;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.common.models.util.HttpUtil;
import play.mvc.Http;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import util.ACTOR_NAMES;

/** Created by arvind on 5/12/17. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpUtil.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*", "jdk.internal.reflect.*",
        "sun.security.ssl.*", "javax.net.ssl.*", "javax.crypto.*",
        "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*"})
public class HealthControllerTest extends BaseApplicationTest {

  @Before
  public void startApp() {
    setup(ACTOR_NAMES.HEALTH_ACTOR, DummyActor.class);
  }

  @Ignore
  @Test
  public void testgetHealth() {
    Http.RequestBuilder req =
            new Http.RequestBuilder()
                    .uri("/health")
                    .method("GET");
    Result result = Helpers.route(application, req);
    Assert.assertEquals( 200, result.status());
  }

  @Test
  public void testServiceHealth() throws Exception {
    PowerMockito.mockStatic(HttpUtil.class);
    String response ="OK";
    PowerMockito.when(HttpUtil.sendPostRequest(Mockito.anyString(),Mockito.anyString(),Mockito.anyMap())).thenReturn(response);
    RequestBuilder req = new RequestBuilder().uri("/service/health").method("GET");
    Result result = Helpers.route(application, req);
    Assert.assertEquals(200, result.status());
  }

}
