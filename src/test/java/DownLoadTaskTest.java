import com.xsg.DownLoadApplication;
import com.xsg.service.DownLoadService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: 测试
 */
@Slf4j
@SpringBootTest(classes = DownLoadApplication.class)
public class DownLoadTaskTest{

    @Setter(onMethod_ ={@Autowired})
    private DownLoadService downLoadService;

    @Test
    public void testTask(){
        downLoadService.createDownloadSingleTask("1234");
    }
}
