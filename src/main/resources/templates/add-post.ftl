<#-- @ftlvariable name="categories" type="java.util.Collection<com.junior.blog.model.Category>" -->
<#-- @ftlvariable name="post" type="com.junior.blog.model.Post" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>
<#import "fragment/post-input.ftl" as i>

<@common.base>
    <@head.base title="Добавить статью">
        <#include "fragment/froala-css.ftl">
    </@head.base>

    <body>
        <@nav.nav true/>

        <div class="container">

            <@i.input_new_category/>

            <form action="/post/new/add" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <#if post??>
                    <#if post.id??>
                        <input type="hidden" name="id" value="${post.id}""/>
                    </#if>
                </#if>

                <@i.select_category/>
                <@i.input_title/>
                <@i.input_description/>
                <@i.text_area/>

                <div class="text-center">
                    <button class="btn btn-secondary" type="submit">Сохранить</button>
                    <button class="btn btn-secondary" type="reset">Сбросить</button>
                </div>
            </form>

        </div>

        <#include "fragment/bootstrap-js.ftl">
        <#include "fragment/froala-js.ftl">
        <script src="/js/ajaxPostCategory.js"></script>
    </body>
</@common.base>
