package com.junior.blog.repository.util

const val selectEmptyCategories = """
select * from "category"
  left join "post" on category.category_id = post.category_id
where "post_id" isnull
"""