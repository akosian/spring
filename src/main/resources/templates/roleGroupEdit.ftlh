<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Edit Role group</h5>
    <form action="/roleGroup" method="post">
        <div class="form-group">
            <label for="roleGroupName">Role Group name</label>
            <input class="form-control" id="roleGroupName" type="text" name="roleGroupName"
                   value="${roleGroup.roleGroupName}">
        </div>
        <div class="form-group">
            <h5>Roles</h5>
        </div>
        <div class="form-group">
            <#list roles as role>
                <div class="form-group form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1"
                           name="${role}" ${roleGroup.roles?seq_contains(role)?string("checked","")}>
                    <label class="form-check-label" for="inlineCheckbox1">${role}</label>
                </div>
            </#list>
            <input type="hidden" value="${roleGroup.id}" name="roleGroupId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <div class="form-group">
                <button class="btn btn-primary ml-2" type="button">Save</button>
            </div>
        </div>
    </form>
</@c.page>