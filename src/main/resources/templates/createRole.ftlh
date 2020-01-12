<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Create Role</h5>
    <div>
        ${message!""}
    </div>
    <form action="/createRole" method="post">
        <div class="form-group">
            <label for="roleGroupName">Email address</label>
            <input class="form-control" id="roleGroupName" type="text" name="roleGroupName"
                   placeholder="Role Group Name">
        </div>
        <div class="form-group">
            <#list roles as role>
                <div class="form-group form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="${role}">
                    <label class="form-check-label" for="inlineCheckbox1">${role}</label>
                </div>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </#list>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Create Role Group</button>
        </div>
    </form>
</@c.page>