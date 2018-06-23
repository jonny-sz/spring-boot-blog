<#assign
    isSession = Session.SPRING_SECURITY_CONTEXT??
>

<#if isSession>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        username = user.getUsername()
        isAdmin = user.isAdmin()
        userId = user.id
    >
</#if>
