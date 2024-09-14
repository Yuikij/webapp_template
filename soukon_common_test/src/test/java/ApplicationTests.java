import com.soukon.Application;

import com.soukon.bean.DatabaseTestBean;
import com.soukon.mapper.DataBaseTestMapper;
import com.soukon.redis.service.RedisService;
import com.soukon.service.DatabaseTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DataBaseTestMapper dataBaseTestMapper;

    @Autowired
    private DatabaseTestService databaseTestService;

    @Test
    public void testRedis() {
        System.out.println(redisService.getCacheObject("123").toString());
    }

    @Test
    public void testDatabase() {
        System.out.println(dataBaseTestMapper.selectList(null));
    }
    @Test
    public void testDatabase2() {
        List<DatabaseTestBean> list = databaseTestService.list();
        System.out.println(list);
    }
}
