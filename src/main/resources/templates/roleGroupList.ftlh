<#import "parts/common.ftlh" as c>

<@c.page>
    List of role groups
    <table>
        <thead>
        <tr>
            <th>Role Group Name</th>
            <th>Roles</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list roleGroups as roleGroup>
            <tr>
                <td>${roleGroup.roleGroupName}</td>
                <td><#list roleGroup.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/roleGroup/${roleGroup.id}">Edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <form>
        <div>
            <form><span><a href="/createRole">Create Role Group</a> </span></form>
        </div>
    </form>
</@c.page>