<#-- @ftlvariable name="categories" type="java.util.Collection<com.junior.blog.model.Category>" -->
<#-- @ftlvariable name="post" type="com.junior.blog.model.Post" -->

<#import "fragment/main.ftl" as main>
<#import "fragment/header.ftl" as header>
<#import "fragment/input.ftl" as i>

<@main.main title="Новая статья" css="style">
    <@header.nav true/>

    <div class="container">

        <@i.input_new_category></@i.input_new_category>

        <form class="text-center needs-validation" action="/post/new/add" novalidate method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <@i.select_category></@i.select_category>
            <@i.input type="text" label="Заголовок" id="01" name="title" feedback="Заполните заголовок"></@i.input>
            <@i.input type="text" label="Описание" id="02" name="description" feedback="Заполните описание"></@i.input>
            <@i.text_area label="Статья" id="02" name="text" feedback="Заполните статью"></@i.text_area>

            <button class="btn btn-secondary" type="submit">Сохранить</button>
            <button class="btn btn-secondary" type="reset">Сбросить</button>
        </form>

    </div>

    <script src="/js/validation.js"></script>
</@main.main>

