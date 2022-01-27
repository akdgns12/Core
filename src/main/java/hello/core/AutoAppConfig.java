package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", 이런식으로 탐색할 패키지의 위치를 지정할 수 있다. 이 패키지를 포함해서 하위 패키지를 모두 탐색
        // 여러개 지정도 가능
//        basePackageClasses = AutoAppConfig.class, 이런식으로 해당 클래스의 패키지 하위 모두 탐색도 가능
        // 지정하지 않으면(default) @ComponentScan이 붙은 설정 정보 클래스의 패키지가 탐색 시작위치가 된다
        // -> 그래서 권장하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것. 스프링 부트도 이방법을 default로 제공
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    
}
