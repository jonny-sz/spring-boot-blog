<#-- @ftlvariable name="message" type="java.lang.String" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="Регистрация">
        <link rel="stylesheet" type="text/css" href="/css/registration.css"/>
    </@head.base>

    <body>
        <@nav.nav false/>

        <div class="container">
            <form class="text-center form-horizontal" role="form" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <h2>Регистрация нового пользователя</h2>
                        <hr>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 field-label-responsive">
                        <label for="name">Логин</label>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                                <input type="text" name="username"
                                       class="form-control ${(userUsernameError??)?string('is-invalid', '')}"
                                       autocomplete="off"
                                       id="username"
                                       placeholder="JONNY-SZ"
                                       value="<#if user??>${user.username}</#if>">
                                <#if userUsernameError??>
                                    <#list userUsernameError as err>
                                        <div class="invalid-feedback text-center">${err}</div>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 field-label-responsive">
                        <label for="email">E-Mail</label>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                                <input type="text" name="email"
                                       class="form-control ${(userEmailError??)?string('is-invalid', '')}"
                                       autocomplete="off"
                                       id="email"
                                       placeholder="you@example.com">
                                <#if userEmailError??>
                                    <#list userEmailError as err>
                                        <div class="invalid-feedback text-center">${err}</div>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 field-label-responsive">
                        <label for="password">Пароль</label>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group has-danger">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                                <input type="password" name="password"
                                       class="form-control ${(userPasswordError??)?string('is-invalid', '')}"
                                       autocomplete="off"
                                       id="password"
                                       placeholder="Пароль">
                                <#if userPasswordError??>
                                    <#list userPasswordError as err>
                                        <div class="invalid-feedback text-center">${err}</div>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-secondary"><i class="fa fa-user-plus"></i> Сохранить</button>
                    </div>
                </div>
            </form>

            <div class="text-center mt-3 text-danger h4">
                <#if message??>
                    ${message}
                </#if>
            </div>

            <div class="text-center mt-5 h5 bg-info text-white p-3">
                После регистрации активируйте свой аккаунт через письмо, высланное Вам на E-mail.
            </div>
        </div>

        <#include "fragment/bootstrap-js.ftl">
    </body>
</@common.base>
