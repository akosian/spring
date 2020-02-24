<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${username}</h5>

    <form action="/user/profile" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password :</label>
            <div class="col-sm-6">
                <label>
                    <input class="form-control" type="password" name="password" placeholder="Password"/>
                </label>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email :</label>
            <div class="col-sm-6">
                <label>
                    <input class="form-control" type="email" name="email" placeholder="test@test.com" value="${email!''}"/>
                </label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Save</button>
    </form>

    <#macro logout>
        <form action="/logout" method="post">
            <button class="btn btn-primary" type="submit">Sign Out</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </#macro>
</@c.page>