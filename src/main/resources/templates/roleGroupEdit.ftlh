<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/roleGroup" method="post">
        <input type="text" name="roleGroupName" value="${roleGroup.roleGroupName}">
        <#list roles as role>
            <div>
                <label><input type="checkbox"
                              name="${role}" ${roleGroup.roles?seq_contains(role)?string("checked","")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${roleGroup.id}" name="roleGroupId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>