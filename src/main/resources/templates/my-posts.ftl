<#-- @ftlvariable name="posts" type="java.util.Collection<com.junior.blog.model.Post>" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="Мои статьи"></@head.base>

    <body>
        <@nav.nav true/>

        <div class="container">
            <div class="row border-top border-bottom p-2 mx-2 font-weight-bold">
                <div class="col-sm-6">Заголовок</div>
                <div class="col-sm-2">Категория</div>
                <div class="col-sm-2">Дата</div>
                <div class="col-sm-2">Операции</div>
            </div>
            <#list posts as post>
                <div class="row border-bottom p-2 mx-2">
                    <div class="col-sm-6"><a href="/post/${post.id}">${post.title}</a></div>
                    <div class="col-sm-2"><a href="/category/${post.category.id}">${post.category.title}</a></div>
                    <div class="col-sm-2">${post.created?string["dd.MM.yyyy, HH:mm"]}</div>
                    <div class="col-sm-2">
                        <div class="d-inline-block">
                            <form action="/post/edit-form" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="postId" value="${post.id}">

                                <button type="submit" class="btn btn-secondary btn-sm">
                                    <i class="fa fa-pencil"></i>Edit
                                </button>
                            </form>
                        </div>
                        <div class="d-inline-block">
                            <form action="/post/delete" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="postId" value="${post.id}">

                                <button type="submit" class="btn btn-secondary btn-sm">
                                    <i class="fa fa-times"></i>Delete
                                </button>
                            </form>
                        </div>

                    </div>
                </div>
            <#else>
                <div class="text-center mt-2 h5">
                    Статьи отсутствуют
                </div>
            </#list>
        </div>

        <#include "fragment/bootstrap-js.ftl">
    </body>
</@common.base>
