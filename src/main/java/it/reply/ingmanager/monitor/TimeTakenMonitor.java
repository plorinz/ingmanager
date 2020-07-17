package it.reply.ingmanager.monitor;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class TimeTakenMonitor {

	private static final Logger logger = Logger.getLogger(TimeTakenMonitor.class.getCanonicalName());

	@Around("execution(* it.reply..*ApiController.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		final Instant start = Instant.now();

		Object output = null;
		try {
			output = joinPoint.proceed();
		} finally {
			logger.info("Method: " + joinPoint.getSignature() + " - Time taken: " + Duration.between(start, Instant.now()));
		}

		return output;
	}
}
