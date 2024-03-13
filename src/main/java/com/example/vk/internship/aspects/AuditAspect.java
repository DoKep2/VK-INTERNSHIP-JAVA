package com.example.vk.internship.aspects;

import com.example.vk.internship.model.Role;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Aspect
@Component
public class AuditAspect {

    @Pointcut("execution(* com.example.vk.internship.controller.*.*(..))")
    private void controllerExecution() {
    }

    @AfterReturning(pointcut = "controllerExecution()", returning = "result")
    public void logAudit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        LocalDateTime timestamp = LocalDateTime.now();
        String username = getName();
        boolean hasAccess = hasAccessToMethod(methodName);

        System.out.println("Audit Log - Date-Time: " + timestamp +
                ", User: " + username +
                ", Roles: " + getRoles() +
                ", Method: " + className + "." + methodName +
                ", Has Access: " + hasAccess +
                ", Result: " + result);
    }

    private String getName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private List<Role> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (List<Role>) authentication.getAuthorities();
    }

    public boolean hasAccessToMethod(String methodName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return checkAccess(getRoles(), methodName);
        }
        return false;
    }

    private boolean checkAccess(List<Role> role, String methodName) {
        if (role.contains(Role.ROLE_ADMIN)) {
            return true;
        }

        if (role.contains(Role.ROLE_USERS)) {
            return methodName.contains("User");
        }

        if (role.contains(Role.ROLE_POSTS)) {
            return methodName.contains("Post");
        }

        if (role.contains(Role.ROLE_ALBUMS)) {
            return methodName.contains("Album");
        }

        return false;
    }
}
