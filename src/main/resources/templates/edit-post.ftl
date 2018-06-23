<#-- @ftlvariable name="post" type="com.junior.blog.model.Post" -->

<#import "fragment/main.ftl" as main>
<#import "fragment/header.ftl" as header>

<@main.main title="Редактирование статьи" css="style">
    <@header.nav true/>

    <div class="container">

        <form class="text-center" action="/post/edit" novalidate method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${post.id}"/>

            <div class="form-group">
                <label for="01">Заголовок</label>
                <input type="text" class="form-control" id="01" name="title" value="${post.title}"/>
            </div>
            <div class="form-group">
                <label for="02">Описание</label>
                <input type="text" class="form-control" id="02" name="description" value="${post.description}"/>
            </div>
            <div class="form-group">
                <label for="03">Статья</label>
                <textarea class="form-control" id="03" rows="6" name="text">${post.text}</textarea>
            </div>

            <button class="btn btn-secondary" type="submit">Сохранить</button>
        </form>

    </div>

</@main.main>

