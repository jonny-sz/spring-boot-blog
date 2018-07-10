package com.junior.blog.repository.util

const val selectEmptyCategories = """
select * from "category" c
  left join "post" p on c.id = p.category_id
where p.id isnull
"""

const val selectAllPostsByUser = """
select * from post
where usr_id = ?
order by created desc
"""
