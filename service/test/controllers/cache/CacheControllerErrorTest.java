package controllers.cache;

import controllers.BaseApplicationTest;
import actors.DummyErrorActor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import util.ACTOR_NAMES;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*", "jdk.internal.reflect.*",
        "sun.security.ssl.*", "javax.net.ssl.*", "javax.crypto.*",
        "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*"})
public class CacheControllerErrorTest extends BaseApplicationTest {

    String MAP_NAME="mapName";
    @Before
    public void before() {
        setup(ACTOR_NAMES.CACHE_MANAGEMENT_ACTOR,DummyErrorActor.class);
    }

    @Ignore
    @Test
    public void ClearCacheErrorTest() {
        Http.RequestBuilder req =
                new Http.RequestBuilder()
                        .uri("/v1/cache/clear/"+MAP_NAME)
                        .method("DELETE");
        Result result = Helpers.route(application, req);
        Assert.assertEquals( 500, result.status());
    }
}
