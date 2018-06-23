<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="emailMessage" type="java.lang.String" -->
<#-- @ftlvariable name="oldEmail" type="java.lang.String" -->
<#-- @ftlvariable name="passMessage" type="java.lang.String" -->

<#import "fragment/main.ftl" as main>
<#import "fragment/header.ftl" as header>

<@main.main title="Мой профиль" css="style">
    <@header.nav true/>

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
                        <input type="text" class="form-control"
                               autocomplete="off"
                               name="oldPassword"
                               placeholder="Старый пароль"
                               required>
                    </div>
                    <div class="col-md-3">
                        <input type="text" class="form-control"
                               autocomplete="off"
                               name="newPassword"
                               placeholder="Новый пароль"
                               required>
                    </div>
                </div>
                <div class="text-center mt-3">
                    <button type="submit" class="btn btn-secondary">Сохранить</button>
                </div>
            </form>
            <div class="text-center mt-3 h4">
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
                    <div class="col-md-6">
                        <input type="email" class="form-control"
                               autocomplete="off"
                               name="newEmail"
                               value="${oldEmail}"
                               required>
                    </div>
                </div>
                <div class="text-center mt-3">
                    <button type="submit" class="btn btn-secondary">Сохранить</button>
                </div>
            </form>
            <div class="text-center mt-3 h4">
                <#if emailMessage??>
                    ${emailMessage}
                </#if>
            </div>
        </div>
    </div>

</@main.main>