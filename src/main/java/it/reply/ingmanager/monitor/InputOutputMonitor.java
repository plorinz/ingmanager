package it.reply.ingmanager.monitor;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(100)
@Component
public class InputOutputMonitor {

	private static final Logger logger = Logger.getLogger(InputOutputMonitor.class.getCanonicalName());

	@Around("execution(* it.reply..*AccountsApiController.*(..))")
	public Object aroundAccounts(ProceedingJoinPoint joinPoint) throws Throwable {
		StringBuilder input = new StringBuilder();
		input.append("Method: " + joinPoint.getSignature());
		input.append(" Values: ");
		for (Object arg : joinPoint.getArgs())
			input.append(String.valueOf(arg) + " ");

		logger.info("Request - " + input.toString().replace(System.lineSeparator(), " "));

		Object output = null;
		try {
			output = joinPoint.proceed();
			logger.info("Response - " + String.valueOf(output).replace(System.lineSeparator(), " "));
		} catch (Exception e) {
			logger.info("Exception - " + e);
			throw e;
		}

		return output;
	}
	
	@Around("execution(* it.reply..*TransactionsApiController.*(..))")
	public Object aroundTransactions(ProceedingJoinPoint joinPoint) throws Throwable {
		StringBuilder input = new StringBuilder();
		input.append("Method: " + joinPoint.getSignature());
		input.append(" Values: ");
		for (Object arg : joinPoint.getArgs())
			input.append(String.valueOf(arg) + " ");

		logger.info("Request - " + input.toString().replace(System.lineSeparator(), " "));

		Object output = null;
		try {
			output = joinPoint.proceed();
			logger.info("Response - " + String.valueOf(output).replace(System.lineSeparator(), " "));
		} catch (Exception e) {
			logger.info("Exception - " + e);
			throw e;
		}

		return output;
	}
}
