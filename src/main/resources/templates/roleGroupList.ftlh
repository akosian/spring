<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>List of role groups</h5>
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
            <form><span><a class="badge badge-primary" href="/createRole">Create Role Group</a> </span></form>
        </div>
    </form>
</@c.page>