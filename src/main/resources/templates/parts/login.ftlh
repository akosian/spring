<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <label>
                    <input class="form-control" type="text" name="username" placeholder="User name"/>
                </label>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password :</label>
            <div class="col-sm-6">
                <label>
                    <input class="form-control" type="password" name="password" placeholder="Password"/>
                </label>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email :</label>
                <div class="col-sm-6">
                    <label>
                        <input class="form-control" type="email" name="email" placeholder="test@test.com"/>
                    </label>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
        <div class="mt-2">
            <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Sign Out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>