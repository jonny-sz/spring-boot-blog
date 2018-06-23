<#-- @ftlvariable name="categories" type="java.util.Collection<com.junior.blog.model.Category>" -->

<#macro input_new_category>

    <form class="text-center needs-validation" novalidate action="/category" method="post">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="input-group mb-3">
            <input type="text" class="form-control"
                   placeholder="Новая категория"
                   aria-label="Recipient's username"
                   aria-describedby="basic-addon2"
                   id="validationCustom04"
                   name="title"
                   required>

            <div class="input-group-append">
                <button class="btn btn-secondary" type="submit">Создать категорию</button>
            </div>

            <div class="invalid-feedback text-center">Заполните категорию</div>
        </div>
    </form>
</#macro>

<#macro input label type id name feedback>
    <div class="form-group">
        <label for="validationCustom${id}">${label}</label>
        <input type="${type}"
               class="form-control"
               id="validationCustom${id}"
               required
               name="${name}"
               placeholder="${label}"/>
        <div class="invalid-feedback text-center">${feedback}</div>
    </div>
</#macro>

<#macro text_area label id name feedback>
    <div class="form-group">
        <label for="exampleFormControlTextarea${id}">${label}</label>
        <textarea class="form-control"
                  id="exampleFormControlTextarea${id}"
                  rows="6"
                  required
                  name="${name}"
                  placeholder="${label}"></textarea>
        <div class="invalid-feedback text-center">${feedback}</div>
    </div>
</#macro>


<#macro select_category>
    <div class="form-group">
        <select class="custom-select" name="category" required>
            <option value="">Категория</option>
                    <#list categories as category>
                        <option value="${category.id}">${category.title}</option>
                    </#list>
        </select>
        <div class="invalid-feedback text-center">Выберите или создайте свою категорию</div>
    </div>
</#macro>