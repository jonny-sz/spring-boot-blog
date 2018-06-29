<#-- @ftlvariable name="success_activation_message" type="java.lang.String" -->
<#-- @ftlvariable name="fail_activation_message" type="java.lang.String" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="Авторизация">
        <link rel="stylesheet" type="text/css" href="/css/login.css"/>
    </@head.base>

    <body>
        <@nav.nav false/>

        <#if RequestParameters.error??>
            <div class="text-center text-danger h5">
                Неправильный логин или пароль!
            </div>
        </#if>
        <#if success_activation_message??>
            <div class="text-center text-success h5">
                ${success_activation_message}
            </div>
        </#if>
        <#if fail_activation_message??>
            <div class="text-center text-danger h5">
                ${fail_activation_message}
            </div>
        </#if>

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-sm-6 col-md-4 col-md-offset-4 outlined">
                    <h1 class="text-center login-title">Авторизация</h1>
                    <div class="account-wall">
                        <form class='form-signin' action="/login" method='post'>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="form-group">
                                <input type='text' name='username' class='underlined' placeholder='Username' required autofocus>
                            </div>
                            <div class="form-group">
                                <input type='password' name='password' class='underlined' placeholder='Password' required>

                            </div>

                            <div class="form-group">
                                <button class='btn btn-lg btn-primary btn-block' type='submit'>Войти</button>
                            </div>
                            <span class="clearfix"></span>
                        </form>
                    </div>
                    <div class="text-center">
                        <a href="/registration" class="new-account">Зарегистрироваться</a>
                    </div>
                </div>
            </div>
        </div>

        <#include "fragment/bootstrap-js.ftl">
    </body>
</@common.base>
