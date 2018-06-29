<#-- @ftlvariable name="post" type="com.junior.blog.model.Post" -->
<#include "fragment/security.ftl">

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="${post.title}"></@head.base>

    <body>
        <@nav.nav true/>

        <div class="container post-bg-color p-4">
            <div class="text-center"><h2 class="my-title">${post.title}</h2></div>
            <div class="text-center"><h4>${post.description}</h4></div>
            <div class="post-indent my-5 mx-4">${post.text}</div>
            <div class="text-center">
                Категория:
                <a href="/category/${post.category.id}">
                    ${post.category.title}
                </a>
            </div>
            <div class="text-center">
                <span class="date">Дата: ${post.created?string["dd.MM.yyyy, HH:mm"]} Автор: ${post.user.username}</span>
            </div>
            <#if isSession && isAdmin>
                <div class="text-center mt-2">
                    <div class="d-inline-block">
                        <form action="/post/edit-form" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="postId" value="${post.id}">

                            <button type="submit" class="btn btn-outline-secondary btn-sm">
                                <i class="fa fa-pencil"></i>Edit
                            </button>
                        </form>
                    </div>
                    <div class="d-inline-block">
                        <form action="/post/delete" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="postId" value="${post.id}">

                            <button type="submit" class="btn btn-outline-secondary btn-sm">
                                <i class="fa fa-times"></i>Delete
                            </button>
                        </form>
                    </div>
                </div>
            </#if>
        </div>

        <#include "fragment/bootstrap-js.ftl">
    </body>
</@common.base>
