<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Edit User</h5>
    <form action="/user" method="post">
        <div class="form-group">
            <label for="username">User name</label>
            <input class="form-control" id="username" type="text" name="username" value="${user.username}">
        </div>
        <div>
            ${validationMessage!}
        </div>
        <div class="form-group">
            <h5>Role Groups</h5>
        </div>
        <div class="form-group">
            <#list roleGroups as roleGroup>
                <div class="form-group form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1"
                           name="${roleGroup.getRoleGroupName()}" ${user.roleGroups?seq_contains(roleGroup.getRoleGroupName())?string("checked", "")}>
                    <label class="form-check-label" for="inlineCheckbox1">${roleGroup.getRoleGroupName()}</label>
                </div>
            </#list>
            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <div class="form-group">
                <button class="btn btn-primary" type="submit">Save</button>
                <div class="form-group">
                </div>
            </div>
        </div>
    </form>
</@c.page>