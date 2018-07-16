<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="emailMessage" type="java.lang.String" -->
<#-- @ftlvariable name="oldEmail" type="java.lang.String" -->
<#-- @ftlvariable name="passMessage" type="java.lang.String" -->

<#import "fragment/common.ftl" as common>
<#import "fragment/head.ftl" as head>
<#import "fragment/navbar.ftl" as nav>

<@common.base>
    <@head.base title="Мой профиль"></@head.base>

    <body>
        <@nav.nav true/>

        <div class="container">
            <div class="border border-secondary rounded py-4 bg-light">
                <form action="/user/profile/password" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="text-center">
                        <h5>Изменение пароля</h5>
                    </div>
                    <div class="form-row mt-3">
                        <div class="col-md-3"></div>
                        <div class="col-md-3">
                            <input type="text"
                                   class="form-control ${(passWrapOldPasswordError??)?string('is-invalid', '')}"
                                   autocomplete="off"
                                   name="oldPassword"
                                   placeholder="Старый пароль">
                            <#if passWrapOldPasswordError??>
                                <#list passWrapOldPasswordError as err>
                                    <div class="invalid-feedback text-center">${err}</div>
                                </#list>
                            </#if>
                        </div>
                        <div class="col-md-3">
                            <input type="text"
                                   class="form-control ${(passWrapNewPasswordError??)?string('is-invalid', '')}"
                                   autocomplete="off"
                                   name="newPassword"
                                   placeholder="Новый пароль">
                            <#if passWrapNewPasswordError??>
                                <#list passWrapNewPasswordError as err>
                                    <div class="invalid-feedback text-center">${err}</div>
                                </#list>
                            </#if>
                        </div>
                    </div>
                    <div class="text-center mt-3">
                        <button type="submit" class="btn btn-secondary">Сохранить</button>
                    </div>
                </form>
                <div class="text-center mt-3 text-success h5">
                    <#if passMessage??>
                        ${passMessage}
                    </#if>
                </div>
            </div>

            <div class="border border-secondary rounded py-4 bg-light">
                <form action="/user/profile/email" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="text-center">
                        <h5>Изменение Email</h5>
                    </div>
                    <div class="form-row mt-3">
                        <div class="col-md-3"></div>
                        <div class="col-md-6 text-center">
                            Текущий: <span class="font-weight-bold">${oldEmail}</span>
                        </div>
                    </div>
                    <div class="form-row mt-3">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <input type="text"
                                   class="form-control ${(emailWrapEmailError??)?string('is-invalid', '')}"
                                   autocomplete="off"
                                   name="email"
                                   placeholder="Новый email">
                            <#if emailWrapEmailError??>
                                <#list emailWrapEmailError as err>
                                    <div class="invalid-feedback text-center">${err}</div>
                                </#list>
                            </#if>
                        </div>
                    </div>
                    <div class="text-center mt-3">
                        <button type="submit" class="btn btn-secondary">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>

        <#include "fragment/bootstrap-js.ftl">
    </body>
</@common.base>
