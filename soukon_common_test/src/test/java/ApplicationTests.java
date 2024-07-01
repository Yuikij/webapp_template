//import com.soukon.Application;
//import com.soukon.auth.domain.UserDTO;
//import com.soukon.auth.mapper.UserMapper;
//import com.soukon.auth.service.UserService;
//import com.soukon.bean.DatabaseTestBean;
//import com.soukon.mapper.DataBaseTestMapper;
//import com.soukon.redis.service.RedisService;
//import com.soukon.service.DatabaseTestService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest(classes = Application.class)
//public class ApplicationTests {
//
//    @Autowired
//    private RedisService redisService;
//
//    @Autowired
//    private DataBaseTestMapper dataBaseTestMapper;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private DatabaseTestService databaseTestService;
//
//    @Test
//    public void testRedis() {
//        System.out.println(redisService.getCacheObject("123").toString());
//    }
//
//    @Test
//    public void testDatabase() {
//        System.out.println(dataBaseTestMapper.selectList(null));
//    }
//    @Test
//    public void testDatabase2() {
//        List<DatabaseTestBean> list = databaseTestService.list();
//        System.out.println(list);
//    }
//
//    @Test
//    public void testAuth() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEnabled(true);
//        userDTO.setUsername("chenyu");
//        userDTO.setPassword("chenyu");
//        System.out.println(userService.register(userDTO));
//    }
//}
