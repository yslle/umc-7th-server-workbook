package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.mission.MissionQueryService;
import umc.spring.service.store.StoreQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
/*
			// 실습
			StoreQueryService storeService = context.getBean(StoreQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float rating = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + rating);

			storeService.findStoresByNameAndRating(name, rating)
					.forEach(System.out::println);
*/

			// 내가 진행 중인, 진행한 미션 조회
			MissionQueryService missionQueryService = context.getBean(MissionQueryService.class);

			Long memberId = 1L;
//			MissionStatus missionStatus = MissionStatus.PROGRESSING;
			MissionStatus missionStatus = MissionStatus.COMPLETED;
			int page = 1;

			System.out.println("Executing getMissionsByMemberAndStatus with parameters:");
			System.out.println("MemberId: " + memberId);
			System.out.println("MissionStatus: " + missionStatus);
			System.out.println("page: " + page);

			missionQueryService.getMissionsByMemberAndStatus(memberId, missionStatus, page)
					.forEach(System.out::println);

		};
	}
}
