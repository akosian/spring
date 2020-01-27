<#import "parts/common.ftlh" as c>
<#assign know = Session.SPRING_SECURITY_CONTEXT??>
<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign name = "Guest" isAdmin = false>
</#if>

<@c.page>
    <#if isAdmin>
        <h5>Hello, ${name}</h5>
        <div>This is simple test project</div>
    <#else>
        <h5>Hello, guest</h5>
        <div>This is simple test project</div>
    </#if>
</@c.page>