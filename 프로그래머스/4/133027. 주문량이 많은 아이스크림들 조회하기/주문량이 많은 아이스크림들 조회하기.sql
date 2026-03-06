-- 코드를 입력하세요
with base as (
    select j.FLAVOR,
            sum(j.TOTAL_ORDER) as TOTAL_ORDER
    from JULY j
    group by j.FLAVOR
), sort as (
    select f.FLAVOR,
            f.TOTAL_ORDER + b.TOTAL_ORDER as TOTAL_ORDER
    from FIRST_HALF f
    inner join base b on b.FLAVOR = f.FLAVOR
    order by TOTAL_ORDER desc
)

select FLAVOR
from sort 
limit 3

