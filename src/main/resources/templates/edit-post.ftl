<#-- @ftlvariable name="post" type="com.junior.blog.model.Post" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="Редактирование статьи">
        <#include "fragment/froala-css.ftl">
    </@head.base>

    <body>
        <@nav.nav true/>

        <div class="container">

            <form action="/post/edit" novalidate method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${post.id}"/>

                <div class="form-group">
                    <div class="text-center"><label for="01">Заголовок</label></div>
                    <input type="text" class="form-control" id="01" name="title" value="${post.title}"/>
                </div>
                <div class="form-group">
                    <div class="text-center"><label for="02">Описание</label></div>
                    <input type="text" class="form-control" id="02" name="description" value="${post.description}"/>
                </div>
                <div class="form-group">
                    <div class="text-center"><label for="editor">Статья</label></div>
                    <textarea class="form-control" id="editor" name="text">${post.text}</textarea>
                </div>

                <div class="text-center">
                    <button class="btn btn-secondary" type="submit">Сохранить</button>
                </div>
            </form>

        </div>

        <#include "fragment/bootstrap-js.ftl">
        <#include "fragment/froala-js.ftl">
    </body>
</@common.base>
