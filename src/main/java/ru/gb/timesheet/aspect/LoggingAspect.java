package ru.gb.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j // Slf4j - Simple logging facade for java
@Aspect
@Component
public class LoggingAspect {

  // Before
  // AfterThrowing
  // AfterReturning
  // After = AfterReturning + AfterThrowing
  // Around ->

//  Bean = TimesheetService, obj = timesheetService
  // proxyTimesheetService(obj)

  @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
  public void timesheetServiceMethodsPointcut() {
  }

  // Pointcut - точка входа в аспект
  @Before(value = "timesheetServiceMethodsPointcut()")
  public void beforeTimesheetServiceFindById(JoinPoint jp) {
    String methodName = jp.getSignature().getName();

    try  {
      String typeArgs = jp.getArgs()[0].getClass().toString();

      String argsValue = jp.getArgs()[0].toString();

      log.info("Before -> TimesheetService#{}({} = {})", methodName, typeArgs, argsValue);
    }
    catch (NullPointerException e){
      log.info("Before -> TimesheetService#{}", methodName);
    }
  }

//  @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "ex")
//  public void afterTimesheetServiceFindById(JoinPoint jp, Exception ex) {
//    String methodName = jp.getSignature().getName();
//    log.info("AfterThrowing -> TimesheetService#{} -> {}", methodName, ex.getClass().getName());
//  }

}
