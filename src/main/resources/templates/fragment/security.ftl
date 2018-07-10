<#assign
    isSession = Session.SPRING_SECURITY_CONTEXT??
>

<#if isSession>
    <#assign
        authUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        loginName = authUser.getUsername()
        isAdmin = authUser.isAdmin()
        userId = authUser.id
    >
</#if>
