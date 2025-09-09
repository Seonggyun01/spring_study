package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    public void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtopyBean.class);
        System.out.println("find prototype1");
        ProtopyBean protopyBean1 = ac.getBean(ProtopyBean.class);

        System.out.println("find prototype2");
        ProtopyBean protopyBean2 = ac.getBean(ProtopyBean.class);

        System.out.println("protopyBean1 = " + protopyBean1);
        System.out.println("protopyBean2 = " + protopyBean2);

        assertThat(protopyBean1).isNotSameAs(protopyBean2);
        ac.close();
    }

    @Scope("prototype")
    static class ProtopyBean{

        @PostConstruct
        public void init(){
            System.out.println("ProtopyBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("ProtopyBean.destroy");
        }
    }
}
