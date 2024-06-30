import com.soukon.Application;
import com.soukon.mapper.DataBaseTestMapper;
import com.soukon.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DataBaseTestMapper dataBaseTestMapper;

    @Test
    public void testRedis() {
        System.out.println(redisService.getCacheObject("123").toString());
    }

    @Test
    public void testDatabase() {
        System.out.println(dataBaseTestMapper.selectList(null));
    }
}
