<#-- @ftlvariable name="categories" type="java.util.Collection<com.junior.blog.model.Category>" -->
<#include "security.ftl">

<#macro nav allItems>

<header class="sticky-top">
    <#if  user??>
        <div class="container-fluid text-center top">
            Добро пожаловать, <span class="usr font-weight-bold">${username}</span>!
        </div>
    </#if>

    <nav class="navbar navbar-expand-sm navbar-light navbar-custom" data-toggle="affix">

        <div class="mx-auto d-sm-flex d-block flex-sm-nowrap">

            <a class="navbar-brand" href="/">
                <i class="fa fa-home fa-home"></i>&nbsp;ГЛАВНАЯ
            </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample11"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <#if allItems>
                <div class="collapse navbar-collapse text-center" id="navbarsExample11">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Категории
                            </a>
                            <div class="dropdown-menu bg-light" aria-labelledby="navbarDropdown">
                                <#list categories as category>
                                    <a class="dropdown-item" href="/category/${category.id}">${category.title}</a>
                                <#else>
                                    <span class="dropdown-item">Нет категорий</span>
                                </#list>
                            </div>
                        </li>
                        <#if user??>
                            <li class="nav-item">
                                <a class="nav-link" href="/post/new/form">Добавить статью</a>
                            </li>
                            <#if isAdmin>
                                <li class="nav-item">
                                    <a class="nav-link" href="/user">Пользователи</a>
                                </li>
                            </#if>
                            <li class="nav-item">
                                <a class="nav-link" href="/post/all-my/">Мои статьи</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/user/profile">Мой профиль</a>
                            </li>
                            <li class="nav-item">
                                <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <button class="btn btn-outline-warning my-2 my-sm-0" type="submit">Выйти</button>
                                </form>
                            </li>
                        <#else>
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Войти</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </#if>

        </div>

    </nav>

</header>
</#macro>
