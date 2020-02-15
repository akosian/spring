<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>List of role groups</h5>
    ${deleteFailMessage!}
    <table class="table table-borderless mr-2">
        <thead>
        <tr>
            <th scope="col">Role Group Name</th>
            <th scope="col">Roles</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list roleGroups as roleGroup>
            <tr>
                <td>${roleGroup.roleGroupName}</td>
                <td><#list roleGroup.roles as role>${role}<#sep>, </#list></td>
                <td><a class="badge badge-primary" href="/roleGroup/${roleGroup.id}">Edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <form>
        <div>
            <form><a class="badge badge-primary" href="/createRole">Create Role Group</a></form>
        </div>
    </form>
    <div class="form-row">
        <div class="form-group col-mr-6">
            <form class="form-inline" method="post" action="/roleGroup/remove">
                <label>
                    <input class="form-control" type="text" name="roleGroupNameToRemove"
                           placeholder="Enter role group name">
                </label>
                <input class="form-control" type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary ml-2" type="submit">Remove</button>
            </form>
        </div>
    </div>
</@c.page>