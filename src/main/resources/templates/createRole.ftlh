<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        ${message!""}
    </div>
    <form action="/createRole" method="post">
        <input type="text" name="roleGroupName" placeholder="Role Group Name">
        <#list roles as role>
            <div>
                <label><input type="checkbox"
                              name="${role}">${role}</label>
            </div>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
        </#list>
        <button type="submit">Create Role Group</button>
    </form>
</@c.page>