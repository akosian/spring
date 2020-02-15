<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>List of users</h5>
    ${deleteFailMessage!}
    <table class="table table-borderless mr-2">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role Group</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.getRoleGroups() as roleGroup>${roleGroup}<#sep>, </#list></td>
                <td><a class="badge badge-primary" href="/user/${user.id}">Edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <div class="form-row">
        <div class="form-group col-mr-6">
            <form class="form-inline" method="post" action="/user/remove">
                <label>
                    <input class="form-control" type="text" name="usernameToRemove"
                           placeholder="Enter user name">
                </label>
                <input class="form-control" type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary ml-2" type="submit">Remove</button>
            </form>
        </div>
    </div>
</@c.page>