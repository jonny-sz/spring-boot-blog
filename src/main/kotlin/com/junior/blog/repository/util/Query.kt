package com.junior.blog.repository.util

const val selectEmptyCategories = """
select * from "category"
  left join "post" on category.category_id = post.category_id
where "post_id" isnull
"""

const val selectAllPostsByUser = """
select * from post
where usr_id = ?
order by post_created desc
"""
