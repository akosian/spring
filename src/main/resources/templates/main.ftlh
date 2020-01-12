<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-mr-6">
            <form class="form-inline" method="get" action="/main">
                <input class="form-control" type="text" name="filter" value="${filter!}"
                       placeholder="Filter by message value">
                <button class="btn btn-primary ml-2" type="submit">Find</button>
            </form>
        </div>
        <div class="form-group col-mr-6">
            <form class="form-inline" method="post" action="/delete">
                <input class="form-control" type="text" name="messageValue"
                       placeholder="Remove message by ID"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary ml-2" type="submit">Delete message</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false">
        Add new message
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input class="form-control" type="text" name="text" placeholder="Enter message text"/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="tag" placeholder="Enter tag"/>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input class="form-control" type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose File</label>
                    </div>
                </div>
                <input class="form-control" type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button class="btn btn-primary" class="btn btn-primary" type="submit">Add Message</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="img-thumbnail">
                </#if>
                <div class="m-2">
                    <b>${message.id}</b>
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        <#else>
            No Messages
        </#list>
    </div>
</@c.page>