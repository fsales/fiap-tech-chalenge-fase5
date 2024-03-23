package br.com.wells.wellspagamento.infrastructure.spring.config.database.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class SpringJpaAuditingConfig /* implements AuditorAware<String> */ {

	// @Override
	// public Optional<String> getCurrentAuditor() {
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// if (authentication != null && authentication.isAuthenticated()) {
	// return Optional.of(authentication.getName());
	// }
	// return null;
	// }

}
