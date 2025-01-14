package com.schneider.onlineshop.aspect.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;

@Aspect
@Component
public class LoggingAspectFileAndKonsole {



        private static final Logger logger =
                LoggerFactory.getLogger(LoggingAspectFileAndKonsole.class);

        /**
         * Pointcut для всех методов контроллеров.
         */
        @Pointcut("within(com.schneider.onlineshop.controller..*)")
        public void controllerMethods() {}

        /**
         * Pointcut для всех методов сервисов.
         */
        @Pointcut("within(com.schneider.onlineshop.service..*)")
        public void serviceMethods() {}

        /**
         * Аспект для логирования методов контроллеров.
         */
        @Around("controllerMethods()")
        public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
            long start = System.currentTimeMillis();
            Object result;

            try (FileWriter logFile = new FileWriter("controller-logs.txt", true)) {
                result = joinPoint.proceed();
                long executionTime = System.currentTimeMillis() - start;

                String logMessage = String.format("Method: %s, Args: %s, Execution time: %dms%n",
                        joinPoint.getSignature().toShortString(),
                        joinPoint.getArgs(),
                        executionTime);

                logFile.write(logMessage);
                logger.info("Logged to file: {}", logMessage);
            } catch (Throwable ex) {
                logger.error("Exception in Controller Method: {}", joinPoint.getSignature().toShortString(), ex);
                throw ex;
            }

            return result;
        }

        /**
         * Аспект для логирования методов сервисов.
         */
        @Around("serviceMethods()")
        public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
            logger.info("Executing method: {}", joinPoint.getSignature().toShortString());
            try {
                return joinPoint.proceed();
            } catch (Throwable ex) {
                logger.error("Exception in Service Method: {}", joinPoint.getSignature().toShortString(), ex);
                throw ex;
            }
        }
    }


