<#-- @ftlvariable name="posts" type="java.util.Collection<com.junior.blog.model.Post>" -->
<#include "fragment/security.ftl">

<#import "fragment/main.ftl" as main>
<#import "fragment/header.ftl" as header>

<@main.main title="Новости" css="style">
    <@header.nav true/>

    <section>
        <#list posts as post>
            <div class="py-4">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <a class="post-link" href="/post/${post.id}">
                                <h2 class="text-center my-title">${post.title}</h2>
                                <h4 class="text-center">${post.description}</h4>
                            </a>
                            <div class="text-center">
                                Категория:
                                <a href="/category/${post.category.id}">
                                    ${post.category.title}
                                </a>
                            </div>
                            <div class="text-center">
                                <span class="date">
                                    <i class="fa fa-history"></i> ${post.created?string["dd.MM.yyyy, HH:mm"]}
                                    <i class="fa fa-user"></i> ${post.user.username}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="text-center">
                <p class="py-4 h5">
                    Статьи отсутствуют, но ты можешь это изменить!
                </p>
                <a class="btn btn-secondary" href="/post/new/form" role="button">Добавить</a>
            </div>
        </#list>
    </section>

</@main.main>
