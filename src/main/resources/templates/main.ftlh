<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
        <form><span><a href="/createRole">Create Role Group</a> </span></form>
        <form><span><a href="/roleGroup">Role Group List</a></span></form>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Enter message text"/>
            <input type="text" name="tag" placeholder="Enter tag"/>
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add Message</button>
        </form>
    </div>
    <div>
        <form method="post" action="/delete">
            <input type="text" name="messageValue" placeholder="Enter message id to delete"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Delete message</button>
        </form>
    </div>
    <div>Messages list</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Find</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}"
                </#if>
            </div>
        </div>
    <#else>
        No Messages
    </#list>
</@c.page>